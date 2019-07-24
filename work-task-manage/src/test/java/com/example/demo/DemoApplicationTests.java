package com.example.demo;

import com.example.demo.entity.Result;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String from;
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 邮件发送者
        message.setTo("yne2213@qq.com.com"); // 邮件接受者
        message.setSubject("邮件测试"); // 主题
        message.setText("工作任务管理系统测试邮件"); // 内容
        mailSender.send(message);
    }
    @Test
    public void getMailMsg(){
        List<Result> results= userService.findMailUser();
        System.out.println(results.size()+results.toString());
    }
    @Test
    public void getDateTime(){
        System.out.println(new Date().toString());
    }

}
