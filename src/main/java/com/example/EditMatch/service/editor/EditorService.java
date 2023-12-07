package com.example.EditMatch.service.editor;

import com.example.EditMatch.controller.editor.dto.EditorCreateDto;
import com.example.EditMatch.controller.editor.mapper.EditorMapper;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.editor.exception.EditorException;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.EditorResumoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;
    private final UsuarioService usuarioService;
    private final UserRepository userRepository;

    public EditorResumoDto listSummary(Integer id) {
        Optional<Editor> isEditor = editorRepository.findById(id);
        if(isEditor.isEmpty()) {
            throw new EditorException("Editor não encontrado");
        }
        Editor editor = isEditor.get();

        EditorResumoDto editorResumoDto = new EditorResumoDto();

        editorResumoDto.setId(editor.getId());
        editorResumoDto.setNome(editor.getNome());
        editorResumoDto.setValorHora(editor.getValorHora());
        editorResumoDto.setPhotoProfileData(editor.getPhotoProfileData());
        editorResumoDto.setPhotoProfileFile(editor.getPhotoProfileFile());
        editorResumoDto.setSkills(editor.getSkills());

        return editorResumoDto;
    }

    public Editor register(EditorCreateDto editorCreateDto) {
        Editor editor1 = editorRepository.findByEmail(editorCreateDto.getEmail());
        if (editor1 != null) {
            throw new EditorException("Editor não encontrado");
        }

        Editor editorMapped = EditorMapper.of(editorCreateDto);
        usuarioService.cadastrar(editorMapped);
        return userRepository.save(editorMapped);
    }

    public List<Editor> list() {
        List<Editor> users = this.editorRepository.findAll();

        if (users.isEmpty()) {
            throw new EditorException("Editor não encontrado");
        }
        return users;
    }

    public Editor update(Integer id, Editor editor) {
        editor.setId(id);

        if(this.editorRepository.existsById(id)){
            return this.editorRepository.save(editor);
        }

        throw new EditorException("Editor não encontrado");
    }

    public void delete(Integer id) {
        if(this.editorRepository.existsById(id)){
            this.editorRepository.deleteById(id);
        }

        throw new EditorException("Editor não encontrado");
    }

    public Editor getById(Integer id) {
        return editorRepository.findById(id).orElseThrow(
                ()-> new EditorException("Editor não encontrado!")
        );
    }
}
