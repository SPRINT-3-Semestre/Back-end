package com.example.EditMatch.controller.editor;

import com.example.EditMatch.controller.editor.dto.EditorCreateDto;
import com.example.EditMatch.controller.editor.dto.EditorResponseDto;
import com.example.EditMatch.controller.editor.mapper.EditorMapper;
import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.repository.PortfolioRepository;
import com.example.EditMatch.service.editor.EditorService;
import com.example.EditMatch.service.portfolio.PortfolioService;
import com.example.EditMatch.service.usuario.autenticacao.dto.EditorResumoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter editor por ID", notes = "Retorna o editor com o ID especificado")
    public ResponseEntity<EditorResumoDto> getById(@PathVariable int id) {
        EditorResumoDto editor = editorService.listSummary(id);
        return ResponseEntity.ok(editor);
    }

    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ApiOperation(value = "Cadastrar editor", notes = "Retorna o editor cadastrado")
    public ResponseEntity<EditorResponseDto> register(@RequestBody @Valid EditorCreateDto editorCreateDto) {

        Editor register = editorService.register(editorCreateDto);
        EditorResponseDto editorResponseDto = EditorMapper.of(register);

        PortfolioCreateDto portfolioCreateDto = new PortfolioCreateDto();
        portfolioCreateDto.setEditor(register);

        Portfolio portfolio = portfolioService.adicionar(portfolioCreateDto);

        return ResponseEntity.status(201).body(editorResponseDto);
    }

    @GetMapping
    @ApiOperation(value = "Lista usuarios", notes = "Retorna todos os usuarios listados")
    public ResponseEntity<List<Editor>> list() {
        List<Editor> list = editorService.list();
        return ResponseEntity.status(200).body(list);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza usuario", notes = "Retorna o usuarios atualizado")
    public ResponseEntity<Editor> update(@PathVariable int id, @RequestBody Editor editor){
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
