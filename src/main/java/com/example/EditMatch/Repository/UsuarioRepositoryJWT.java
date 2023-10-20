package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UsuarioRepositoryJWT extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

}