package com.example.demo.schedule;

import com.example.demo.entity.Mail;
import com.example.demo.entity.Result;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSchedule {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "0 30 9 * * 1-5")
    public void mailPush(){
        Mail mail = new Mail();
        List<Result> results = userService.findMailUser();
        if (results.size()>0){
            for (Result result: results
            ) {
                mail.setAddress(result.getMail());
                mail.setSubject("【工作任务管理系统提醒邮件】-【系统消息】");
                mail.setContent(result.getName()+"你好，你有"+result.getCountNum()+"条工作任务已经delay，请尽快完成。");
                rabbitTemplate.convertAndSend("exchange.work-task","work-task",mail);
            }
        }
    }
}
