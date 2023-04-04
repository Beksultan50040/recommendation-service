package com.example.recommendations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig
{
//    String[] to = new String[]{"beksultan.melis@kimep.kz" /*"beksultan.melisov@gmail.com"*/};

    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("beksultan.melis@kimep.kz");
        message.setFrom("beksultan.melisov@gmail.com");
        message.setSubject("Important email");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}