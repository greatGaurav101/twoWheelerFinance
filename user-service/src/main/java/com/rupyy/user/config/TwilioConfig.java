package com.rupyy.user.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
public class TwilioConfig {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.whatsapp_number}")
    private String fromWhatsappNumber;

    @PostConstruct  //javax is now replaced with jakarta in newer version of springboot
    //bcz of this annotation this method will automatically run "without calling" as soon as the project starts
    //no need to call this method -- twilio account login will happen through this
    public void initTwilio(){
        Twilio.init(accountSid,authToken);
    }

}
