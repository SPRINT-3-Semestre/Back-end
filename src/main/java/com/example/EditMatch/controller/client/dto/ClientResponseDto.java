package com.example.EditMatch.controller.client.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
public class ClientResponseDto {
    private Integer id;
    private String name;
    private String last_name;
    private LocalDate birth;
    private Integer gender;
    private String photo_profile;
    private String desc_profile;
    private LocalDate dataEntrega;
    private Double valorHora;
    private byte[] photoProfileData;
    private MultipartFile photoProfileFile;
}
