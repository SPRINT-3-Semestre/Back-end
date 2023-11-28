package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Repository.EditorRepository;
import com.example.EditMatch.Service.EditorService;
import com.example.EditMatch.Service.usuario.UsuarioService;
import com.example.EditMatch.Service.usuario.autenticacao.dto.EditorResumoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editores")
@RequiredArgsConstructor
@Api(value = "EditorController", description = "Controladora de editor")
public class EditorController {

    private final EditorService editorService;

    @GetMapping("/{listarResumo}")
    public ResponseEntity<List<EditorResumoDto>> listSummary() {
        List<EditorResumoDto> editorSummary = editorService.listSummary();
        return ResponseEntity.ok().body(editorSummary);
    }

    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar editor", notes = "Retorna o editor cadastrado")
    public ResponseEntity<Editor> register(@RequestBody Editor editor) {
        editorService.register(editor);
        return ResponseEntity.status(200).body(editor);
    }

    @GetMapping
    @ApiOperation(value = "Lista usuarios", notes = "Retorna todos os usuarios listados")
    public ResponseEntity<List<Editor>> list() {
        List<Editor> list = editorService.list();
        return ResponseEntity.status(200).body(list);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuarios atualizado")
    public ResponseEntity<Editor> updata(@PathVariable int id, @RequestBody Editor editor){
        Editor editorUpdated = editorService.update(id, editor);

        return ResponseEntity.ok().body(editorUpdated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuario", notes = "Retorna o status 200 caso de certo")
    public ResponseEntity<Void> delete(@PathVariable int id){
        editorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/alerta")
    @ApiOperation(value = "Avisa o editor da data de entregado do video", notes = "Retorna uma data para o editor")
    public String alerta(Editor editor){
        return editor.alertarPrazo();
    }

    public boolean isEditorValido(Editor editor){
        return editor.getNome().length() >= 3 && !editor.getNome().isBlank()
                && editor.getPassword().length() >= 3 && !editor.getPassword().isBlank()
                && editor.getEmail().length() >= 3 && !editor.getEmail().isBlank();
    }
}
