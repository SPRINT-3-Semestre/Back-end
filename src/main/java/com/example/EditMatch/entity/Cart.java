package com.example.EditMatch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer valorTotal;
    @ManyToOne
    @JoinColumn(name = "fk_editor")
    private Editor editor;
    @ManyToOne
    @JoinColumn(name = "fk_client")
    private ClientFinal clientFinal;
}
