package com.example.EditMatch.controller.editor.dto;

import com.example.EditMatch.controller.usuario.dto.UsuarioCriacaoDto;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EditorCreateDto {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank(message = "O sobrenome não pode estar em branco")
    private String last_name;
    @Pattern(regexp = "\\d{9}", message = "RG deve conter 9 dígitos numéricos")
    private String rg;
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    private String cpf;
    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate birth;
    @Min(value = 0, message = "O valor mínimo para gender é 0")
    @Max(value = 1, message = "O valor máximo para gender é 1")
    private Integer gender;
    private String photo_profile;
    @Size(min = 8, message = "A descrição do perfil deve ter pelo menos 8 caracteres")
    private String desc_profile;
    @Future(message = "A data de entrega deve estar no futuro")
    private LocalDate dataEntrega;
    @NotNull(message = "O valor da hora não pode ser nulo")
    @Positive(message = "O valor da hora deve ser positivo")
    private Double valorHora;
    @NotBlank(message = "A chave Pix não pode estar em branco")
    private String chavePix;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean isEditor;
    private String email;
    private String password;
}

