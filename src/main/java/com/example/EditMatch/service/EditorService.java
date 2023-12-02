package com.example.EditMatch.service;

import com.example.EditMatch.controller.editor.dto.EditorCreateDto;
import com.example.EditMatch.controller.editor.mapper.EditorMapper;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.repository.EditorRepository;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.repository.UsuarioRepositoryJWT;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.EditorResumoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;
    private final UsuarioService usuarioService;
    private final UserRepository userRepository;

    public List<EditorResumoDto> listSummary() {
        List<Editor> editores = editorRepository.findAll();
        List<EditorResumoDto> editorResumoDtoArrayList = new ArrayList<>();

        if(editores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        for (Editor e: editores ) {
            EditorResumoDto editorResumoDto = new EditorResumoDto();
            editorResumoDto.setId(e.getId());
            editorResumoDto.setNome(e.getNome());
            editorResumoDto.setHabilidades(e.getHabilidades());
            editorResumoDto.setValorHora(e.getValorHora());
            editorResumoDto.setPhoto_profile(e.getPhoto_profile());

            editorResumoDtoArrayList.add(editorResumoDto);
        }

        return editorResumoDtoArrayList;

    }

    public Editor register(EditorCreateDto editorCreateDto) {
        Editor editor1 = editorRepository.findByEmail(editorCreateDto.getEmail());
        if (editor1 != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Editor editorMapped = EditorMapper.of(editorCreateDto);
        usuarioService.cadastrar(editorMapped);
        return userRepository.save(editorMapped);
    }

    public List<Editor> list() {
        List<Editor> users = this.editorRepository.findAll();

        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return users;
    }

    public Editor update(Integer id, Editor editor) {
        editor.setId(id);

        if(this.editorRepository.existsById(id)){
            return this.editorRepository.save(editor);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delete(Integer id) {
        if(this.editorRepository.existsById(id)){
            this.editorRepository.deleteById(id);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
