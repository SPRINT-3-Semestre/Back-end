package com.example.EditMatch.controller.transacao.mapper;

import com.example.EditMatch.controller.order.dto.OrderCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoSaqueDto;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Orders;
import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import lombok.Data;

@Data
public class TransacaoMapper {
    public static Transacao toTransacao(TransacaoCreateDto transacaoCreateDto, Carteira carteira) {
        return new Transacao(
                null,
                transacaoCreateDto.getValor(),
                transacaoCreateDto.getDataHora(),
                transacaoCreateDto.getTipo(),
                carteira
        );
    }

    public static TransacaoResponseDto to(Transacao transacao) {
        TransacaoResponseDto transacaoResponseDto = new TransacaoResponseDto();

        transacaoResponseDto.setValor(transacao.getValor());
        transacaoResponseDto.setTipo(transacao.getTipo());
        transacaoResponseDto.setDataHora(transacao.getDataHora());

        return transacaoResponseDto;
    }


}
