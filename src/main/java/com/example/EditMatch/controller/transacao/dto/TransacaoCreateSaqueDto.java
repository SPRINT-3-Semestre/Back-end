package com.example.EditMatch.controller.transacao.dto;

import com.example.EditMatch.entity.carteira.Carteira;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoCreateSaqueDto {
    private Integer id;
    private LocalDateTime dataHora;
    private String tipo;
    private Double valor;
    private Carteira carteiraId;
}
