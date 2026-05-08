package com.rupyy.twf.service;

import com.rupyy.twf.controller.utils.EmailSender;
import com.rupyy.twf.dto.CustomerRequestDTO;
import com.rupyy.twf.dto.APIResponseDTO;
import com.rupyy.twf.entity.Customer;
import com.rupyy.twf.repository.CustomerRepository;
import com.rupyy.twf.scheduler.UserScheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private WhatsappService whatsappService;

    @Autowired
    private UserScheduler userScheduler;

    public APIResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customerEntity = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customerEntity);
        Customer savedCustomer = customerRepository.save(customerEntity);

//        to send email notification once lead is created
        emailSender.sendEmail("amitranjan876@gmail.com",
                "User created",
                "Hello How are you!");

//        to send whatsapp notification
      // whatsappService.sendWhatsAppMessage("+918448055679","Hello");

        // to send cron
        userScheduler.sendScheduledLeads();

        APIResponseDTO<Object> CustomerResponseDTO = new APIResponseDTO<>();
        BeanUtils.copyProperties(savedCustomer, CustomerResponseDTO);

        return CustomerResponseDTO;
    }

}
