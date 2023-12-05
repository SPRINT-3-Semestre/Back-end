package com.example.EditMatch.controller.order.mapper;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.order.dto.OrderResponseDto;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Order;


public class OrderMapper {

    public static Order toOrder(OrderCreateDto orderCreateDto, ClientFinal clientFinal, Editor editor) {
        return new Order(
                null,
                orderCreateDto.getTitle(),
                orderCreateDto.getDesc(),
                orderCreateDto.getSkills(),
                editor,
                clientFinal
        );
    }
    public static Order of(OrderCreateDto orderCreateDto){
        Order order = new Order();

        order.setTitle(orderCreateDto.getTitle());
        order.setDesc(orderCreateDto.getDesc());
        order.setSkills(orderCreateDto.getSkills());

        return order;
    }

    public static OrderResponseDto of(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setTitle(order.getTitle());
        orderResponseDto.setDesc(order.getDesc());
        orderResponseDto.setSkills(order.getSkills());
        orderResponseDto.setEditorId(order.getEditor().getId());
        orderResponseDto.setOrderId(order.getId());

        return orderResponseDto;
    }
}
