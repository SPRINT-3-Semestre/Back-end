package com.example.EditMatch.controller.transacao;


import com.example.EditMatch.configuration.paymentApi.Auth;
import com.example.EditMatch.configuration.paymentApi.Pagamento;
import com.example.EditMatch.controller.transacao.dto.SaqueResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoResponseDto;
import com.example.EditMatch.controller.transacao.dto.TransacaoCreateSaqueDto;
import com.example.EditMatch.controller.transacao.mapper.TransacaoMapper;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.entity.transacao.Transacao;
import com.example.EditMatch.service.carteira.CarteiraService;
import com.example.EditMatch.service.transacao.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CarteiraService carteiraService;

    @PutMapping("/{id}")
    public ResponseEntity<?> novaTransacao(@RequestBody TransacaoCreateDto transacaoCreateDto, @PathVariable Integer id) {
        Auth auth = new Auth();
        String token = auth.geraToken();

        Pagamento pagamento = new Pagamento();

        StringBuilder respostaPagamento = pagamento.enviarPagamento(token, transacaoCreateDto.getNomePagante(), transacaoCreateDto.getValor());

        // Verifica se a resposta do pagamento não está vazia
        if (respostaPagamento != null && !respostaPagamento.isEmpty()) {

            Transacao add = transacaoService.novaTransacao(transacaoCreateDto, id);

            Carteira carteira = carteiraService.findByIdUsuario(id);
            carteiraService.AtualizarSaldo(carteira, transacaoCreateDto.getValor());

            return ResponseEntity.ok().body(respostaPagamento); // Retorna a resposta do pagamento
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar pagamento."); // Retorna uma resposta de erro se a resposta do pagamento estiver vazia
        }
    }

    @GetMapping("/qrcode/{locId}")
    public ResponseEntity<?> enviarQrCode(@PathVariable Integer locId) {
        Auth auth = new Auth();
        String token = auth.geraToken();

        Pagamento pagamento = new Pagamento();
        StringBuilder qrCode = pagamento.enviarQrCode(token, locId);

        if(qrCode == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao gerar QR Code");

        return ResponseEntity.ok().body(qrCode);
    }

    @PostMapping("/sacar/{id}")
    public ResponseEntity<SaqueResponseDto> sacar(@RequestBody TransacaoCreateSaqueDto transacaoCreateSaqueDto, @PathVariable Integer id) {

        Carteira carteira = carteiraService.findByIdUsuario(id);
        carteiraService.sacar(carteira, transacaoCreateSaqueDto.getValor());

        SaqueResponseDto responseDto = TransacaoMapper.saqueResponse(transacaoCreateSaqueDto);

        transacaoService.saque(transacaoCreateSaqueDto, id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
    }
//    @GetMapping("/listar/{id}")
//    public String listarTransacoes(Integer id) {
//    }
}
