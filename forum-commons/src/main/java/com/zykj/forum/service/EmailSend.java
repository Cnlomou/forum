package com.zykj.forum.service;

import javax.mail.MessagingException;

public interface EmailSend {
    void send(String content,EmailType type, String... to) throws MessagingException;
    void sendSimpleMessage(String content, String... to) throws MessagingException;
    void sendMimeMessage(String content,String...to) throws MessagingException;
    enum EmailType{
        SIMPLE,
        MIME
    }
}
