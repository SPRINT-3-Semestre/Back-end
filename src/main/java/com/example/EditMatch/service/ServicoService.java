package com.example.EditMatch.service;

import com.example.EditMatch.entity.Servico;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.ServicoRepository;
import com.example.EditMatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

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

}

