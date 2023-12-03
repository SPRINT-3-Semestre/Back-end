package com.example.EditMatch.service;

import com.example.EditMatch.excepition.EntidadeNaoEncontradaException;
import com.example.EditMatch.entity.Servico;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.ServicoRepository;
import com.example.EditMatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.EditMatch.controller.usuario.dto.CustomServiceInfo;
import com.example.EditMatch.controller.usuario.dto.CustomServiceEditor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import com.example.EditMatch.Service.generic.FilaObj;
import com.example.EditMatch.Service.generic.PilhaObj;
@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final UserRepository usuarioRepository;

    public Servico create(Servico service) {
        if (service.getUsuarioEditor() != null && service.getUsuarioEditor().getId() != null) {
            Usuario editor = usuarioRepository.findByIdAndIsEditorTrue(service.getUsuarioEditor().getId());
            if (editor == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            service.setUsuarioEditor(editor);
        }

        if (service.getUsuarioCliente() != null && service.getUsuarioCliente().getId() != null) {
            Usuario cliente = usuarioRepository.findByIdAndIsEditorFalse(service.getUsuarioCliente().getId());
            if (cliente == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            service.setUsuarioCliente(cliente);
        }

        return servicoRepository.save(service);
    }

    public Servico getServiceByid(Integer id) {
        Optional<Servico> serviceOptional = servicoRepository.findById(id);

        if(serviceOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return serviceOptional.get();
    }

    public Stack<Servico> listServices(Integer id) {
        List<Servico> servicesList = this.servicoRepository.findByUsuarioClienteId(id);
        Stack<Servico> servicesStack = new Stack<>();
        if (servicesList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        servicesStack.addAll(servicesList);
        return servicesStack;
    }

    public Servico updateService(Integer id, Servico service) {
        Optional<Servico> isServico = servicoRepository.findById(id);

        if(isServico.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Servico updatedService = isServico.get();

        updatedService.setTitle(service.getTitle());
        updatedService.setDesc(service.getDesc());
        updatedService.setValor(service.getValor());

        return servicoRepository.save(updatedService);
    }

    public void delete(Integer id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Servico createPedido(Integer idCliente, Servico servico) {
        Usuario cliente = usuarioRepository.findByIdAndIsEditorFalse(idCliente);
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        servico.setUsuarioCliente(cliente);

        return servicoRepository.save(servico);

    }

    public CustomServiceInfo aceitarServico(Integer idServico, Integer idEditor, Double valor) {
        Servico servico = servicoRepository.findById(idServico).orElse(null);
        if (servico == null) {
            throw new EntidadeNaoEncontradaException("O id do serviço não foi encontrado");
        }
        Usuario editor = usuarioRepository.findByIdAndIsEditorTrue(idEditor);
        if (editor == null) {
            throw new EntidadeNaoEncontradaException("O id do editor não foi encontrado");
        }
        servico.setUsuarioEditor(editor);
        servico.setValor(valor);
        Servico updatedServico = servicoRepository.save(servico);
        CustomServiceInfo customServiceInfo = new CustomServiceInfo(updatedServico.getId(), updatedServico.getUsuarioCliente().getId(), updatedServico.getUsuarioCliente().getNome(), updatedServico.getUsuarioEditor().getId(), updatedServico.getUsuarioEditor().getNome(), updatedServico.getTitle(), updatedServico.getDesc(), updatedServico.getValor());
        return customServiceInfo;
    }

    public Servico cancelarProposta(Integer idServico) {
        Servico servico = servicoRepository.findById(idServico).orElse(null);
        if (servico == null) {
            throw new EntidadeNaoEncontradaException("O id do serviço não foi encontrado");
        }
        servico.setUsuarioEditor(null);
        Servico updatedServico;
        updatedServico = servicoRepository.save(servico);
        return updatedServico;
    }

    public Servico finalizarServico(Integer idServico) {
        Servico servico = servicoRepository.findById(idServico).orElse(null);
        if (servico == null) {
            throw new EntidadeNaoEncontradaException("O id do serviço não foi encontrado");
        }
        servico.setFinishedAt(LocalDate.now());
        Servico updatedServico = servicoRepository.save(servico);
        return updatedServico;
    }

    public List<Servico> listarServicosNaoContratados() {
        List<Servico> servicos = this.servicoRepository.findByUsuarioEditorIsNull();
        if (servicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Não há serviços não contratados");
        }
        return servicos;
    }

    public List<CustomServiceInfo> empilharServices(Integer id) {
        List<Servico> servicesList = this.servicoRepository.findByUsuarioClienteId(id);
        PilhaObj<CustomServiceInfo> servicesPilha = new PilhaObj<>(servicesList.size());
        List<CustomServiceInfo> customServiceList = new ArrayList<>();
        Double valorTotal = 0.0;
        if (!servicesList.isEmpty()) {
            // Armazena na pilha
            for (Servico servico : servicesList) {
                if (servico.getUsuarioEditor() == null) continue; // Ignora serviços não contratados (sem editor
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
            return customServiceList;
        } else {
            throw new EntidadeNaoEncontradaException("Não há serviços para este usuário");
        }
    }

    public List<CustomServiceEditor> listarServicosParaEditor(Integer idEditor) {
        List<Servico> servicesList = this.servicoRepository.findByUsuarioEditorId(idEditor);
        FilaObj<CustomServiceEditor> servicesFila = new FilaObj<>(servicesList.size());
        List<CustomServiceEditor> customServiceList = new ArrayList<>();

        if (!servicesList.isEmpty()) {
            // Adiciona os serviços à fila
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

                servicesFila.insert(customInfo);
            }

            // Converte a fila para a lista
            while (!servicesFila.isEmpty()) {
                customServiceList.add(servicesFila.poll());
            }
            return customServiceList;
        } else {
            throw new EntidadeNaoEncontradaException("Não há serviços para este editor");
        }
    }

}

