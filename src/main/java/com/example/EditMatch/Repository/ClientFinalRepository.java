package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.ClientFinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientFinalRepository extends JpaRepository<ClientFinal, Integer> {
    boolean existsByEmail(String email);
}
