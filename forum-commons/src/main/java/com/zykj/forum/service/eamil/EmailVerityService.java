package com.zykj.forum.service.eamil;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.util.Date;

public class EmailVerityService extends AbstractEmailSend {
    private final static String PREFIX="<font color='red'>";
    private final static String SUFFIX="</font>";

    public EmailVerityService(JavaMailSender javaMailSender,String from) {
        this("注册验证码",from,javaMailSender);
    }

    public EmailVerityService(String subject, String from, JavaMailSender javaMailSender) {
        super(subject, from,javaMailSender);
    }

    @Override
    protected void sendMime(String content, String[] to) throws MessagingException {
        this.setTo(to);
        this.setText(paseText(content));
        this.mimeMailMessage.setSentDate(new Date());
        if(log.isInfoEnabled())
            log.info("send mail message {}",content);
        send(EmailType.MIME);
    }
    public void setSubject(String subject){
        this.mimeMailMessage.setSubject(subject);
    }
    public void setFrom(String from){
        this.mimeMailMessage.setFrom(from);
    }
    public void setSentDate(Date sentDate){
        this.mimeMailMessage.setSentDate(sentDate);
    }
    private void setText(String text) throws MessagingException {
        this.mimeMailMessage.getMimeMessageHelper().setText(text,true);
    }
    private void setTo(String...to){
        this.mimeMailMessage.setTo(to);
    }
    private String paseText(String text){
        return PREFIX + text + SUFFIX;
    }
    @Override
    protected void sendSimple(String content, String[] to) {
        throw new IllegalStateException("not support method");
    }

    @Override
    public void sendSimpleMessage(String content, String... to) {
        sendSimple(content,to);
    }

    @Override
    public void sendMimeMessage(String content, String... to) throws MessagingException {
        this.sendMime(content,to);
    }
}
