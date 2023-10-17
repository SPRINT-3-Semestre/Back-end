package com.example.EditMatch.Repository.cliente;

import com.example.EditMatch.Entity.ClienteFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteFinalRepositoryJWT extends JpaRepository<ClienteFinal, Long> {

    Optional<ClienteFinal> findByEmail(String email);

}