package com.example.EditMatch.entity;

import com.google.gson.Gson;

public class Response {
    private Calendario calendario;
    private String txid;
    private int revisao;
    private String status;
    private Valor valor;
    private String chave;
    private Devedor devedor;
    private String solicitacaoPagador;
    private Loc loc;
    private String location;
    private String pixCopiaECola;

    // Getters e setters aqui

    public static Response fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }
}

class Calendario {
    private String criacao;
    private int expiracao;

    // Getters e setters aqui
}

class Valor {
    private String original;

    // Getters e setters aqui
}

class Devedor {
    private String cpf;
    private String nome;

    // Getters e setters aqui
}

class Loc {
    private int id;
    private String location;
    private String tipoCob;
    private String criacao;

    // Getters e setters aqui
}

