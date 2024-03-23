package com.example.EditMatch.controller.transacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  // Generate a constructor with all fields
public class SaqueResponseDto {
    private Double valor;
    private String message;
}
