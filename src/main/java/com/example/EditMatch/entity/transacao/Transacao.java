package com.example.EditMatch.entity.transacao;

import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.carteira.Carteira;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valor;
    private LocalDateTime dataHora;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "fk_carteira")
    @JsonBackReference // Adicione esta anotação na extremidade que queremos ignorar durante a serialização
    private Carteira carteira;

    // Construtor com argumentos
    public Transacao(Integer id, Double valor, String tipo, Carteira carteira) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.carteira = carteira;
        this.dataHora = LocalDateTime.now(); // Inicializa dataHora com o valor atual
    }
}

