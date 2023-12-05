package com.example.EditMatch.service;

import com.example.EditMatch.controller.cart.dto.CartCreateDto;
import com.example.EditMatch.controller.cart.mapper.CartMapper;
import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.Cart;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Order;
import com.example.EditMatch.repository.CartRepository;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final ClientFinalRepository clientFinalRepository;
    private final OrderRepository orderRepository;
    private final EditorRepository editorRepository;

    public Order add(OrderCreateDto orderCreateDto) {
        ClientFinal clientFinal = clientFinalRepository.findById(orderCreateDto.getClientFinal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Order newOrder = OrderMapper.toOrder(orderCreateDto, clientFinal, null);

        return orderRepository.save(newOrder);
    }

    public void associateEditor(Integer orderId, Integer editorId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        order.setEditor(editor);
        orderRepository.save(order);
    }

    public List<Order> orderClient(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        return orderRepository.orderClient(clientFinal);
    }
    public void edit(Integer id, Integer valorTotal){
        cartRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.editCart(id,valorTotal);
    }
    public void emptyCart(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.emptyCart(clientFinal);
    }
    public void removeEditor(Integer id){
        cartRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.deleteById(id);
    }
}
