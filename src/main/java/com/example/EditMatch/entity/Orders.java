package com.example.EditMatch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String desc;
    private String skills;
    @ManyToOne
    @JoinColumn(name = "fk_editor")
    private Editor editor;
    @ManyToOne
    @JoinColumn(name = "fk_client")
    private ClientFinal clientFinal;
}
