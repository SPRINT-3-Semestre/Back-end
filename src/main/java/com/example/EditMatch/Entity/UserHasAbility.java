package com.example.EditMatch.Entity;

import jakarta.persistence.*;

@Entity
public class UserHasAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserHasAbility;
    @ManyToOne
    @JoinColumn(name = "id")
    private User id_user;
    @ManyToOne
    @JoinColumn(name = "id")
    private Ability id_ability;

    public Integer getIdUserHasAbility() {
        return idUserHasAbility;
    }

    public void setIdUserHasAbility(Integer idUserHasAbility) {
        this.idUserHasAbility = idUserHasAbility;
    }

    public User getUsuario_id() {
        return id_user;
    }

    public void setUsuario_id(User user_id) {
        this.id_user = user_id;
    }

    public Ability getAbility_id() {
        return id_ability;
    }

    public void setAbility_id(Ability ability_id) {
        this.id_ability = ability_id;
    }
}