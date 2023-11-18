package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.EditorRepository;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.service.usuario.UsuarioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.EditorResumoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/editores")
@RequiredArgsConstructor
@Api(value = "EditorController", description = "Controladora de editor")
public class EditorController {

    private final UsuarioService usuarioService;
    private final EditorRepository editorRepository;

    @GetMapping("/{listarResumo}")
    public ResponseEntity<List<EditorResumoDto>> listarResumo() {
        List<Editor> editores = editorRepository.findAll();

        List<EditorResumoDto> editorResumoDtoArrayList = new ArrayList<>();

        for (Editor e: editores ) {
            EditorResumoDto editorResumoDto = new EditorResumoDto();
            editorResumoDto.setId(e.getId());
            editorResumoDto.setNome(e.getNome());
            editorResumoDto.setHabilidades(e.getHabilidades());
            editorResumoDto.setValorHora(e.getValorHora());
            editorResumoDto.setPhoto_profile(e.getPhoto_profile());

            editorResumoDtoArrayList.add(editorResumoDto);
        }

        if(editores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(editorResumoDtoArrayList);
    }

    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar editor", notes = "Retorna o editor cadastrado")
    public ResponseEntity<Editor> cadastrar(@RequestBody Editor editor) {
        Editor editor1 = editorRepository.findByEmail(editor.getEmail());
        if (editor1 != null) {
            return ResponseEntity.status(400).build();
        }
        this.usuarioService.cadastrar(editor);

        return ResponseEntity.status(200).body(editor);
    }

    @GetMapping
    @ApiOperation(value = "Lista usuarios", notes = "Retorna todos os usuarios listados")
    public ResponseEntity<List<Editor>> listar() {
        List<Editor> users = this.editorRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(users);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuarios atualizado")
    public ResponseEntity<Editor> atualizar(@PathVariable int id, @RequestBody Editor editorFinal){
        editorFinal.setId(id);

        if(this.editorRepository.existsById(id)){
            Editor editor = this.editorRepository.save(editorFinal);
            return ResponseEntity.status(200).body(editor);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuario", notes = "Retorna o status 200 caso de certo")
    public ResponseEntity<Editor> delete(@PathVariable int id){
        if(this.editorRepository.existsById(id)){
            this.editorRepository.deleteById(id);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/alerta")
    @ApiOperation(value = "Avisa o editor da data de entregado do video", notes = "Retorna uma data para o editor")
    public String alerta(Editor editor){
        return editor.alertarPrazo();
    }

    public boolean isEditorValido(Editor editor){
        if(editor.getNome().length() < 3 || editor.getNome().isBlank()
                || editor.getPassword().length() < 3 || editor.getPassword().isBlank()
                || editor.getEmail().length() < 3 || editor.getEmail().isBlank()){
            return false;
        }
        return true;
    }
}
