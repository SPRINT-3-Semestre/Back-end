package com.example.EditMatch.controller.transacao.dto;

import com.google.api.client.util.DateTime;
import lombok.Data;

@Data
public class TransacaoSaqueDto {
    private String message = "Saque realizado com sucesso";
    private Double valor;
    private DateTime dataHora;

}
