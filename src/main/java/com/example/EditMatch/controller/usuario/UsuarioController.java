package com.example.EditMatch.controller.usuario;

import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.usuario.ListaObj;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@Api(value = "UsuarioController", description = "Controladora de usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;
    private final UsuarioService usuarioService;
    private ListaObj<Usuario> listaUsuario;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        listaUsuario = new ListaObj<>(100);
    }

    @GetMapping("/listar-editor")
    public ResponseEntity<List<Usuario>> listarEditores() {
        List<Usuario> editores = this.userRepository.findByIsEditorTrue();

        if (!editores.isEmpty()) {
            return new ResponseEntity<>(editores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listar-clientes")
    public ResponseEntity<List<Usuario>> listarClientes() {
        List<Usuario> clientes = this.userRepository.findByIsEditorFalse();

        if (!clientes.isEmpty()) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
            File csvFile = usuarioService.gravarArquivoCsv("Usuarios");

            FileInputStream fileInputStream = new FileInputStream(csvFile);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Usuarios.csv");

            InputStreamResource resource = new InputStreamResource(fileInputStream);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @CrossOrigin
    @GetMapping("/download/txt")
    @ApiOperation(value = "Baixa TXT", notes = "baixar TXT")
    public ResponseEntity<InputStreamResource> downloadTxt(){
        try {
            List<Usuario> usuarios = this.userRepository.findAll();
            File txtFile = usuarioService.gravaArquivoTxt(usuarios, "Usuarios");

            FileInputStream fileInputStream = new FileInputStream(txtFile);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Usuarios.txt");

            InputStreamResource resource = new InputStreamResource(fileInputStream);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/txt"))
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
