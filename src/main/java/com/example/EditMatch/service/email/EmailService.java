package com.example.EditMatch.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendWelcomeEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Bem-vindo ao nosso sistema!");
        message.setText("Olá,\n\nBem-vindo ao nosso sistema. Esperamos que você aproveite sua experiência conosco!\n\nAtenciosamente,\nEditMatch");

        emailSender.send(message);
    }
}
