package com.example.EditMatch.controller.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomServiceEditor {
    private Integer serviceId;
    private Integer clientId;
    private String clientName;
    private String	title;
    private String	desc;
    private Double	valor;
    public CustomServiceEditor(Integer serviceId, Integer clientId, String clientName, String title, String desc, Double valor) {
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.title = title;
        this.desc = desc;
        this.valor = valor;
    }
}
