package com.example.EditMatch.controller.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioCriacaoDto {

    @Size(min = 3, max = 10)
    private String nome;
    @Email
    private String email;
    @Size(min = 6, max = 20)
    private String senha;
}
