package com.rupyy.twf.service;

import com.rupyy.twf.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Autowired
    private TwilioConfig twilioConfig;

    public String sendWhatsAppMessage(String to,String body){
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(twilioConfig.getFromWhatsappNumber()),
                body).create();

        return "Message sent,STD: " + message.getAccountSid();

    }

}
