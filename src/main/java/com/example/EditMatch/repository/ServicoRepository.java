package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByUsuarioClienteId(Integer id);
}
