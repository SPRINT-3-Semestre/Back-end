package com.example.EditMatch.Entity;

import jakarta.persistence.*;

@Entity
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

    public Integer getIdUserHasAbility() {
        return idUserHasAbility;
    }

    public void setIdUserHasAbility(Integer idUserHasAbility) {
        this.idUserHasAbility = idUserHasAbility;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}