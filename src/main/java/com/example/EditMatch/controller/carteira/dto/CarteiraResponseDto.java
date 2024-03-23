package com.example.EditMatch.controller.carteira.dto;

import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.entity.transacao.Transacao;
import lombok.Data;

import java.util.List;

@Data
public class CarteiraResponseDto {

    private Double saldoTotal;
    private Double saldoAtual;
    private List<TransacaoResponseDto> transacoes;

}
