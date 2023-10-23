package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Api(value = "ClienteFinalController", description = "Controladora de cliente final")
public class ClienteFinalController {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @GetMapping
    @ApiOperation(value = "Lista cliente", notes = "Retorna todos os clientes listados")
    public ResponseEntity<List<Usuario>> listar() {
        // Obtém a lista de todos os usuários do repositório
        List<Usuario> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        // Retorna a lista de usuários com status 200 (OK)
        return ResponseEntity.status(200).body(users);
    }

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuario atualizado")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody ClienteFinal clienteFinal){
        // Define o ID do clienteFinal com base no parâmetro da URL
        clienteFinal.setId(id);
        if(this.userRepository.existsById(id)){
            // Atualiza o cliente no repositório e retorna uma resposta com status 200 (OK)
            Usuario userAtualizado = this.userRepository.save(clienteFinal);
            return ResponseEntity.status(200).body(userAtualizado);
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuario", notes = "Retorna 200 code caso de certo")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        if(this.userRepository.existsById(id)){
            // Exclui o cliente do repositório e retorna uma resposta com status 200 (OK)
            this.userRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    @ApiOperation(value = "Avisa cliente", notes = "Retorna uma data")
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

