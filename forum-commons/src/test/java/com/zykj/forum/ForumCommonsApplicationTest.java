package com.zykj.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;

@SpringBootTest(classes = ForumCommonsApplication.class)
public class ForumCommonsApplicationTest {
    @Resource
    JavaMailSenderImpl javaMailSender;
    @Test
    void testEmail() throws MessagingException {
        MimeMailMessage mimeMailMessage = new MimeMailMessage(javaMailSender.createMimeMessage());
        mimeMailMessage.setFrom("zykjforum@163.com");
        mimeMailMessage.setSubject("注册验证码");
        mimeMailMessage.setSentDate(new Date());
        mimeMailMessage.setTo("1246269795@qq.com");
        mimeMailMessage.getMimeMessageHelper().setText("<font color='red'>ssssadf</font>",true);
        javaMailSender.send(mimeMailMessage.getMimeMessageHelper().getMimeMessage());
    }
}
