package com.example.EditMatch.controller.order.mapper;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.dto.OrderResponseDto;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Orders;


public class OrderMapper {

    public static Orders toOrder(OrderCreateDto orderCreateDto, ClientFinal clientFinal, Editor editor) {
        return new Orders(
                null,
                orderCreateDto.getTitle(),
                orderCreateDto.getDescrible(),
                orderCreateDto.getSkills().toString(),
                null,
                clientFinal
        );
    }
    public static Orders of(OrderCreateDto orderCreateDto){
        Orders orders = new Orders();

        orders.setTitle(orderCreateDto.getTitle());
        orders.setDescrible(orderCreateDto.getDescrible());
        orders.setSkills(orderCreateDto.getSkills().toString());
        orders.setClientFinal(orders.getClientFinal());
        orders.setEditor(null);

        return orders;
    }

    public static OrderResponseDto of(Orders orders){
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setTitle(orders.getTitle());
        orderResponseDto.setDesc(orders.getDescrible());
        orderResponseDto.setSkills(orders.getSkills());
        if (orders.getEditor() != null) {
            orderResponseDto.setEditorId(orders.getEditor().getId());
        }
        orderResponseDto.setOrderId(orders.getId());
        orderResponseDto.setNome(orders.getClientFinal().getNome());

        return orderResponseDto;
    }
}
