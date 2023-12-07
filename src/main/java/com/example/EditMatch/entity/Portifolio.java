package com.example.EditMatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Portifolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String linkYtVideoId;
    @ManyToOne
    @JoinColumn(name = "fk_editor")
    private Usuario editor;
}

