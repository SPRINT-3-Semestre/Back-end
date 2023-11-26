package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Servico, Integer> {

    @Query("SELECT s FROM Servico s WHERE s.usuarioCliente.id = :clienteId AND s.usuarioCliente.isEditor = false")
    List<Servico> findByUsuarioClienteId(@Param("clienteId") Integer clienteId);
    @Query("SELECT s FROM Servico s WHERE s.usuarioEditor.id = :clienteId AND s.usuarioEditor.isEditor = true")
    List<Servico> findByUsuarioEditorId(@Param("clienteId") Integer clienteId);

}
