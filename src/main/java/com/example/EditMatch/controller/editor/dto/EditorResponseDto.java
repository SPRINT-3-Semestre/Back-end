package com.example.EditMatch.controller.editor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorResponseDto {
    private Integer id;
    private String name;
    private String last_name;
    private LocalDate birth;
    private Integer gender;
    private String photo_profile;
    private String desc_profile;
    private LocalDate dataEntrega;
    private Double valorHora;
}
