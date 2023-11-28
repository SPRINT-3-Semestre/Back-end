package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByUsuarioClienteId(Integer id);

    // Consulta para buscar serviços por ID do usuário e mês/ano
    @Query("SELECT s FROM Servico s " +
            "WHERE s.usuarioCliente.id = :idUsuario " +
            "AND YEAR(s.createdAt) = :ano " +
            "AND MONTH(s.createdAt) = :mes")
    List<Servico> findByUsuarioClienteIdAndCreatedAtYearAndCreatedAtMonth(
            @Param("idUsuario") Integer idUsuario,
            @Param("ano") Integer ano,
            @Param("mes") Integer mes);
}
