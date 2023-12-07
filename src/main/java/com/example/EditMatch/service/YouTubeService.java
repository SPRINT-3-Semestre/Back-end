package com.example.EditMatch.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class YouTubeService {
    @Value("${youtube.api.key}")
    private String apiKey;

    private final YouTube youTube;

    public YouTubeService() throws GeneralSecurityException, IOException {
        this.youTube = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                null)
                .setApplicationName("NomeDaSuaAplicacao")
                .build();
    }

    /**
     * Obtém a URL de incorporação de um vídeo do YouTube.
     *
     * @param videoId O ID do vídeo do YouTube.
     * @return A URL de incorporação do vídeo.
     * @throws IOException Se ocorrer um erro de rede.
     */
    public String getVideoEmbedUrl(String videoId) throws IOException {
        YouTube.Videos.List listRequest = youTube.videos().list(Collections.singletonList("snippet").toString());
        listRequest.setId(Collections.singletonList(videoId).toString());
        listRequest.setKey(apiKey);

        VideoListResponse listResponse = listRequest.execute();

        if (listResponse.getItems().isEmpty()) {
            throw new IllegalArgumentException("ID do vídeo do YouTube inválido");
        }

        return "https://www.youtube.com/embed/" + videoId;
    }

    public YouTubeService(String apiKey, YouTube youTube) {
        this.apiKey = apiKey;
        this.youTube = youTube;
    }
}