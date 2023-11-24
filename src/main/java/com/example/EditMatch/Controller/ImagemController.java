package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Imagem;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.ImagemRepository;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.Repository.UsuarioRepositoryJWT;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/imagens")
public class ImagemController {
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private UserRepository usuarioRepository;

    @PostMapping("/upload/{id}")
    public ResponseEntity<Usuario> uploadImagem(@RequestParam("file") MultipartFile file, @RequestParam("nome") String nome, @PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        try {
            if (!file.isEmpty()) {
                Imagem imagem = new Imagem();
                imagem.setNome(nome);
                imagem.setDados(file.getBytes());
                imagemRepository.save(imagem);
                if (usuario.isPresent()){
                    Usuario usuario1 = usuario.get();
                    usuario1.setImagem(imagem);
                    usuario1.setId(id);

                    usuario1 = usuarioRepository.save(usuario1);
                    return ResponseEntity.ok(usuario1);
                }
            }
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateImagem(@RequestParam("file") MultipartFile file, @RequestParam("nome") String nome, @PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        try {
            if (!file.isEmpty()) {
                Imagem imagem = new Imagem();
                imagem.setNome(nome);
                imagem.setDados(file.getBytes());
                imagemRepository.save(imagem);
                if (usuario.isPresent()){
                    Usuario usuario1 = usuario.get();
                    usuario1.setImagem(imagem);
                    usuario1.setId(id);

                    usuario1 = usuarioRepository.save(usuario1);
                    return ResponseEntity.ok(usuario1);
                }
            }
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Usuario> deleteImagem(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        try {
            if (usuario.isPresent()){
                Usuario usuario1 = usuario.get();
                usuario1.setImagem(null);
                usuario1.setId(id);

                usuario1 = usuarioRepository.save(usuario1);
                return ResponseEntity.ok(usuario1);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
