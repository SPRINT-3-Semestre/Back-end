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

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        // Obtém a lista de todos os usuários do repositório
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            // Retorna uma resposta com status 404 (Not Found) se a lista estiver vazia
            return ResponseEntity.status(404).build();
        }
        // Retorna a lista de usuários com status 200 (OK)
        return ResponseEntity.status(200).body(usuarios);
    }

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody ClienteFinal cliente) {
        // Verifica se o cliente fornecido é válido usando o método isClienteValido
        if (isClienteValido(cliente)) {
            // Salva o cliente no repositório e retorna uma resposta com status 201 (Created)
            Usuario usuarioSalvo = this.usuarioRepository.save(cliente);
            return ResponseEntity.status(201).body(usuarioSalvo);
        }
        // Retorna uma resposta com status 400 (Bad Request) se o cliente não for válido
        return ResponseEntity.status(400).build();
    }

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody ClienteFinal clienteFinal){
        // Define o ID do clienteFinal com base no parâmetro da URL
        clienteFinal.setId(id);
        if(this.usuarioRepository.existsById(id)){
            // Atualiza o cliente no repositório e retorna uma resposta com status 200 (OK)
            Usuario usuarioAtualizado = this.usuarioRepository.save(clienteFinal);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        if(this.usuarioRepository.existsById(id)){
            // Exclui o cliente do repositório e retorna uma resposta com status 200 (OK)
            this.usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    public String alerta(ClienteFinal cliente){
        // Chama o método alertarPrazo do cliente e retorna a mensagem resultante como uma String
        return cliente.alertarPrazo();
    }

    // Verifica se o cliente é válido com base em critérios de validação
    public boolean isClienteValido(ClienteFinal clienteFinal){
        if(clienteFinal.getNome().length() < 3 || clienteFinal.getNome().isBlank()
                || clienteFinal.getPassword().length() < 3 || clienteFinal.getPassword().isBlank()
                || clienteFinal.getEmail().length() < 3 || clienteFinal.getEmail().isBlank()){
            return false;
        }
        return true;
    }
}

