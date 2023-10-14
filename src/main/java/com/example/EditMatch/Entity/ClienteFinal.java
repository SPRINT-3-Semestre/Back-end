package com.example.EditMatch.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Entity
public class ClienteFinal extends User {
    @Override
    public String alertarPrazo() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataEntrega = getDataEntrega();

        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataEntrega);

        if (diasRestantes <= 0) {
            return "Lembre ao seu editor o prazo de entrega! O prazo está no final!";
        } else if (diasRestantes <= 3) {
            return "Faltam apenas " + diasRestantes + " dias para a entrega do vídeo. Continue trabalhando!";
        } else {
            return "Faltam " + diasRestantes + " para acabar o prazo que foi passado ao seu editor.";
        }
    }
}
