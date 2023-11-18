package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserHasAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserHasAbility;

    @ManyToOne
    @JoinColumn(name = "user_id") // Nomeie a coluna para a associação com User
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "ability_id") // Nomeie a coluna para a associação com Ability
    private Ability ability;

}