package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.User;
import com.example.EditMatch.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/editors")
public class EditorController {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Endpoint para listar todos os usuários/editores
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        // Busca todos os usuários no banco de dados
        List<User> users = this.userRepository.findAll();

        // Verifica se a lista de usuários está vazia
        if (users.isEmpty()) {
            // Retorna uma resposta HTTP 404 (Not Found) se não houver usuários
            return ResponseEntity.status(404).build();
        }

        // Retorna uma resposta HTTP 200 (OK) com a lista de usuários no corpo da resposta
        return ResponseEntity.status(200).body(users);
    }

    // Endpoint para cadastrar um novo editor
    @PostMapping
    public ResponseEntity<User> cadastrar(@RequestBody Editor editor) {
        // Verifica se já existe um usuário com o mesmo email
        User userExistente = userRepository.findByEmail(editor.getEmail());
        if (userExistente != null) {
            // Retorna uma resposta HTTP 400 (Bad Request) se o email já estiver em uso
            return ResponseEntity.status(400).build();
        }

        // Salva o novo editor no banco de dados
        User editorSalvo = userRepository.save(editor);

        // Retorna uma resposta HTTP 200 (OK) com o novo editor no corpo da resposta
        return ResponseEntity.status(200).body(editorSalvo);
    }

    // Endpoint para atualizar os dados de um editor existente
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable int id, @RequestBody Editor editorFinal){
        // Define o ID do editor com base no parâmetro da URL
        editorFinal.setId(id);

        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.userRepository.existsById(id)){
            // Atualiza os dados do editor e o salva no banco de dados
            User userAtualizado = this.userRepository.save(editorFinal);

            // Retorna uma resposta HTTP 200 (OK) com o editor atualizado no corpo da resposta
            return ResponseEntity.status(200).body(userAtualizado);
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    // Endpoint para excluir um editor pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id){
        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.userRepository.existsById(id)){
            // Exclui o editor do banco de dados
            this.userRepository.deleteById(id);

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
