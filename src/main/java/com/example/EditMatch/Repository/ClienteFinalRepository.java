package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.ClienteFinal;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteFinalRepository extends JpaRepository<ClienteFinal, Integer> {
    boolean existsByEmail(@Email(message = "O formato do e-mail é inválido") String email);
}
