package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Servico;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.ServiceRepository;
import com.example.EditMatch.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<Servico> createService(@RequestBody Servico servico) {
        // Verifica se o usuário é um editor
        if (servico.getUsuarioEditor() != null && servico.getUsuarioEditor().getId() != null) {
            Usuario editor = usuarioRepository.findByIdAndIsEditorTrue(servico.getUsuarioEditor().getId());
            if (editor == null) {
                return ResponseEntity.badRequest().body(null); // Editor não encontrado
            }
            servico.setUsuarioEditor(editor);
        }

        // Verifica se o usuário é um cliente
        if (servico.getUsuarioCliente() != null && servico.getUsuarioCliente().getId() != null) {
            Usuario cliente = usuarioRepository.findByIdAndIsEditorFalse(servico.getUsuarioCliente().getId());
            if (cliente == null) {
                return ResponseEntity.badRequest().body(null); // Cliente não encontrado
            }
            servico.setUsuarioCliente(cliente);
        }

        Servico createdServico = serviceRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServico);
    }
    // Endpoint para buscar um serviço pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServiceById(@PathVariable Integer id) {
        Optional<Servico> serviceOptional = serviceRepository.findById(id);
        return serviceOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/listar-services/{id}")
    public ResponseEntity<Stack<Servico>> listarServices(@PathVariable Integer id) {
        List<Servico> servicesList = this.serviceRepository.findByUsuarioClienteId(id);
        Stack<Servico> servicesStack = new Stack<>();
        if (!servicesList.isEmpty()) {
            // Adiciona os serviços à pilha
            servicesStack.addAll(servicesList);
            return new ResponseEntity<>(servicesStack, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint para atualizar um serviço
    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateService(@PathVariable Integer id, @RequestBody Servico updatedService) {
        return serviceRepository.findById(id)
                .map(existingService -> {
                    existingService.setTitle(updatedService.getTitle());
                    existingService.setDesc(updatedService.getDesc());
                    existingService.setValor(updatedService.getValor());
                    // Atualize outros campos conforme necessário

                    Servico savedService = serviceRepository.save(existingService);
                    return ResponseEntity.ok(savedService);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para excluir um serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
