package com.example.EditMatch.entity.carteira;

import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.transacao.Transacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double saldoTotal;
    private Double saldoAtual;
    @OneToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "id")
    private Usuario usuario;
    @OneToMany(mappedBy = "carteira")  // Assuming "carteira" is the mappedBy attribute
    private List<Transacao> transacoes;
}
