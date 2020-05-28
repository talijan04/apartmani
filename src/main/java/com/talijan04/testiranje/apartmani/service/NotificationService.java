package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    private NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification() throws MailException {
        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("talijan04@gmail.com");
        mail.setFrom("talijan04@gmail.com");
        mail.setSubject("Rezervacija Apartmani GRAND");
        mail.setText("Uspesno ste izvrsili registraciju apartmana");
        javaMailSender.send(mail);
    }

}
