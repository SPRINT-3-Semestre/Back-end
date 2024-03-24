package com.example.EditMatch.controller.transacao.dto;

import com.example.EditMatch.entity.carteira.Carteira;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoResponseDto {
    private String tipo;
    private Double valor;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Define o formato da data e hora
    private LocalDateTime dataHora;
}
