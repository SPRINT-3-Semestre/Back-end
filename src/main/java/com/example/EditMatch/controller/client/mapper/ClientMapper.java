package com.example.EditMatch.controller.client.mapper;

import com.example.EditMatch.controller.client.dto.ClientCreateDto;
import com.example.EditMatch.controller.client.dto.ClientResponseDto;
import com.example.EditMatch.entity.ClientFinal;

import java.time.LocalDateTime;

public class ClientMapper {

    public static ClientFinal of(ClientCreateDto clientCreateDto){
        ClientFinal client = new ClientFinal();

        client.setNome(clientCreateDto.getName());
        client.setLast_name(clientCreateDto.getLast_name());
        client.setCpf(clientCreateDto.getCpf());
        client.setBirth(clientCreateDto.getBirth());
        client.setRg(clientCreateDto.getRg());
        client.setGender(clientCreateDto.getGender());
        client.setDataEntrega(clientCreateDto.getDataEntrega());
        client.setChavePix(clientCreateDto.getChavePix());
        client.setDesc_profile(clientCreateDto.getDesc_profile());
        client.setCreated_at(LocalDateTime.now());
        client.setUpdated_at(LocalDateTime.now());
        client.setPhoto_profile(clientCreateDto.getPhoto_profile());
        client.setIsEditor(false);
        client.setEmail(clientCreateDto.getEmail());
        client.setPassword(clientCreateDto.getPassword());

        return client;
    }

    public static ClientResponseDto of(ClientFinal clientFinal){
        ClientResponseDto clientResponseDto = new ClientResponseDto();

        clientResponseDto.setId(clientFinal.getId());
        clientResponseDto.setName(clientFinal.getNome());
        clientResponseDto.setLast_name(clientFinal.getLast_name());
        clientResponseDto.setBirth(clientFinal.getBirth());
        clientResponseDto.setGender(clientFinal.getGender());
        clientResponseDto.setDataEntrega(clientFinal.getDataEntrega());
        clientResponseDto.setDesc_profile(clientFinal.getDesc_profile());
        clientResponseDto.setPhoto_profile(clientFinal.getPhoto_profile());

        return clientResponseDto;
    }
}
