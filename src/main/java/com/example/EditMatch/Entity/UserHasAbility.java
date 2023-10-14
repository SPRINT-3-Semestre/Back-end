package com.example.EditMatch.Entity;

import jakarta.persistence.*;

@Entity
public class UserHasAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserHasAbility;
    @ManyToOne
    @JoinColumn(name = "id")
    private User idUser;
    @ManyToOne
    @JoinColumn(name = "id")
    private Ability idAbility;

    public Integer getIdUserHasAbility() {
        return idUserHasAbility;
    }

    public void setIdUserHasAbility(Integer idUserHasAbility) {
        this.idUserHasAbility = idUserHasAbility;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setUsuario_id(User user_id) {
        this.idUser = user_id;
    }

    public Ability getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(Ability ability_id) {
        this.idAbility = idAbility;
    }
}