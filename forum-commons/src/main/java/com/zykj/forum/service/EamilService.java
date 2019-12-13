package com.zykj.forum.service;

import com.zykj.forum.service.eamil.EmailVerityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Service
public class EamilService {

    @Resource
    JavaMailSender javaMailSender;

    EmailSend emailSend;
    @Value("${spring.mail.username}")
    String from;

    @Async  //异步任务
    public void sendMail(String code ,String[] to) throws MessagingException {
        getEmailSender().sendMimeMessage(code,to);
    }

    private synchronized EmailSend getEmailSender(){
        Assert.notNull(this.javaMailSender,"javaMailSender must not null");
        if(this.emailSend==null)
            this.emailSend=new EmailVerityService(this.javaMailSender,this.from);
        return this.emailSend;
    }
}
