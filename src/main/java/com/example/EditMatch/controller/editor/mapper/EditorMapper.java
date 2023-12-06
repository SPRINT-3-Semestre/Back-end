package com.example.EditMatch.controller.editor.mapper;

import com.example.EditMatch.controller.editor.dto.EditorCreateDto;
import com.example.EditMatch.controller.editor.dto.EditorResponseDto;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.time.LocalDateTime;


public class EditorMapper {

    public static Editor of(EditorCreateDto editorCreateDto){
        Editor editor = new Editor();

        editor.setNome(editorCreateDto.getName());
        editor.setLast_name(editorCreateDto.getLast_name());
        editor.setCpf(editorCreateDto.getCpf());
        editor.setBirth(editorCreateDto.getBirth());
        editor.setRg(editorCreateDto.getRg());
        editor.setGender(editorCreateDto.getGender());
        editor.setDataEntrega(editorCreateDto.getDataEntrega());
        editor.setChavePix(editorCreateDto.getChavePix());
        editor.setDesc_profile(editorCreateDto.getDesc_profile());
        editor.setCreated_at(LocalDateTime.now());
        editor.setUpdated_at(LocalDateTime.now());
        editor.setValorHora(editorCreateDto.getValorHora());
        editor.setIsEditor(true);
        editor.setEmail(editorCreateDto.getEmail());
        editor.setPassword(editorCreateDto.getPassword());
        editor.setPhotoProfileData(editorCreateDto.getPhotoProfileData());
        editor.setPhotoProfileFile(editorCreateDto.getPhotoProfileFile());

        return editor;
    }

    public static EditorResponseDto of(Editor editor){
        EditorResponseDto editorDto = new EditorResponseDto();

        editorDto.setId(editor.getId());
        editorDto.setName(editor.getNome());
        editorDto.setLast_name(editor.getLast_name());
        editorDto.setBirth(editor.getBirth());
        editorDto.setGender(editor.getGender());
        editorDto.setDataEntrega(editor.getDataEntrega());
        editorDto.setDesc_profile(editor.getDesc_profile());
        editorDto.setValorHora(editor.getValorHora());
        editorDto.setPhotoProfileFile(editor.getPhotoProfileFile());
        editorDto.setPhotoProfileData(editor.getPhotoProfileData());


        return editorDto;
    }

}
