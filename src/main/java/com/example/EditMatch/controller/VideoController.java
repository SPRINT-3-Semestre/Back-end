package com.example.EditMatch.controller;

import com.example.EditMatch.entity.Video;
import com.example.EditMatch.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
@Api(value = "VideoController", description = "Controladora de videos")
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    @ApiOperation(value = "Lista videos", notes = "Retorna todos os videos cadastrados")
    public ResponseEntity<List<Video>> listar() {
        List<Video> list = videoService.list();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{userId}")
    @ApiOperation(value = "Lista videos do editor", notes = "Retorna todos os videos cadastrados de um respctivo editor")
    public ResponseEntity<List<Video>> listarVideosDoEditor(@PathVariable Integer userId) {
        List<Video> list = videoService.listVideosDoEditor(userId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{userId}")
    @ApiOperation(value = "Cadastra videos", notes = "Retorna 200 caso tenha cadastrado corretamente")
    public ResponseEntity<Video> create(@PathVariable Integer userId, @RequestBody @Valid Video video) {
        Video video1 = videoService.create(userId, video);
        return ResponseEntity.ok().body(video1);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza videos", notes = "Retorna 200 caso tenha atulizado corretamente")
    public ResponseEntity<Video> update(@PathVariable Integer id, @RequestBody Video videoUpdated){
        Video update = videoService.update(id, videoUpdated);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta videos", notes = "Retorna 200 caso tenha deletado corretamente")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        videoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
