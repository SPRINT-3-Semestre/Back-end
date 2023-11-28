package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByUsuarioClienteId(Integer id);
}
