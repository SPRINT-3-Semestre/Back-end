package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.cliente.ClienteFinalRepository;
import com.example.EditMatch.service.usuario.ClienteFinalService;
import com.example.EditMatch.service.usuario.autenticacao.dto.cliente.ClienteFinalLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.cliente.ClienteFinalTokenDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteFinalController {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private ClienteFinalRepository clienteRepository;

    private ClienteFinalService clienteFinalService;

    public ClienteFinalController(ClienteFinalService clienteFinalService) {
        this.clienteFinalService = clienteFinalService;
    }

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @GetMapping()
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteFinal>> listar() {
        // Obtém a lista de todos os usuários do repositório
        List<ClienteFinal> usuarios = this.clienteRepository.findAll();
        if (usuarios.isEmpty()) {
            // Retorna uma resposta com status 404 (Not Found) se a lista estiver vazia
            return ResponseEntity.status(204).build();
        }
        // Retorna a lista de usuários com status 200 (OK)
        return ResponseEntity.status(200).body(usuarios);
    }

    // Configuração para permitir solicitações de diferentes origens (CORS)
    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> cadastrar(@RequestBody ClienteFinal cliente) {
        Usuario usuarioExistente = clienteRepository.findByEmail(cliente.getEmail());
        // Verifica se o cliente fornecido é válido usando o método isClienteValido
        if (isClienteValido(cliente)) {
            if (usuarioExistente != null) {
                // Retorna uma resposta HTTP 400 (Bad Request) se o email já estiver em uso
                return ResponseEntity.status(400).build();
            }
            // Salva o cliente no repositório e retorna uma resposta com status 201 (Created)
            this.clienteFinalService.cadastrar(cliente);
            return ResponseEntity.status(201).body(cliente);
        }
        // Retorna uma resposta com status 400 (Bad Request) se o cliente não for válido
        return ResponseEntity.status(400).build();
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<ClienteFinalTokenDto> login(@RequestBody ClienteFinalLoginDto usuarioLoginDto) {
        ClienteFinalTokenDto clienteTokenDto = this.clienteFinalService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(clienteTokenDto);
    }

    @CrossOrigin // Configuração para permitir solicitações de diferentes origens (CORS)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody ClienteFinal clienteFinal) {
        // Define o ID do clienteFinal com base no parâmetro da URL
        clienteFinal.setId(id);
        if (this.clienteRepository.existsById(id)) {
            // Atualiza o cliente no repositório e retorna uma resposta com status 200 (OK)
            this.clienteFinalService.atualizar(id, clienteFinal);
            return ResponseEntity.status(200).body(clienteFinal);
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable int id) {
        if (this.clienteRepository.existsById(id)) {
            // Exclui o cliente do repositório e retorna uma resposta com status 200 (OK)
            this.clienteRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        // Retorna uma resposta com status 404 (Not Found) se o cliente não existe no repositório
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    public String alerta(ClienteFinal cliente) {
        // Chama o método alertarPrazo do cliente e retorna a mensagem resultante como uma String
        return cliente.alertarPrazo();
    }

    // Verifica se o cliente é válido com base em critérios de validação
    public boolean isClienteValido(ClienteFinal clienteFinal) {
        if (clienteFinal.getNome().length() < 3 || clienteFinal.getNome().isBlank()
                || clienteFinal.getPassword().length() < 3 || clienteFinal.getPassword().isBlank()
                || clienteFinal.getEmail().length() < 3 || clienteFinal.getEmail().isBlank()) {
            return false;
        }
        return true;
    }
}

