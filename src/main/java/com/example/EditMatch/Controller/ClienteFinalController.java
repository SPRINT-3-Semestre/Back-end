package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.ClienteFinalRepository;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.service.usuario.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@Api(value = "ClienteFinalController", description = "Controladora de cliente final")
public class ClienteFinalController {

    private final UserRepository userRepository;
    private final ClienteFinalRepository clienteFinalRepository;
    private final UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "Lista cliente", notes = "Retorna todos os clientes listados")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(users);
    }

    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar cliente final", notes = "Retorna o editor cadastrado")
    public ResponseEntity<ClienteFinal> cadastrar(@RequestBody ClienteFinal clienteFinal) {
        boolean isEmail = clienteFinalRepository.existsByEmail(clienteFinal.getEmail());
        if (!isEmail) {
            return ResponseEntity.status(400).build();
        }

        clienteFinal.setIsEditor(!clienteFinal.getIsEditor());

        this.usuarioService.cadastrar(clienteFinal);

        return ResponseEntity.status(200).body(clienteFinal);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuario atualizado")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody ClienteFinal clienteFinal){
        clienteFinal.setId(id);
        if(this.userRepository.existsById(id)){
            Usuario userAtualizado = this.userRepository.save(clienteFinal);
            return ResponseEntity.status(200).body(userAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuario", notes = "Retorna 200 code caso de certo")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    @ApiOperation(value = "Avisa cliente", notes = "Retorna uma data")
    public String alerta(ClienteFinal cliente){
        return cliente.alertarPrazo();
    }

    public boolean isClienteValido(ClienteFinal clienteFinal){
        if(clienteFinal.getNome().length() < 3 || clienteFinal.getNome().isBlank()
                || clienteFinal.getPassword().length() < 3 || clienteFinal.getPassword().isBlank()
                || clienteFinal.getEmail().length() < 3 || clienteFinal.getEmail().isBlank()){
            return false;
        }
        return true;
    }
}

