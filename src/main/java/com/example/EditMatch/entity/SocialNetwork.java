package com.example.EditMatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100, message = "O link precisa estar dentro do limite de caracteres.")
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id") // Use o nome da coluna apropriado
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "social_network_type_id") // Use o nome da coluna apropriado
    private SocialNetworkType socialNetworkType;

}