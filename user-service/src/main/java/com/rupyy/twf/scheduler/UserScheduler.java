package com.rupyy.twf.scheduler;

import com.rupyy.twf.controller.utils.EmailSender;
import com.rupyy.twf.entity.Customer;
import com.rupyy.twf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailSender emailSender;

    @Scheduled(cron = "0 */10 * * * *")
    public List<String> sendScheduledLeads(){
        List<Customer> customers = customerRepository.findAll();
        List<String> collectedId = customers.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        emailSender.sendEmail("amitranjan876@gmail.com",
                "daily cron for all lenders",
                "Hi bajaj,plz find the attatched leads:" + collectedId);

        return collectedId;
    }
}
