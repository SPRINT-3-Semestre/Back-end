package com.example.EditMatch.Service;

import com.example.EditMatch.Entity.ClientFinal;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.ClientFinalRepository;
import com.example.EditMatch.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientFinalService {

    private final ClientFinalRepository clientFinalRepository;
    private final UserRepository userRepository;

    public List<Usuario> list() {
        List<Usuario> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return users;
    }

    public ClientFinal register(ClientFinal clientFinal) {
        boolean isEmail = clientFinalRepository.existsByEmail(clientFinal.getEmail());
        if (isEmail) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        clientFinal.setIsEditor(!clientFinal.getIsEditor());

        return userRepository.save(clientFinal);
    }

    public Usuario update(Integer id, ClientFinal clientFinal) {
        clientFinal.setId(id);
        if(this.userRepository.existsById(id)){
            return this.userRepository.save(clientFinal);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delete(Integer id) {
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
