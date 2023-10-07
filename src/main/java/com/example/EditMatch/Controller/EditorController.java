package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/editores")
public class EditorController {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint para listar todos os usuários/editores
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        // Busca todos os usuários no banco de dados
        List<Usuario> usuarios = this.usuarioRepository.findAll();

        // Verifica se a lista de usuários está vazia
        if (usuarios.isEmpty()) {
            // Retorna uma resposta HTTP 404 (Not Found) se não houver usuários
            return ResponseEntity.status(404).build();
        }

        // Retorna uma resposta HTTP 200 (OK) com a lista de usuários no corpo da resposta
        return ResponseEntity.status(200).body(usuarios);
    }

    // Endpoint para cadastrar um novo editor
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Editor editor) {
        // Verifica se já existe um usuário com o mesmo email
        Usuario usuarioExistente = usuarioRepository.findByEmail(editor.getEmail());
        if (usuarioExistente != null) {
            // Retorna uma resposta HTTP 400 (Bad Request) se o email já estiver em uso
            return ResponseEntity.status(400).build();
        }

        // Salva o novo editor no banco de dados
        Usuario editorSalvo = usuarioRepository.save(editor);

        // Retorna uma resposta HTTP 200 (OK) com o novo editor no corpo da resposta
        return ResponseEntity.status(200).body(editorSalvo);
    }

    // Endpoint para atualizar os dados de um editor existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Editor editorFinal){
        // Define o ID do editor com base no parâmetro da URL
        editorFinal.setId(id);

        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.usuarioRepository.existsById(id)){
            // Atualiza os dados do editor e o salva no banco de dados
            Usuario usuarioAtualizado = this.usuarioRepository.save(editorFinal);

            // Retorna uma resposta HTTP 200 (OK) com o editor atualizado no corpo da resposta
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    // Endpoint para excluir um editor pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable int id){
        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.usuarioRepository.existsById(id)){
            // Exclui o editor do banco de dados
            this.usuarioRepository.deleteById(id);

            // Retorna uma resposta HTTP 200 (OK) indicando que a exclusão foi realizada
            return ResponseEntity.status(200).build();
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    // Endpoint personalizado para alerta de prazo, que retorna uma mensagem
    @GetMapping("/alerta")
    public String alerta(Editor editor){
        return editor.alertarPrazo();
    }

    // Método auxiliar para verificar se os dados de um editor são válidos
    public boolean isEditorValido(Editor editor){
        if(editor.getNome().length() < 3 || editor.getNome().isBlank()
                || editor.getPassword().length() < 3 || editor.getPassword().isBlank()
                || editor.getEmail().length() < 3 || editor.getEmail().isBlank()){
            return false;
        }
        return true;
    }
}
