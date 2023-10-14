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
    @JoinColumn(name = "id")
    private User id_user;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SocialNetworkType id_social_network_type;

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

    public User getUser_id() {
        return id_user;
    }

    public void setUser_id(User user_id) {
        this.id_user = user_id;
    }

    public SocialNetworkType getId_social_network_type() {
        return id_social_network_type;
    }

    public void setId_social_network_type(SocialNetworkType id_social_network_type) {
        this.id_social_network_type = id_social_network_type;
    }
}