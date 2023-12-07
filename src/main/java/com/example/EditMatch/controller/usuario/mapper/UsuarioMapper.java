package com.example.EditMatch.controller.usuario.mapper;

import com.example.EditMatch.controller.usuario.dto.UsuarioCriacaoDto;
import com.example.EditMatch.controller.usuario.dto.UsuarioServiceDto;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setPassword(usuarioCriacaoDto.getSenha());

        return usuario;
    }

    public static Usuario of(UsuarioServiceDto usuarioServiceDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioServiceDto.getEmail());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {

        UsuarioTokenDto UsuarioTokenDto = new UsuarioTokenDto();

        UsuarioTokenDto.setUserId(usuario.getId());
        UsuarioTokenDto.setNome(usuario.getNome());
        UsuarioTokenDto.setEmail(usuario.getEmail());
        UsuarioTokenDto.setToken(token);
        UsuarioTokenDto.setEditor(usuario.getIsEditor());

        return UsuarioTokenDto;
    }
}