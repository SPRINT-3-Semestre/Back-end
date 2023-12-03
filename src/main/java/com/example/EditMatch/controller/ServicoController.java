package com.example.EditMatch.controller;

import com.example.EditMatch.entity.Servico;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.ServicoRepository;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.ServicoService;

import com.example.EditMatch.entity.Servico;
import com.example.EditMatch.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EditMatch.controller.usuario.dto.CustomServiceInfo;
import com.example.EditMatch.controller.usuario.dto.CustomServiceEditor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> create(@RequestBody Servico servico) {
        Servico service = servicoService.create(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(service);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServiceById(@PathVariable Integer id) {
        Servico serviceByid = servicoService.getServiceByid(id);
        return ResponseEntity.ok().body(serviceByid);
    }

    @GetMapping("/listar-services/{id}")
    public ResponseEntity<Stack<Servico>> list(@PathVariable Integer id) {
        Stack<Servico> servicos = servicoService.listServices(id);
        return ResponseEntity.ok().body(servicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable Integer id, @RequestBody Servico updatedService) {
        Servico servicoUpdated = servicoService.updateService(id, updatedService);
        return ResponseEntity.ok().body(servicoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        servicoService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint para criar pedoido de um serviço
    @PostMapping("/pedido/{idCliente}")
    public ResponseEntity<Servico> createPedido(@PathVariable Integer idCliente, @RequestBody Servico servico) {
        Servico pedido = servicoService.createPedido(idCliente, servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    // Endpoint para Editor aceitar um serviço
    @PutMapping("/fazer-proposta/{idServico}/{idEditor}")
    public ResponseEntity<CustomServiceInfo> aceitarServico(@PathVariable Integer idServico, @PathVariable Integer idEditor, @RequestParam Double valor) {
        CustomServiceInfo customServiceInfo = servicoService.aceitarServico(idServico, idEditor, valor);
        return ResponseEntity.ok(customServiceInfo);
    }

    @PutMapping("/cancelar-proposta/{idServico}")
    public ResponseEntity<Servico> cancelarProposta(@PathVariable Integer idServico) {
        Servico servico = servicoService.cancelarProposta(idServico);
        return ResponseEntity.ok(servico);
    }

    @PutMapping("/finalizar-servico/{idServico}")
    public ResponseEntity<Servico> finalizarServico(@PathVariable Integer idServico) {
        Servico servico = servicoService.finalizarServico(idServico);
        return ResponseEntity.ok(servico);
    }

    // Endpoint para listar serviços não contratados
    @GetMapping("/listar-nao-contratados")
    public ResponseEntity<List<Servico>> listarServicosNaoContratados() {
        List<Servico> servicos = servicoService.listarServicosNaoContratados();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/empilhar-services/{id}")
    public ResponseEntity<List<CustomServiceInfo>> listarServices(@PathVariable Integer id) {
        List<CustomServiceInfo> servicesList = servicoService.empilharServices(id);
        return ResponseEntity.ok(servicesList);
    }

    @GetMapping("/listar-servicos-editor/{idEditor}")
    public ResponseEntity<List<CustomServiceEditor>> listarServicosParaEditor(@PathVariable Integer idEditor) {
        List<CustomServiceEditor> servicesList = servicoService.listarServicosParaEditor(idEditor);
        return ResponseEntity.ok(servicesList);
    }

}
