package com.zykj.forum.controller;

import com.zykj.forum.service.EamilService;
import com.zykj.forum.service.EmailSend;
import com.zykj.forum.service.eamil.EmailVerityService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;


@RestController
public class EmailController {

    @Autowired
    EamilService eamilService;

    @GetMapping("/sendmail")
    public String sendMail(String[] to,Long code) throws MessagingException {
            eamilService.sendMail(code.toString(),to);
            return "ok";
    }
}
