package com.example.EditMatch.controller.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    @NotBlank(message = "O título não pode estar em branco")
    private String title;
    @NotBlank(message = "A descrição não pode estar em branco")
    private String Describle;
    @Size(min = 1, message = "Pelo menos uma habilidade deve ser especificada")
    private List<String> skills;
    @NotNull
    private Integer clientFinal;
    private Integer editor;
}
