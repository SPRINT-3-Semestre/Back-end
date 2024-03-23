package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.carteira.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

    Carteira findByUsuarioId(Integer idUsuario);
}
