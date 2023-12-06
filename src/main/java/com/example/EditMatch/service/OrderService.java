package com.example.EditMatch.service;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Orders;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Orders newOrders = OrderMapper.toOrder(orderCreateDto, clientFinal, null);

        return orderRepository.save(newOrders);
    }

    public void associateEditor(Integer orderId, Integer editorId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        orders.setEditor(editor);
        orderRepository.save(orders);
    }

    public List<Orders> orderClient(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return orderRepository.orderClient(clientFinal);
    }
    public void edit(Integer id, String title, String desc, String skills){
        orderRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        orderRepository.editOrder(id,title,desc,skills);
    }
    public void removeEditorFromOrder(Integer id){
        Optional<Orders> byId = orderRepository.findById(id);
        if(byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Orders orders = byId.get();
        orders.setEditor(null);
        orderRepository.deleteById(id);
    }
}
