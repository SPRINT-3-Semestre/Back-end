package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Portifolio;
import com.example.EditMatch.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portifolio, Integer> {
    Optional<Portifolio> findByEditor(Usuario editor);
}
