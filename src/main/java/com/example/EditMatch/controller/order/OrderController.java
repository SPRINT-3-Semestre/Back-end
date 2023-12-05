package com.example.EditMatch.controller.order;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.dto.OrderResponseDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.Order;
import com.example.EditMatch.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> orderClient(@RequestParam Integer id) {
        List<Order> orders = orderService.orderClient(id);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orders) {
            orderResponseDtoList.add(OrderMapper.of(order));
        }
        return ResponseEntity.ok(orderResponseDtoList);
    }
    @PostMapping
    public ResponseEntity<Order>addEditor(@Valid @RequestBody OrderCreateDto orderCreateDto){
        return ResponseEntity.ok(orderService.add(orderCreateDto));
    }
    @PatchMapping("/{orderId}/associate-editor/{editorId}")
    public ResponseEntity<Void> associateEditor(@PathVariable Integer orderId, @PathVariable Integer editorId) {
        orderService.associateEditor(orderId, editorId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void>edit(@PathVariable Integer id, @RequestParam Integer quantidade){
        orderService.edit(id,quantidade);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>removeEditor(@PathVariable Integer id){
        orderService.removeEditor(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/empty-cart/{id}")
    public ResponseEntity<Void>emptyCart(@PathVariable Integer id){
        orderService.emptyCart(id);
        return ResponseEntity.noContent().build();
    }
}
