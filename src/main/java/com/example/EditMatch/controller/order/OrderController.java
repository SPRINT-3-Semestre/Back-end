package com.example.EditMatch.controller.order;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.dto.OrderResponseDto;
import com.example.EditMatch.controller.order.mapper.OrderMapper;
import com.example.EditMatch.entity.Orders;
import com.example.EditMatch.service.order.OrderService;
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
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Orders order : orders) {
            orderResponseDtoList.add(OrderMapper.of(order));
        }
        return ResponseEntity.ok(orderResponseDtoList);
    }
    @GetMapping("/order-client")
    public ResponseEntity<List<OrderResponseDto>> orderClient(@RequestParam Integer id) {
        List<Orders> orders = orderService.orderClient(id);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Orders order : orders) {
            orderResponseDtoList.add(OrderMapper.of(order));
        }
        return ResponseEntity.ok(orderResponseDtoList);
    }
    @PostMapping
    public ResponseEntity<OrderResponseDto>add(@Valid @RequestBody OrderCreateDto orderCreateDto){
        Orders add = orderService.add(orderCreateDto);
        OrderResponseDto orderResponseDto = OrderMapper.of(add);
        return ResponseEntity.ok(orderResponseDto);
    }
    @PatchMapping("/{orderId}/associate-editor/{editorId}")
    public ResponseEntity<Void> associateEditor(@PathVariable Integer orderId, @PathVariable Integer editorId) {
        orderService.associateEditor(orderId, editorId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void>edit(@PathVariable Integer id, @RequestParam String title, @RequestParam String desc, @RequestParam String skills){
        orderService.edit(id,title,desc,skills);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/removeEditor/{id}")
    public ResponseEntity<Void>removeEditorFromOrder(@PathVariable Integer id){
        orderService.removeEditorFromOrder(id);
        return ResponseEntity.noContent().build();
    }
}
