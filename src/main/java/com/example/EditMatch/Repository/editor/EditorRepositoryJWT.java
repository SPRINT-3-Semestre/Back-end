package com.example.EditMatch.Repository.editor;

import com.example.EditMatch.Entity.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface EditorRepositoryJWT extends JpaRepository<Editor, Long> {

    Optional<Editor> findByEmail(String email);

}