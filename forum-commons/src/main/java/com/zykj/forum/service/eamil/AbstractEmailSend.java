package com.zykj.forum.service.eamil;

import com.zykj.forum.service.EmailSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;

public abstract class AbstractEmailSend implements EmailSend {
    protected final Logger log= LoggerFactory.getLogger(this.getClass());
    protected SimpleMailMessage simpleMailMessage;
    protected MimeMailMessage mimeMailMessage;
    private JavaMailSender javaMailSender;
    public AbstractEmailSend(JavaMailSender javaMailSender){
        this(null,null,javaMailSender);
    }
    public AbstractEmailSend(String subject,String from,JavaMailSender javaMailSender){
        this.simpleMailMessage=new SimpleMailMessage();
        this.simpleMailMessage.setSubject(subject);
        this.simpleMailMessage.setFrom(from);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        this.mimeMailMessage=new MimeMailMessage(mimeMessageHelper);
        this.mimeMailMessage.setSubject(subject);
        this.mimeMailMessage.setFrom(from);
        this.javaMailSender=javaMailSender;
    }

    @Override
    public void send(String content, EmailType type, String... to) throws MessagingException {
        switch (type){
            case SIMPLE:
                sendSimple(content,to);
            case MIME:
                sendMime(content,to);
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    protected void send(EmailType emailType){
        switch (emailType){
            case MIME:
                javaMailSender.send(this.mimeMailMessage.getMimeMessage());
                break;
            case SIMPLE:
                javaMailSender.send(this.simpleMailMessage);
                break;
        }
    }
    protected abstract void sendMime(String content, String[] to) throws MessagingException;

    protected abstract void sendSimple(String content, String[] to);
}
