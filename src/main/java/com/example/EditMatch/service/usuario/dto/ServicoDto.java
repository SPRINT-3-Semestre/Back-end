package com.example.EditMatch.service.usuario.dto;

import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class ServicoDto {
    private Integer id;

    @ManyToOne
    private UsuarioServiceDto usuarioCliente;

    @ManyToOne
    private UsuarioServiceDto usuarioEditor;

    private String title;
    private String desc;
    private Double valor;
    private LocalDate createdAt;
    private LocalDate expectedDate;
    private LocalDate updatedAt;
    private LocalDate finishedAt;

    public ServicoDto() {
    }

    public ServicoDto(Integer id, UsuarioServiceDto usuarioCliente, UsuarioServiceDto usuarioEditor, String title, String desc, Double valor, LocalDate createdAt, LocalDate expectedDate, LocalDate updatedAt, LocalDate finishedAt) {
        this.id = id;
        this.usuarioCliente = usuarioCliente;
        this.usuarioEditor = usuarioEditor;
        this.title = title;
        this.desc = desc;
        this.valor = valor;
        this.createdAt = createdAt;
        this.expectedDate = expectedDate;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioServiceDto getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(UsuarioServiceDto usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public UsuarioServiceDto getUsuarioEditor() {
        return usuarioEditor;
    }

    public void setUsuarioEditor(UsuarioServiceDto usuarioEditor) {
        this.usuarioEditor = usuarioEditor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }
}
