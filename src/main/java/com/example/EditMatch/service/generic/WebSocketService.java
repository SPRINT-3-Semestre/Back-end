package com.example.EditMatch.Service.generic;


import com.example.EditMatch.service.generic.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public void sendMessage(MessageDTO message) {
        template.convertAndSend("/topic/message", message.getMessage());
    }
}