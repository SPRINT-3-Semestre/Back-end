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
    private String desc;
    private String linkYtVideoId;
    private String linkGit;
    private String linkLinkedin;
    @ManyToOne
    @JoinColumn(name = "fk_editor")
    private Usuario editor;
}

