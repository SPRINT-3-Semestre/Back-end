package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteFinalController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody ClienteFinal cliente) {
        if (isClienteValido(cliente)) {
            Usuario usuarioSalvo = this.usuarioRepository.save(cliente);
            return ResponseEntity.status(201).body(usuarioSalvo);
        }
        return ResponseEntity.status(400).build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody ClienteFinal clienteFinal){
        clienteFinal.setId(id);
        if(this.usuarioRepository.existsById(id)){
            Usuario usuarioAtualizado = this.usuarioRepository.save(clienteFinal);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        if(this.usuarioRepository.existsById(id)){
            this.usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    public String alerta(ClienteFinal cliente){
        return cliente.alertarPrazo();
    }
    public boolean isClienteValido(ClienteFinal clienteFinal){
        if(clienteFinal.getNome().length() < 3 || clienteFinal.getNome().isBlank()
                || clienteFinal.getSenha().length() < 3 || clienteFinal.getSenha().isBlank()
                || clienteFinal.getEmail().length() < 3 || clienteFinal.getEmail().isBlank()){
            return false;
        }
        return true;
    }
}
