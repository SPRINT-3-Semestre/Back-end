package com.example.EditMatch.service.order;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Orders;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.OrderRepository;
import com.example.EditMatch.service.email.EmailService;
import com.example.EditMatch.service.order.exception.OrderException;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ClientFinalRepository clientFinalRepository;
    private final OrderRepository orderRepository;
    private final EditorRepository editorRepository;
    private final EmailService emailService;

    public Orders add(OrderCreateDto orderCreateDto) {
        ClientFinal clientFinal = clientFinalRepository.findById(orderCreateDto.getClientFinal())
                .orElseThrow(() -> new OrderException("Cliente não encontrado"));

        Orders newOrders = OrderMapper.toOrder(orderCreateDto, clientFinal, null);

        emailService.sendNewProjectEmail(clientFinal.getEmail(), clientFinal.getNome(), clientFinal.getLast_name(), newOrders.getTitle());

        return orderRepository.save(newOrders);
    }

    public void associateEditor(Integer orderId, Integer editorId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Ordem não encontrada"));

        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new OrderException("Editor não encontrado"));

        orders.setEditor(editor);
        orderRepository.save(orders);
        emailService.sendAssociateEditorEmail(editor.getEmail(), editor.getNome(), orders.getTitle(), orders.getLink());
    }

    //getOrdersByEditorId
    public List<Orders> getOrdersByEditorId(Integer editorId) {
        // Busca todas as ordens que possuem o editor com o ID fornecido
        return orderRepository.findByEditorId(editorId);
    }

    public Orders finishOrder(Integer id, String link) {
        Orders order = orderRepository.findById(id).orElseThrow(
                () -> new OrderException("Ordem não encontrada")
        );
        order.setLink(link);

        // Obter o usuário associado à ordem
        ClientFinal user = order.getClientFinal();


        // Verificar se o usuário está associado à ordem
        if (user != null) {
            String email = user.getEmail();
            emailService.sendFinishOrderEmail(user.getEmail(), user.getNome(), order.getTitle(), order.getLink());
        } else {
            // Caso o usuário não esteja associado à ordem
            System.out.println("Usuário não encontrado para esta ordem.");
        }

        return orderRepository.save(order);
    }



    public List<Orders> orderClient(Integer id) {
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                () -> new OrderException("Cliente não encontrado")
        );
        return orderRepository.orderClient(clientFinal);
    }

    public void edit(Integer id, String title, String desc, String skills) {
        orderRepository.findById(id).orElseThrow(
                () -> new OrderException("Ordem não encontrada")
        );
        orderRepository.editOrder(id, title, desc, skills);
    }

    public Orders getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderException("Ordem não encontrada")
        );
    }

    public void removeEditorFromOrder(Integer id) {
        Optional<Orders> byId = orderRepository.findById(id);
        if (byId.isEmpty()) {
            throw new OrderException("Ordem não encontrada");
        }
        Orders orders = byId.get();
        orders.setEditor(null);
        orderRepository.deleteById(id);
    }

    public void deleteOrder(Integer id) {
        Optional<Orders> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new OrderException("Ordem não encontrada");
        }
        orderRepository.deleteById(id);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
