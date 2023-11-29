package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.ClientFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.Service.ClientFinalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@Api(value = "ClienteFinalController", description = "Controladora de cliente final")
public class ClientFinalController {

    private final ClientFinalService clientFinalService;

    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "Lista cliente", notes = "Retorna todos os clientes listados")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> users = clientFinalService.list();
        return ResponseEntity.status(200).body(users);
    }

    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar cliente final", notes = "Retorna o cliente cadastrado")
    public ResponseEntity<ClientFinal> register(@RequestBody ClientFinal clientFinal) {
        clientFinalService.register(clientFinal);
        return ResponseEntity.created(null).body(clientFinal);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuario atualizado")
    public ResponseEntity<Usuario> update(@PathVariable int id, @RequestBody ClientFinal clientFinal){
        Usuario usuario = clientFinalService.update(id, clientFinal);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuario", notes = "Retorna 200 code caso de certo")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        clientFinalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alerta")
    @ApiOperation(value = "Avisa cliente", notes = "Retorna uma data")
    public String alert(ClientFinal cliente){
        return cliente.alertarPrazo();
    }

    public boolean isClientValid(ClientFinal clientFinal){
        return clientFinal.getNome().length() >= 3 && !clientFinal.getNome().isBlank()
                && clientFinal.getPassword().length() >= 3 && !clientFinal.getPassword().isBlank()
                && clientFinal.getEmail().length() >= 3 && !clientFinal.getEmail().isBlank();
    }
}

