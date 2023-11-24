package com.example.EditMatch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer expiracaoMes;
    private Integer status;
    private LocalDateTime crateAt;
    @OneToOne
    private Servico servico;
    
}
