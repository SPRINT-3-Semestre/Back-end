package com.example.EditMatch.service.usuario.dto;

public class CustomServiceInfo {
    private Integer serviceId;
    private Integer clientId;
    private String clientName;

    private Integer editorId;
    private String editorName;
    private String	title;
    private String	desc;
    private Double	valor;
    private Double valorTotal;
    public CustomServiceInfo(Integer serviceId, Integer clientId, String clientName, Integer editorId, String editorName, String title, String desc, Double valor) {
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.editorId = editorId;
        this.editorName = editorName;
        this.title = title;
        this.desc = desc;
        this.valor = valor;
    }

    public CustomServiceInfo(Integer serviceId, Integer clientId, String clientName, String title, String desc, Double valor) {
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.title = title;
        this.desc = desc;
        this.valor = valor;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
