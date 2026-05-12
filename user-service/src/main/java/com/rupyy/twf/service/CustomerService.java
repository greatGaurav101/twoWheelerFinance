package com.rupyy.twf.service;

import com.rupyy.twf.constants.AppConstants;
import com.rupyy.twf.controller.utils.EmailSender;
import com.rupyy.twf.dto.CustomerRequestDTO;
import com.rupyy.twf.dto.APIResponseDTO;
import com.rupyy.twf.entity.Customer;
import com.rupyy.twf.repository.CustomerRepository;
import com.rupyy.twf.scheduler.UserScheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    private KafkaTemplate kafkaTemplate;//when DI will happen this reference will have all the details present in hashmap i.e
    //to access kafka what url i have to use.

    public APIResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customerEntity = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customerEntity);
        Customer savedCustomer = customerRepository.save(customerEntity);

        //  to send email notification once lead is created
        emailSender.sendEmail("amitranjan876@gmail.com",
                "User/Lead created",
                "Greetings : lead id created with leadId:" + savedCustomer.getId() + savedCustomer.getName());

        //  to send whatsapp notification after lead is created
        whatsappService.sendWhatsAppMessage("+918448055679",
                "Dear Customer,your lead id created with leadId:" + savedCustomer.getId()+savedCustomer.getName());

        // to send data through cron
        userScheduler.sendScheduledLeads();

        //send message to kafka Topic
        kafkaTemplate.send(AppConstants.TOPIC,customerRequestDTO);

        APIResponseDTO<Object> CustomerResponseDTO = new APIResponseDTO<>();
        BeanUtils.copyProperties(savedCustomer, CustomerResponseDTO);

        return CustomerResponseDTO;
    }


}
