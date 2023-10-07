package com.example.EditMatch.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
@Entity
abstract public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Email
    private String email;
    private String senha;
    private LocalDate dataEntrega;

    public Usuario() {
    }

    // APLIQUE UMA LÓGICA DE IMPLEMENTAÇÃO QUE FAÇA SENTIDO NESSE MÉTODO DE FORMAS DIFERENTES
    // PARA O EDITOR E O CLIENTE FINAL.
    // PODE MODIFICAR O QUE ACHAR PRECISO.
    // SE ADICIONAR UMA NOVA CLASE AVISE PARA IMPLEMENTAR NO DIAGRAMA.
    public abstract String alertarPrazo();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}
