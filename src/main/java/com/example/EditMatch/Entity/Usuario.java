package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotBlank(message = "O nome não pode estar em branco")
    private String nome;

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

    private Boolean isEditor;

    @Email(message = "O formato do e-mail é inválido")
    private String email;

    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String password;

    private String photo_profile;

    private String desc_profile;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    //@Future(message = "A data de entrega deve estar no futuro")
    private LocalDate dataEntrega;

    private Double valorHora;

    private String chavePix;

    @OneToOne
    private Endereco endereco;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Imagem imagem;

    public Usuario() {
    }

}
