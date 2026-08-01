package com.rupyy.user.utils;

import com.rupyy.user.exception.EmailSentFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject,String message){

        log.info("=> Attempting to send email to '{}' with subject: '{}'", to, subject);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        log.debug("Constructed email message details: {}", mailMessage.toString());

        try{
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            log.error("<= Failed to send email to '{}'. Reason: {}", to, e.getMessage());
            throw new EmailSentFailException("Email sent failed!");
        }


        log.info("<= Successfully sent email to '{}'", to);


    }

}
