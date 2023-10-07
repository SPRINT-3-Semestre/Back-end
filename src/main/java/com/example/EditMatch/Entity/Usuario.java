package com.example.EditMatch.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
abstract public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    private String last_name;
    private String rg;
    private String cpf;
    private LocalDate birth;
    private Integer gender;
    private Boolean is_editor;
    private String email;
    private String password;
    private String photo_profile;
    private String desc_profile;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getIs_editor() {
        return is_editor;
    }

    public void setIs_editor(Boolean is_editor) {
        this.is_editor = is_editor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getDesc_profile() {
        return desc_profile;
    }

    public void setDesc_profile(String desc_profile) {
        this.desc_profile = desc_profile;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}
