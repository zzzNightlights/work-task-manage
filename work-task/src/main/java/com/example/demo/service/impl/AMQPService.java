package com.example.demo.service.impl;

import com.example.demo.entity.Mail;
import com.example.demo.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AMQPService {
    @Autowired
    MailService mailService;
    @RabbitListener(queues = "queue.work-task")
    public void receiveMsg (Mail mail){
        mailService.sendMail(mail);
    }

}
