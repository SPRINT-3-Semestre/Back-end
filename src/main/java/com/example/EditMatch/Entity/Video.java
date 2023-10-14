package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

@Entity
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
    @JoinColumn(name = "id")
    private User id_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getIdUser() {
        return id_user;
    }

    public void setIdUser(User id_user) {
        this.id_user = id_user;
    }
}
