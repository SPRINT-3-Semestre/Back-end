package com.example.EditMatch.controller.order.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private String nome;
    private String title;
    private String desc;
    private String skills;
    private Integer orderId;
    private Integer editorId;
}
