package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Endereco;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.EnderecoRepository;
import com.example.EditMatch.Repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
@Api(value = "EnderecoController", description = "Controladora de endereco")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;
    private final UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar endereco", notes = "Retorna o endereco cadastrado")
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco , @PathVariable Integer id) {
        Optional<Usuario> usuario = userRepository.findById(id);

        if(usuario.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        Usuario usuario1 = usuario.get();

        usuario1.setEndereco(endereco);

        Endereco save = this.enderecoRepository.save(endereco);

        return ResponseEntity.created(null).body(save);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Atualizar endereco", notes = "Retorna o endereco atualizado")
    public ResponseEntity<Endereco> atualizar(@RequestBody Endereco novoEndereco, @PathVariable Integer id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOptional.get();

        if (usuario.getEndereco() == null) {
            return ResponseEntity.notFound().build();
        }

        Endereco enderecoAtual = usuario.getEndereco();

        enderecoAtual.setCidade(novoEndereco.getCidade());
        enderecoAtual.setLogradouro(novoEndereco.getLogradouro());
        enderecoAtual.setComplemento(novoEndereco.getComplemento());
        enderecoAtual.setEstado(novoEndereco.getEstado());
        enderecoAtual.setBairro(novoEndereco.getBairro());
        enderecoAtual.setNumero(novoEndereco.getNumero());
        enderecoAtual.setCep(novoEndereco.getCep());

        Endereco enderecoAtualizado = enderecoRepository.save(enderecoAtual);

        return ResponseEntity.ok().body(enderecoAtualizado);
    }
}
