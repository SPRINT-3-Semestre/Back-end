package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Max(50)
    private String title;

    @Max(100)
    private String descricao;

    @Max(100)
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

}
