package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.editor.EditorRepository;
import com.example.EditMatch.service.usuario.EditorService;
import com.example.EditMatch.service.usuario.autenticacao.dto.editor.EditorLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.editor.EditorTokenDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editores")
public class EditorController {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private EditorRepository editorRepository;

    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    // Endpoint para listar todos os usuários/editores
    @CrossOrigin
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Editor>> listar() {
        // Busca todos os usuários no banco de dados
        List<Editor> usuarios = this.editorRepository.findAll();

        // Verifica se a lista de usuários está vazia
        if (usuarios.isEmpty()) {
            // Retorna uma resposta HTTP 404 (Not Found) se não houver usuários
            return ResponseEntity.status(204).build();
        }

        // Retorna uma resposta HTTP 200 (OK) com a lista de usuários no corpo da resposta
        return ResponseEntity.status(200).body(usuarios);
    }

    // Endpoint para cadastrar um novo editor
    @CrossOrigin
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Editor editor) {
        // Verifica se já existe um usuário com o mesmo email
        Usuario usuarioExistente = editorRepository.findByEmail(editor.getEmail());
        if (usuarioExistente != null) {
            // Retorna uma resposta HTTP 400 (Bad Request) se o email já estiver em uso
            return ResponseEntity.status(400).build();
        }

        // Salva o novo editor no banco de dados
        this.editorService.cadastrar(editor);

        // Retorna uma resposta HTTP 200 (OK) com o novo editor no corpo da resposta
        return ResponseEntity.status(200).body(editor);
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<EditorTokenDto> login(@RequestBody EditorLoginDto usuarioLoginDto) {
        EditorTokenDto editorTokenDto = this.editorService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(editorTokenDto);
    }

    // Endpoint para atualizar os dados de um editor existente
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Editor editorFinal) {
        // Define o ID do editor com base no parâmetro da URL
        editorFinal.setId(id);

        // Verifica se o editor com o ID especificado existe no banco de dados
        if (this.editorRepository.existsById(id)) {
            // Atualiza os dados do editor e o salva no banco de dados
            this.editorService.atualizar(id, editorFinal);

            // Retorna uma resposta HTTP 200 (OK) com o editor atualizado no corpo da resposta
            return ResponseEntity.status(200).body(editorFinal);
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    // Endpoint para excluir um editor pelo ID
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable int id) {
        // Verifica se o editor com o ID especificado existe no banco de dados
        if (this.editorRepository.existsById(id)) {
            // Exclui o editor do banco de dados
            this.editorRepository.deleteById(id);

            // Retorna uma resposta HTTP 200 (OK) indicando que a exclusão foi realizada
            return ResponseEntity.status(200).build();
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    // Endpoint personalizado para alerta de prazo, que retorna uma mensagem
    @CrossOrigin
    @GetMapping("/alerta")
    public String alerta(Editor editor) {
        return editor.alertarPrazo();
    }

    // Método auxiliar para verificar se os dados de um editor são válidos
    public boolean isEditorValido(Editor editor) {
        if (editor.getNome().length() < 3 || editor.getNome().isBlank()
                || editor.getPassword().length() < 3 || editor.getPassword().isBlank()
                || editor.getEmail().length() < 3 || editor.getEmail().isBlank()) {
            return false;
        }
        return true;
    }
}

