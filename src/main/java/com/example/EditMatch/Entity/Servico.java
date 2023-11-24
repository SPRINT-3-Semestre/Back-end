package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuarioCliente;

    @ManyToOne
    private Usuario usuarioEditor;

    private String title;
    private String desc;
    private Double valor;
    private LocalDate createdAt;
    private LocalDate expectedDate;
    private LocalDate updatedAt;
    private LocalDate finishedAt;
}
