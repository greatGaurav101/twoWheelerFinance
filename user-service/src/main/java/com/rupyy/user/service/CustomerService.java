package com.rupyy.user.service;

import com.rupyy.user.constants.AppConstants;
import com.rupyy.user.controller.utils.EmailSender;
import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.dto.APIResponseDTO;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.repository.CustomerRepository;
import com.rupyy.user.scheduler.UserScheduler;
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
