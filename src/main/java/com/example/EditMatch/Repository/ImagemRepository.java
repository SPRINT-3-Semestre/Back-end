package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

}
