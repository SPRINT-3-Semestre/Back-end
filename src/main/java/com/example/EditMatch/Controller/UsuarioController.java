package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/usuarios")
@Api(value = "UsuarioController", description = "Controladora de usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "Cadastra usuarios", notes = "Retorna o usuario cadastrado")
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
    @ApiOperation(value = "Login de usuarios", notes = "login de usuarios")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto editorTokenDto = this.usuarioService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(editorTokenDto);
    }
    @CrossOrigin
    @GetMapping("/download/csv")
    @ApiOperation(value = "Baixa CSV", notes = "baixar CSV")
    public ResponseEntity<InputStreamResource> downloadCsv(){
        try {
            // Chama o método para gerar o arquivo CSV com base no nome fornecido
            File csvFile = usuarioService.gravarArquivoCsv("Usuarios");

            // Converte o arquivo em um fluxo de entrada
            FileInputStream fileInputStream = new FileInputStream(csvFile);

            // Configura os cabeçalhos da resposta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Usuarios.csv");

            // Cria um recurso de fluxo de entrada a partir do arquivo
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            // Retorna o ResponseEntity com os cabeçalhos e o recurso de fluxo de entrada
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);

        } catch (IOException e) {
            // Lida com exceções de IO, por exemplo, arquivo não encontrado
            e.printStackTrace();
            // Retorna uma resposta de erro, por exemplo, status 500
            return ResponseEntity.status(500).body(null);
        }
    }
}
