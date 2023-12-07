package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorRepository extends JpaRepository<Editor, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Editor findByEmail(@Param("email") String email);
}
