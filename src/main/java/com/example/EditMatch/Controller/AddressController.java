package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Address;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.AddressRepository;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.Service.AddressService;
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
public class AddressController {

    private final AddressService addressService;

    @CrossOrigin
    @PostMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar endereco", notes = "Retorna o endereco cadastrado")
    public ResponseEntity<Address> register(@RequestBody Address address, @PathVariable Integer id) {
        Address register = addressService.register(address, id);
        return ResponseEntity.created(null).body(register);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Atualizar endereco", notes = "Retorna o endereco atualizado")
    public ResponseEntity<Address> update(@RequestBody Address newAddress, @PathVariable Integer id) {
        Address update = addressService.update(newAddress, id);
        return ResponseEntity.ok().body(update);
    }
}
