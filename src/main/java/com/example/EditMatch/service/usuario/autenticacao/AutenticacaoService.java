package com.example.EditMatch.service.usuario.autenticacao;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Repository.cliente.ClienteFinalRepositoryJWT;
import com.example.EditMatch.Repository.editor.EditorRepositoryJWT;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private EditorRepositoryJWT editorRepository;
    @Autowired
    private ClienteFinalRepositoryJWT clienteFinalRepository;

    // Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Editor> editorOpt = editorRepository.findByEmail(username);
        Optional<ClienteFinal> clienteOpt = clienteFinalRepository.findByEmail(username);

        if (editorOpt.isEmpty() && clienteOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }

        if (editorOpt.isPresent()) {
            return new UsuarioDetalhesDto(editorOpt.get());
        } else {
            return new UsuarioDetalhesDto(clienteOpt.get());
        }
    }
}
