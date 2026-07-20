package com.rupyy.user.scheduler;

import com.rupyy.user.utils.EmailSender;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailSender emailSender;

    @Scheduled(cron = "0 */10 * * * *")
    public List<String> sendScheduledLeads() {
        List<Customer> customers = customerRepository.findAll();
        List<String> collectedId = customers.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        FileWriter fw;
        try {
            fw = new FileWriter("D://abc/t1.txt");
            fw.write(String.valueOf(collectedId));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        emailSender.sendEmail("amitranjan876@gmail.com",
                "daily cron for all lenders",
                "Hi bajaj,plz find the attatched leads:" + fw);

        return collectedId;
    }
}
