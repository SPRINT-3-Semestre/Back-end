package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);

    Usuario findBynome(String nome);

    List<Usuario> findByIsEditorTrue();
    List<Usuario> findByIsEditorFalse();

    Usuario findByIdAndIsEditorTrue(Integer idEditor);
    Usuario findByIdAndIsEditorFalse(Integer idEditor);

}
