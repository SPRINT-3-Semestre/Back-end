package com.example.EditMatch.service;

import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.Video;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public List<Video> list() {
        List<Video> videos = this.videoRepository.findAll();

        if (videos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return videos;
    }

    public Video create(Integer id, Video video) {
        Optional<Usuario> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Usuario user = userOptional.get();
        video.setUser(user);
        videoRepository.save(video);

        return video;

    }

    public Video update(Integer id, Video videoUpdated) {
        Optional<Video> isVideo = videoRepository.findById(id);

        if(isVideo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Video video = isVideo.get();

        video.setUser(videoUpdated.getUser());
        video.setLink(videoUpdated.getLink());
        video.setDescricao(videoUpdated.getLink());
        video.setTitle(videoUpdated.getTitle());

        this.videoRepository.save(video);

        return video;
    }

    public void delete(Integer id) {
        if(this.videoRepository.existsById(id)){
            this.videoRepository.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<Video> listVideosDoEditor(Integer id) {
        List<Video> videos = this.videoRepository.findByUser(userRepository.findById(id).get());

        if (videos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return videos;
    }

}
