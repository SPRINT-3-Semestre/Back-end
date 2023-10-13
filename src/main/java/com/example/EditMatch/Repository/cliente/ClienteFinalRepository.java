package com.example.EditMatch.Repository.cliente;

import com.example.EditMatch.Entity.ClienteFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteFinalRepository extends JpaRepository<ClienteFinal, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    ClienteFinal findByEmail(@Param("email") String email);
}
