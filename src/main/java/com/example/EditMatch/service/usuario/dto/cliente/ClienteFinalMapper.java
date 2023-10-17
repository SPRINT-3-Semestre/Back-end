package com.example.EditMatch.service.usuario.dto.cliente;

import com.example.EditMatch.Entity.ClienteFinal;
import com.example.EditMatch.service.usuario.autenticacao.dto.cliente.ClienteFinalTokenDto;

public class ClienteFinalMapper {

    public static ClienteFinal of(ClienteFinalCriacaoDto clienteFinalCriacaoDto) {
        ClienteFinal clienteFinal = new ClienteFinal();

        clienteFinal.setEmail(clienteFinalCriacaoDto.getEmail());
        clienteFinal.setNome(clienteFinalCriacaoDto.getNome());
        clienteFinal.setPassword(clienteFinalCriacaoDto.getSenha());

        return clienteFinal;
    }

    public static ClienteFinalTokenDto of(ClienteFinal cliente, String token) {

        ClienteFinalTokenDto ClienteFinalTokenDto = new ClienteFinalTokenDto();

        ClienteFinalTokenDto.setUserId(cliente.getId());
        ClienteFinalTokenDto.setEmail(cliente.getEmail());
        ClienteFinalTokenDto.setNome(cliente.getNome());
        ClienteFinalTokenDto.setToken(token);

        return ClienteFinalTokenDto;
    }
}