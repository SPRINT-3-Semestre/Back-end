package com.example.EditMatch.service.usuario.autenticacao.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Data
public class EditorResumoDto {
    private Integer id;
    private String nome;
    private Double valorHora;
    private String skills;
    private byte[] photoProfileData;
    private MultipartFile photoProfileFile;
}
