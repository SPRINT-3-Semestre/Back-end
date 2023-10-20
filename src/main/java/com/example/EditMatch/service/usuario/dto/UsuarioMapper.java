package com.example.EditMatch.service.usuario.dto;

import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setPassword(usuarioCriacaoDto.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {

        UsuarioTokenDto UsuarioTokenDto = new UsuarioTokenDto();

        UsuarioTokenDto.setUserId(usuario.getId());
        UsuarioTokenDto.setEmail(usuario.getEmail());
        UsuarioTokenDto.setNome(usuario.getNome());
        UsuarioTokenDto.setToken(token);

        return UsuarioTokenDto;
    }
}