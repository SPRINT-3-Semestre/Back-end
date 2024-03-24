package com.example.EditMatch.controller.transacao.dto;

import com.example.EditMatch.entity.carteira.Carteira;
import com.google.api.client.util.DateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransacaoCreateDto {
    @NotNull
    private String nomePagante;
    private String tipo;
    @NotNull
    private Double valor;
}
