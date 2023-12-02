package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UsuarioRepositoryJWT extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Integer countAllBy();
    Usuario findById(Integer id);
}