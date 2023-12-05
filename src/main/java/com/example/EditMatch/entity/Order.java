package com.example.EditMatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {
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
