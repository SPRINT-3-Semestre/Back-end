package com.example.EditMatch.service.client;

import com.example.EditMatch.controller.client.dto.ClientCreateDto;
import com.example.EditMatch.controller.client.mapper.ClientMapper;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.client.exception.ClientException;
import com.example.EditMatch.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientFinalService {

    private final ClientFinalRepository clientFinalRepository;
    private final UserRepository userRepository;
    private final UsuarioService usuarioService;

    public List<Usuario> list() {
        List<Usuario> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            throw new ClientException("Nenhum usuário encontrado");
        }
        return users;
    }

    public ClientFinal register(ClientCreateDto clientCreateDto) {
        boolean isEmail = clientFinalRepository.existsByEmail(clientCreateDto.getEmail());
        if (isEmail) {
            throw new  ClientException("Email já cadastrado");
        }
        ClientFinal clientFinal = ClientMapper.of(clientCreateDto);
        usuarioService.cadastrar(clientFinal);
        return userRepository.save(clientFinal);
    }

    public Usuario update(Integer id, ClientFinal clientFinal) {
        clientFinal.setId(id);
        if(this.userRepository.existsById(id)){
            return this.userRepository.save(clientFinal);
        }

        throw new  ClientException("Nenhum usuário encontrado");
    }

    public void delete(Integer id) {
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }

        throw new  ClientException("Nenhum usuário encontrado");
    }
}
