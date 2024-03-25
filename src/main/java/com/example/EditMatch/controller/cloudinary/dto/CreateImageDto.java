package com.example.EditMatch.controller.cloudinary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateImageDto {
    @NotBlank(message = "O link da imagem não pode estar em branco")
    private String linkImage;
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;
    @NotNull(message = "O ID do usuário não pode ser nulo")
    @Size(min = 1, max = 50, message = "O ID do usuário deve ter entre 1 e 50 caracteres")
    private String userId;
}
