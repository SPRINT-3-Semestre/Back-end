package com.example.EditMatch.controller.transacao.mapper;

import com.example.EditMatch.controller.transacao.dto.SaqueResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateSaqueDto;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoMapper {
    public static Transacao toTransacao(TransacaoCreateDto transacaoCreateDto, Carteira carteira) {
        LocalDateTime dataHora = LocalDateTime.now();
        return new Transacao(
                null,
                transacaoCreateDto.getValor(),
                dataHora, // Definir a data e hora atual
                transacaoCreateDto.getTipo(),
                carteira
        );
    }

    public static Transacao toSaque(TransacaoCreateSaqueDto transacaoCreateSaqueDto, Carteira carteira) {
        LocalDateTime dataHora = LocalDateTime.now();

        return new Transacao(
                null,
                transacaoCreateSaqueDto.getValor(),
                dataHora,
                transacaoCreateSaqueDto.getTipo(),
                carteira
        );
    }

    public static SaqueResponseDto saqueResponse(TransacaoCreateSaqueDto transacao){
        SaqueResponseDto saqueResponseDto = new SaqueResponseDto();
        LocalDateTime dataHora = transacao.getDataHora();

        saqueResponseDto.setValor(transacao.getValor());
        saqueResponseDto.setTipo(transacao.getTipo());
        saqueResponseDto.setDataHora(dataHora);

        return saqueResponseDto;
    }




    public static TransacaoResponseDto to(Transacao transacao) {
        TransacaoResponseDto transacaoResponseDto = new TransacaoResponseDto();

        transacaoResponseDto.setValor(transacao.getValor());
        transacaoResponseDto.setTipo(transacao.getTipo());
        transacaoResponseDto.setDataHora(transacao.getDataHora());

        return transacaoResponseDto;
    }


}
