package com.example.EditMatch.Controller;

import com.example.EditMatch.service.generic.WebSocketService;
import com.example.EditMatch.service.generic.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notification")
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @PostMapping
    @CrossOrigin
    public void sendMessage(@RequestBody MessageDTO messageDto){
        webSocketService.sendMessage(messageDto);
    }

}