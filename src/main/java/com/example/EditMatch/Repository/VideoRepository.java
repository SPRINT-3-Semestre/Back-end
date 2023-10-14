package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.User;
import com.example.EditMatch.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findByUsuario(User user);
}
