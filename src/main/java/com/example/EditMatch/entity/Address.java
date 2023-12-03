package com.example.EditMatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String cidade;
    private String logradouro;
    private String complemento;
    private String estado;
    private String bairro;
    private String numero;

}
