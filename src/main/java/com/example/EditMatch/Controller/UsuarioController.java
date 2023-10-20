package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo email
        Usuario userExistente = userRepository.findByEmail(usuario.getEmail());
        if (userExistente != null) {
            // Retorna uma resposta HTTP 400 (Bad Request) se o email já estiver em uso
            return ResponseEntity.status(400).build();
        }

        // Salva o novo editor no banco de dados
        this.usuarioService.cadastrar(usuario);

        // Retorna uma resposta HTTP 200 (OK) com o novo editor no corpo da resposta
        return ResponseEntity.status(200).body(usuario);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto editorTokenDto = this.usuarioService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(editorTokenDto);
    }

}
