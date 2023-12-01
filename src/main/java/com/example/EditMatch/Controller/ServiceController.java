package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Servico;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.ServiceRepository;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.service.usuario.dto.CustomServiceEditor;
import com.example.EditMatch.service.usuario.dto.CustomServiceInfo;
import com.example.EditMatch.service.generic.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Endpoint para criar pedoido de um serviço
    @PostMapping("/pedido/{idCliente}")
    public ResponseEntity<Servico> createServicoPedido(@PathVariable Integer idCliente, @RequestBody Servico servico){
        Usuario cliente = usuarioRepository.findByIdAndIsEditorFalse(idCliente);
        if (cliente == null) {
            return ResponseEntity.badRequest().build(); // Cliente não encontrado
        }
        servico.setUsuarioCliente(cliente);
        Servico createdServico = serviceRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServico);
    }

    // Endpoint para Editor aceitar um serviço
    @PutMapping("/fazer-proposta/{idServico}/{idEditor}")
    public ResponseEntity<CustomServiceInfo> aceitarServico(@PathVariable Integer idServico, @PathVariable Integer idEditor, @RequestParam Double valor) {
        Servico servico = serviceRepository.findById(idServico).orElse(null);
        if (servico == null) {
            return ResponseEntity.badRequest().body(null); // Serviço não encontrado
        }
        Usuario editor = usuarioRepository.findByIdAndIsEditorTrue(idEditor);
        if (editor == null) {
            return ResponseEntity.badRequest().body(null); // Editor não encontrado
        }
        servico.setUsuarioEditor(editor);
        servico.setValor(valor);
        Servico updatedServico = serviceRepository.save(servico);
        CustomServiceInfo customServiceInfo = new CustomServiceInfo(updatedServico.getId(), updatedServico.getUsuarioCliente().getId(), updatedServico.getUsuarioCliente().getNome(), updatedServico.getUsuarioEditor().getId(), updatedServico.getUsuarioEditor().getNome(), updatedServico.getTitle(), updatedServico.getDesc(), updatedServico.getValor());
        return ResponseEntity.ok(customServiceInfo);
    }

    @PutMapping("/cancelar-proposta/{idServico}")
    public ResponseEntity<Servico> cancelarProposta(@PathVariable Integer idServico) {
        Servico servico = serviceRepository.findById(idServico).orElse(null);
        if (servico == null) {
            return ResponseEntity.badRequest().body(null); // Serviço não encontrado
        }
        servico.setUsuarioEditor(null);
        Servico updatedServico = serviceRepository.save(servico);
        return ResponseEntity.ok(updatedServico);
    }

    @PutMapping("/finalizar-servico/{idServico}")
    public ResponseEntity<Servico> finalizarServico(@PathVariable Integer idServico) {
        Servico servico = serviceRepository.findById(idServico).orElse(null);
        if (servico == null) {
            return ResponseEntity.badRequest().body(null); // Serviço não encontrado
        }
        servico.setFinishedAt(LocalDate.now());
        Servico updatedServico = serviceRepository.save(servico);
        return ResponseEntity.ok(updatedServico);
    }

    // Endpoint para listar serviços não contratados
    @GetMapping("/listar-nao-contratados")
    public ResponseEntity<List<Servico>> listarServicosNaoContratados() {
        List<Servico> servicos = this.serviceRepository.findByUsuarioEditorIsNull();
        if (!servicos.isEmpty()) {
            return new ResponseEntity<>(servicos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar um serviço pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServiceById(@PathVariable Integer id) {
        Optional<Servico> serviceOptional = serviceRepository.findById(id);
        return serviceOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/empilhar-services/{id}")
    public ResponseEntity<List<CustomServiceInfo>> listarServices(@PathVariable Integer id) {
        List<Servico> servicesList = this.serviceRepository.findByUsuarioClienteId(id);
        PilhaObj<CustomServiceInfo> servicesPilha = new PilhaObj<>(servicesList.size());
        List<CustomServiceInfo> customServiceList = new ArrayList<>();
        Double valorTotal = 0.0;
        if (!servicesList.isEmpty()) {
            // Armazena na pilha
            for (Servico servico : servicesList) {
                if(servico.getUsuarioEditor() == null) continue; // Ignora serviços não contratados (sem editor
                Usuario cliente = servico.getUsuarioCliente();
                Usuario editor = servico.getUsuarioEditor();
                CustomServiceInfo customInfo = new CustomServiceInfo(servico.getId(), cliente.getId(), cliente.getNome(), editor.getId(), editor.getNome(), servico.getTitle(), servico.getDesc(), servico.getValor());
                servicesPilha.push(customInfo);
                valorTotal += servico.getValor();
            }

            // Transfere para a lista
            while (!servicesPilha.isEmpty()) {
                customServiceList.add(servicesPilha.pop());
            }
            // Atualiza o valor total para cada objeto na lista
            for (CustomServiceInfo customServiceInfo : customServiceList) {
                customServiceInfo.setValorTotal(valorTotal);
            }


            return ResponseEntity.ok(customServiceList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar-servicos-editor/{idEditor}")
    public ResponseEntity<List<CustomServiceEditor>> listarServicosParaEditor(@PathVariable Integer idEditor) {
        List<Servico> servicesList = this.serviceRepository.findByUsuarioEditorId(idEditor);
        List<CustomServiceEditor> customServiceList = new ArrayList<>();

        if (!servicesList.isEmpty()) {
            // Adiciona os serviços à lista de CustomServiceInfo
            for (Servico servico : servicesList) {
                Usuario cliente = servico.getUsuarioCliente();
                CustomServiceEditor customInfo = new CustomServiceEditor(
                        servico.getId(),
                        cliente.getId(),
                        cliente.getNome(),
                        servico.getTitle(),
                        servico.getDesc(),
                        servico.getValor()
                );

                customServiceList.add(customInfo);
            }

            return new ResponseEntity<>(customServiceList, HttpStatus.OK);
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
