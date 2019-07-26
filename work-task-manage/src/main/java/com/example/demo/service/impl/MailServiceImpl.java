package com.example.demo.service.impl;

import com.example.demo.entity.Mail;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;


@Service
public class MailServiceImpl implements MailService {

  @Autowired
   private JavaMailSender sender;

    @Value("${spring.mail.username}")
    String from;
    @Override
    public void sendMail(Mail mail) {
        try {
            //JavaMailSenderImpl sender = new JavaMailSenderImpl();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from); // 邮件发送者
            message.setTo(mail.getAddress()); // 邮件接受者
            message.setSubject(mail.getSubject()); // 主题
            message.setText(mail.getContent()); // 内容
            sender.send(message);//发送邮件
        }
        catch (Exception e){
            throw new RuntimeException("邮件发送失败"+e.toString());
        }
    }
}
