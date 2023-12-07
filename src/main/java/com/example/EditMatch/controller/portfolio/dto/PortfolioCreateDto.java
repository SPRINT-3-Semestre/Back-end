package com.example.EditMatch.controller.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class PortfolioCreateDto {
    @NotBlank(message = "A descrição não pode estar vazia.")
    @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres.")
    private String desc;
    @NotBlank(message = "O ID do vídeo do YouTube é obrigatório.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{11}$", message = "O ID do vídeo do YouTube é inválido.")
    private String linkYtVideoId;
    @NotBlank(message = "O link do GitHub é obrigatório.")
    @URL(message = "O link do GitHub é inválido.")
    private String linkGit;
    @NotBlank(message = "O link do LinkedIn é obrigatório.")
    @URL(message = "O link do LinkedIn é inválido.")
    private String linkLinkedin;
    private Integer editorId;
}
