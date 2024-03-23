package com.example.EditMatch.controller.transacao.dto;

import com.example.EditMatch.entity.carteira.Carteira;
import com.google.api.client.util.DateTime;
import lombok.Data;

@Data
public class TransacaoCreateDto {
    private Integer id;
    private DateTime dataHora;
    private String tipo;
    private Double valor;
    private Carteira carteiraId;
}
