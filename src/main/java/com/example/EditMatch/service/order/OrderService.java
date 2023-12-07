package com.example.EditMatch.service.order;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Orders;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.OrderRepository;
import com.example.EditMatch.service.order.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ClientFinalRepository clientFinalRepository;
    private final OrderRepository orderRepository;
    private final EditorRepository editorRepository;

    public Orders add(OrderCreateDto orderCreateDto) {
        ClientFinal clientFinal = clientFinalRepository.findById(orderCreateDto.getClientFinal())
                .orElseThrow(() -> new OrderException("Cliente não encontrado"));

        Orders newOrders = OrderMapper.toOrder(orderCreateDto, clientFinal, null);

        return orderRepository.save(newOrders);
    }

    public void associateEditor(Integer orderId, Integer editorId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Ordem não encontrada"));

        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new OrderException("Editor não encontrado"));

        orders.setEditor(editor);
        orderRepository.save(orders);
    }

    public List<Orders> orderClient(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()->new OrderException("Cliente não encontrado")
        );
        return orderRepository.orderClient(clientFinal);
    }
    public void edit(Integer id, String title, String desc, String skills){
        orderRepository.findById(id).orElseThrow(
                ()-> new OrderException("Ordem não encontrada")
        );
        orderRepository.editOrder(id,title,desc,skills);
    }
    public void removeEditorFromOrder(Integer id){
        Optional<Orders> byId = orderRepository.findById(id);
        if(byId.isEmpty()) {
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
