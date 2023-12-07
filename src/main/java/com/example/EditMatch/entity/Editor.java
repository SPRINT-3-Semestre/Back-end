package com.example.EditMatch.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@DiscriminatorValue("Editor")
public class Editor extends Usuario {
    private List<String> portifolio = new ArrayList<>();

    private List<String> habilidades = new ArrayList<>();

    private List<String> link = new ArrayList<>();


    public String alertarPrazo() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataEntrega = getDataEntrega();

        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataEntrega);

        if (diasRestantes <= 0) {
            return "O prazo de entrega do vídeo já passou. Complete e entregue o projeto o quanto antes.";
        } else if (diasRestantes <= 3) {
            return "Faltam apenas " + diasRestantes + " dias para a entrega do vídeo. Continue trabalhando!";
        } else {
            return "Você tem " + diasRestantes + " dias restantes para concluir o vídeo.";
        }
    }

}
