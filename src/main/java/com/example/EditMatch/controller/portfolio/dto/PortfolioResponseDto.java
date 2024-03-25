package com.example.EditMatch.controller.portfolio.dto;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioResponseDto {
    private Integer editorId;
    private String nomeEditor;
    private byte[] photoProfileData;
    private String title;
    private Double valor;
    private List<String> linkYtVideoId;
}
