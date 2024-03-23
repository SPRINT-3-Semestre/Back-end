package com.example.EditMatch.controller.transacao;


import com.example.EditMatch.controller.transacao.dto.SaqueResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoSaqueDto;
import com.example.EditMatch.controller.transacao.mapper.TransacaoMapper;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import com.example.EditMatch.service.carteira.CarteiraService;
import com.example.EditMatch.service.transacao.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CarteiraService carteiraService;

    @PostMapping("/{id}")
    public ResponseEntity<TransacaoResponseDto> novaTransacao(@RequestBody TransacaoCreateDto transacaoCreateDto, @PathVariable Integer id) {

        Transacao add = transacaoService.novaTransacao(transacaoCreateDto, id);

        TransacaoResponseDto transacaoResponseDto = TransacaoMapper.to(add);

        Carteira carteira = carteiraService.findByIdUsuario(id);
        carteiraService.AtualizarSaldo(carteira, transacaoCreateDto.getValor());

        return ResponseEntity.ok(transacaoResponseDto);
    }

    @PostMapping("/sacar/{id}")
    public ResponseEntity<SaqueResponseDto> sacar(@RequestBody TransacaoSaqueDto transacaoSaqueDto, @PathVariable Integer id) {

        Carteira carteira = carteiraService.findByIdUsuario(id);
        Carteira saque = carteiraService.sacar(carteira, transacaoSaqueDto.getValor());


        SaqueResponseDto responseDto = new SaqueResponseDto(transacaoSaqueDto.getValor(), transacaoSaqueDto.getMessage());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
    }
//    @GetMapping("/listar/{id}")
//    public String listarTransacoes(Integer id) {
//    }
}
