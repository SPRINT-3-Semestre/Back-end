package com.example.EditMatch.repository;

import com.example.EditMatch.entity.ClientFinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientFinalRepository extends JpaRepository<ClientFinal, Integer> {
    boolean existsByEmail(String email);
}
