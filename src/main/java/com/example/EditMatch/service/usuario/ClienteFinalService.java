package com.example.EditMatch.service.usuario;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Repository.cliente.ClienteFinalRepositoryJWT;
import com.example.EditMatch.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.EditMatch.service.usuario.autenticacao.dto.cliente.ClienteFinalLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.cliente.ClienteFinalTokenDto;
import com.example.EditMatch.service.usuario.dto.cliente.ClienteFinalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteFinalService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteFinalRepositoryJWT clienteFinalRepositoryJwt;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void cadastrar(ClienteFinal cliente) {
        final ClienteFinal novoCliente = cliente;

        String senhaCriptografada = passwordEncoder.encode(novoCliente.getPassword());
        novoCliente.setPassword(senhaCriptografada);

        this.clienteFinalRepositoryJwt.save(novoCliente);
    }
    public ClienteFinalTokenDto autenticar(ClienteFinalLoginDto clienteLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                clienteLoginDto.getEmail(), clienteLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        ClienteFinal clienteAutenticado =
                clienteFinalRepositoryJwt.findByEmail(clienteLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return ClienteFinalMapper.of(clienteAutenticado, token);
    }

    public void atualizar(int id, ClienteFinal clienteFinal) {
        String senhaCriptografada = passwordEncoder.encode(clienteFinal.getPassword());
        clienteFinal.setPassword(senhaCriptografada);

        this.clienteFinalRepositoryJwt.save(clienteFinal);
    }
}
