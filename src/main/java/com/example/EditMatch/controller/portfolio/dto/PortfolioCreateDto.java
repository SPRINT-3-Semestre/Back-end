package com.example.EditMatch.controller.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PortfolioCreateDto {
    private String title;
    private Integer editorId;
    @NotBlank(message = "O ID do vídeo do YouTube é obrigatório.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{11}$", message = "O ID do vídeo do YouTube é inválido.")
    private String linkYtVideoId;
}
