package com.example.EditMatch.controller.portfolio.dto;

import lombok.Data;
@Data
public class PortfolioResponseDto {
    private Integer editorId;
    private String nomeEditor;
    private byte[] photoProfileData;
    private String title;
}
