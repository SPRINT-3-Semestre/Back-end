package com.example.EditMatch.service.usuario.dto.editor;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.service.usuario.autenticacao.dto.editor.EditorTokenDto;

public class EditorMapper {

    public static Editor of(EditorCriacaoDto editorCriacaoDto) {
        Editor editor = new Editor();

        editor.setEmail(editorCriacaoDto.getEmail());
        editor.setNome(editorCriacaoDto.getNome());
        editor.setPassword(editorCriacaoDto.getSenha());

        return editor;
    }

    public static EditorTokenDto of(Editor editor, String token) {

        EditorTokenDto EditorTokenDto = new EditorTokenDto();

        EditorTokenDto.setUserId(editor.getId());
        EditorTokenDto.setEmail(editor.getEmail());
        EditorTokenDto.setNome(editor.getNome());
        EditorTokenDto.setToken(token);

        return EditorTokenDto;
    }
}