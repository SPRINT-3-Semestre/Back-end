package com.example.EditMatch.controller.transacao.dto;

import com.example.EditMatch.entity.carteira.Carteira;
import com.google.api.client.util.DateTime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoResponseDto {
    private DateTime dataHora;
    private String tipo;
    private Double valor;
    private Carteira carteira;
}
