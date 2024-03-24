package com.example.EditMatch.controller.carteira;

import com.example.EditMatch.controller.carteira.dto.CarteiraResponseDto;
import com.example.EditMatch.controller.carteira.mapper.CarteiraMapper;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.controller.transacao.mapper.TransacaoMapper;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.carteira.CarteiraService;
import com.example.EditMatch.service.editor.EditorService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CarteiraResponseDto> findByIdUsuario(@PathVariable Integer idUsuario) {
        Carteira carteira = carteiraService.findByIdUsuario(idUsuario);

        // Mapear as transações para o DTO TransacaoResponseDto
        List<TransacaoResponseDto> transacoesDto = new ArrayList<>();
        for (Transacao transacao : carteira.getTransacoes()) {
            TransacaoResponseDto transacaoDto = TransacaoMapper.to(transacao);
            transacoesDto.add(transacaoDto);
        }

        // Mapear a carteira para o DTO CarteiraResponseDto
        CarteiraMapper carteiraMapper = new CarteiraMapper();
        CarteiraResponseDto carteiraResponseDto = carteiraMapper.of(carteira);
        carteiraResponseDto.setTransacoes(transacoesDto);

        try {
            return ResponseEntity.ok(carteiraResponseDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

