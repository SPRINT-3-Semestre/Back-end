package com.example.EditMatch.controller.transacao.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaqueResponseDto {
    private Double valor;
    private String tipo;
    private LocalDateTime dataHora;
}