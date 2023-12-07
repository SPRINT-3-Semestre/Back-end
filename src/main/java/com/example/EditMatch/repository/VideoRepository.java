package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findByUser(Usuario user);

}
