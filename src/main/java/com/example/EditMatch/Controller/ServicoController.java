package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Servico;
import com.example.EditMatch.Service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
