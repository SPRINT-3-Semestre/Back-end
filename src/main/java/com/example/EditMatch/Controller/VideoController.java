package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Entity.Video;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoController {
    // Injeção de dependência do repositório de usuários
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Video>> listar() {
        // Busca todos os usuários no banco de dados
        List<Video> videos = this.videoRepository.findAll();

        // Verifica se a lista de usuários está vazia
        if (videos.isEmpty()) {
            // Retorna uma resposta HTTP 404 (Not Found) se não houver usuários
            return ResponseEntity.status(404).build();
        }

        // Retorna uma resposta HTTP 200 (OK) com a lista de usuários no corpo da resposta
        return ResponseEntity.status(200).body(videos);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createVideo(@PathVariable Integer userId, @RequestBody Video video) {
        Optional<Usuario> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            Usuario user = userOptional.get();
            video.setUser(user);
            videoRepository.save(video);
            return ResponseEntity.ok("Vídeo criado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> atualizar(@PathVariable int id, @RequestBody Video video){
        // Define o ID do editor com base no parâmetro da URL
        video.setId(id);

        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.videoRepository.existsById(id)){
            // Atualiza os dados do editor e o salva no banco de dados
            Video videoAtualizado= this.videoRepository.save(video);

            // Retorna uma resposta HTTP 200 (OK) com o editor atualizado no corpo da resposta
            return ResponseEntity.status(200).body(videoAtualizado);
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Video> delete(@PathVariable int id){
        // Verifica se o editor com o ID especificado existe no banco de dados
        if(this.videoRepository.existsById(id)){
            // Exclui o editor do banco de dados
            this.videoRepository.deleteById(id);

            // Retorna uma resposta HTTP 200 (OK) indicando que a exclusão foi realizada
            return ResponseEntity.status(200).build();
        }

        // Retorna uma resposta HTTP 404 (Not Found) se o editor não existe
        return ResponseEntity.status(404).build();
    }

}
