package com.example.EditMatch.service.usuario.autenticacao.dto.editor;

public class EditorTokenDto {

    private Integer userId;
    private String nome;
    private String email;
    private String token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getToken() {
        System.out.println("Token ------- " + token);
       return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
