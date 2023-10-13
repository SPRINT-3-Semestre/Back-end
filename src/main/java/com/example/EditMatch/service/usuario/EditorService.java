package com.example.EditMatch.service.usuario;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Repository.editor.EditorRepositoryJWT;
import com.example.EditMatch.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.EditMatch.service.usuario.autenticacao.dto.editor.EditorLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.editor.EditorTokenDto;
import com.example.EditMatch.service.usuario.dto.editor.EditorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EditorService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EditorRepositoryJWT editorRepositoryJwt;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void cadastrar(Editor editor) {
        final Editor novoEditor = editor;

        String senhaCriptografada = passwordEncoder.encode(novoEditor.getPassword());
        novoEditor.setPassword(senhaCriptografada);

        this.editorRepositoryJwt.save(novoEditor);
    }

    public EditorTokenDto autenticar(EditorLoginDto editorLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                editorLoginDto.getEmail(), editorLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Editor editorAutenticado =
                editorRepositoryJwt.findByEmail(editorLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return EditorMapper.of(editorAutenticado, token);
    }
}
