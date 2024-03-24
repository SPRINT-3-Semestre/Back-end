package com.example.EditMatch.service.transacao;

import com.example.EditMatch.controller.transacao.dto.TransacaoCreateDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateSaqueDto;
import com.example.EditMatch.controller.transacao.mapper.TransacaoMapper;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import com.example.EditMatch.repository.CarteiraRepository;
import com.example.EditMatch.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    public Transacao novaTransacao(TransacaoCreateDto transacaoCreateDto, Integer usuarioId) {
        Carteira carteira = carteiraRepository.findByUsuarioId(usuarioId);

        Transacao transacao = TransacaoMapper.toTransacao(transacaoCreateDto, carteira);

        return transacaoRepository.save(transacao);
    }

    public Transacao saque(TransacaoCreateSaqueDto transacaoCreateSaqueDto, Integer usuarioId) {
        Carteira carteira = carteiraRepository.findByUsuarioId(usuarioId);

        Transacao transacao = TransacaoMapper.toSaque(transacaoCreateSaqueDto, carteira);

        return transacaoRepository.save(transacao);
    }

    public void exibirTodasTransacoes(Integer carteiraId) {
        List<Transacao> transacoes = transacaoRepository.findAll();
    }



}
