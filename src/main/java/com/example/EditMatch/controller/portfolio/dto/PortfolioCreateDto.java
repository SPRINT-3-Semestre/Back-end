package com.example.EditMatch.controller.portfolio.dto;

import com.example.EditMatch.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioCreateDto {
    private Usuario editor;
}
