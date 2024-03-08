package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Optional<Portfolio> findByEditor(Usuario editor);

    Portfolio findByEditorId(Integer id);
}
