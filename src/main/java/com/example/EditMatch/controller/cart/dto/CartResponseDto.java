package com.example.EditMatch.controller.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
    private String name;
    private List<String> skills;
    private Double price;
    private Integer cardId;
    private Integer editorId;
}
