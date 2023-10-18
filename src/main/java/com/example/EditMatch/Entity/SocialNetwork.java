package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class SocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100, message = "O link precisa estar dentro do limite de caracteres.")
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id") // Use o nome da coluna apropriado
    private User user;

    @ManyToOne
    @JoinColumn(name = "social_network_type_id") // Use o nome da coluna apropriado
    private SocialNetworkType socialNetworkType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SocialNetworkType getSocialNetworkType() {
        return socialNetworkType;
    }

    public void setSocialNetworkType(SocialNetworkType socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }
}