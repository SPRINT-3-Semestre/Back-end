package com.example.EditMatch.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendWelcomeEmail(String toEmail,String name, String last_name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Bem-vindo ao nosso sistema!");
        message.setText("Olá, " + name + " " + last_name +"\n\nBem-vindo ao nosso sistema. Esperamos que você aproveite sua experiência conosco!\n\nAtenciosamente,\nEditMatch");
        emailSender.send(message);
    }

    public void sendNewProjectEmail(String toEmail,String name, String last_name, String nameProject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Novo projeto!");
        message.setText("Olá, " + name + " " + last_name +"\n\nVocê criou um novo projeto:  " + nameProject + ".\n\nAtenciosamente,\nEditMatch");
        emailSender.send(message);
    }

    public void sendAssociateEditorEmail(String toEmail,String name, String nameProject, String link){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Novo video para editar!");
        message.setText("Olá, " + name + " " + "\n\nVocê foi associado a um novo projeto:  " + nameProject + "\n\nLink do projeto: " + link + ".\n\nAtenciosamente,\nEditMatch");
        emailSender.send(message);
    }
    public void sendFinishOrderEmail(String toEmail,String name, String nameProject, String link){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Projeto finalizado!");
        message.setText("Olá, " + name + " " + "\n\nSeu projeto:  " + nameProject + " foi finalizado.\n\nLink do projeto atualizado: " + link + ".\n\nAtenciosamente,\nEditMatch");
        emailSender.send(message);
    }

}
