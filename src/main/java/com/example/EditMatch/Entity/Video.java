package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    private String title;

    @Size(max = 100)
    private String descricao;

    @Size(max = 100)
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

}
