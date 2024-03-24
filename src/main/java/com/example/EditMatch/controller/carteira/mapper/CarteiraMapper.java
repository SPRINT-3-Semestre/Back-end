package com.example.EditMatch.controller.carteira.mapper;

import com.example.EditMatch.controller.carteira.dto.CarteiraCreateDto;
import com.example.EditMatch.controller.carteira.dto.CarteiraResponseDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;

import java.util.ArrayList;
import java.util.List;

public class CarteiraMapper {
    public static Carteira of(CarteiraCreateDto CarteiraCreateDto){
        Carteira carteira = new Carteira();
        return carteira;
    }

    public CarteiraResponseDto of(Carteira carteira) {
        CarteiraResponseDto responseDto = new CarteiraResponseDto();
        responseDto.setSaldoTotal(carteira.getSaldoTotal());
        responseDto.setSaldoAtual(carteira.getSaldoAtual());

        // Adicione a lógica para mapear as transações
        List<TransacaoResponseDto> transacoesDto = new ArrayList<>();
        for (Transacao transacao : carteira.getTransacoes()) {
            TransacaoResponseDto transacaoDto = new TransacaoResponseDto();
            transacaoDto.setTipo(transacao.getTipo());
            transacaoDto.setValor(transacao.getValor());
            transacoesDto.add(transacaoDto);
        }
        responseDto.setTransacoes(transacoesDto);

        return responseDto;
    }
//    public static CarteiraResponseDto of(Carteira carteira) {
//        CarteiraResponseDto carteiraResponseDto = new CarteiraResponseDto();
//
//        carteiraResponseDto.setSaldoTotal(carteira.getSaldoTotal());
//        carteiraResponseDto.setSaldoAtual(carteira.getSaldoAtual());
//
//        return carteiraResponseDto;
//    }

}
