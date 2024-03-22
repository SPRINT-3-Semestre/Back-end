package com.example.EditMatch.entity.wallet;

import com.example.EditMatch.entity.Usuario;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Carteira {
    @Id
    private Integer id;
    private Double saldo_total;
    private Double saldo_atual;
    @OneToOne
    private Usuario editor;


}
