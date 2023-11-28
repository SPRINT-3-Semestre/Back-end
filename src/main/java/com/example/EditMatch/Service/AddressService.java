package com.example.EditMatch.Service;

import com.example.EditMatch.Entity.Address;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.AddressRepository;
import com.example.EditMatch.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Address register(Address address, Integer id) {
        Optional<Usuario> usuario = userRepository.findById(id);

        if(usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Usuario user = usuario.get();

        user.setAddress(address);

        Address save = this.addressRepository.save(address);

        return save;
    }

    public Address update(Address newAddress, Integer id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        if (usuario.getAddress() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Address addressAtual = usuario.getAddress();

        addressAtual.setCidade(newAddress.getCidade());
        addressAtual.setLogradouro(newAddress.getLogradouro());
        addressAtual.setComplemento(newAddress.getComplemento());
        addressAtual.setEstado(newAddress.getEstado());
        addressAtual.setBairro(newAddress.getBairro());
        addressAtual.setNumero(newAddress.getNumero());
        addressAtual.setCep(newAddress.getCep());

        return addressRepository.save(addressAtual);
    }

}
