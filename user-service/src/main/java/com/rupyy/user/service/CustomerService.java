package com.rupyy.user.service;

import com.rupyy.customer_events.data.CustomerCreatedEvent;
import com.rupyy.user.constants.AppConstants;
import com.rupyy.user.utils.EmailSender;
import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.dto.APIResponseDTO;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.exception.NotAnyUserFoundException;
import com.rupyy.user.exception.UserNotFoundException;
import com.rupyy.user.repository.CustomerRepository;
import com.rupyy.user.scheduler.UserScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    /**
     * this will save a new customer in database customer table
     * also when a new customer will be created an email,whatsapp notification will be sent to the customer
     */
    public APIResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customerEntity = new Customer();

        log.info("=> created customer with mobile:{} and email:{}", customerRequestDTO.getMobile(), customerRequestDTO.getEmail());

        BeanUtils.copyProperties(customerRequestDTO, customerEntity);
        Customer savedCustomer = customerRepository.save(customerEntity);

        log.info("<= createCustomer: Successfully saved to the database with id:", savedCustomer.getId());

        //  to send email notification once lead is created
        emailSender.sendEmail("amitranjan876@gmail.com",
                "User/Lead created",
                "Greetings : lead id created with leadId:" + savedCustomer.getId() + savedCustomer.getName());

        log.info("Email notification sent for new customer ID: {} to address: {}", savedCustomer.getId(), "amitranjan876@gmail.com");

        //  to send whatsapp notification after lead is created
        whatsappService.sendWhatsAppMessage("+918448055679",
                "Dear Customer,your lead id created with leadId:" + savedCustomer.getId() + savedCustomer.getName());

        log.info("WhatsApp notification sent for new customer ID: {} to number: {}", savedCustomer.getId(), "+918448055679");


        // to send data through cron
        userScheduler.sendScheduledLeads();

        //send message to kafka Topic

        CustomerCreatedEvent createdEvent = new CustomerCreatedEvent();
        createdEvent.setName(customerRequestDTO.getName());
        createdEvent.setEmail(customerRequestDTO.getEmail());
        createdEvent.setMobile(customerRequestDTO.getMobile());
        createdEvent.setPanNumber(customerRequestDTO.getPanNumber());

        kafkaTemplate.send(AppConstants.TOPIC, createdEvent);

        log.info("Published CustomerCreatedEvent to Kafka topic '{}' for customer ID: {}", AppConstants.TOPIC, savedCustomer.getId());


        APIResponseDTO<Object> CustomerResponseDTO = new APIResponseDTO<>();
        BeanUtils.copyProperties(savedCustomer, CustomerResponseDTO);

        return CustomerResponseDTO;
    }

    /* to get user by id(UUID) */
    public Customer getUserDetails(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty()){
            log.error("user not found with ID:{}",id);
            throw new UserNotFoundException("user doesn't exists!");
        }

        log.debug("getUserDetails: Found customer with ID: {}", id);
        return customer.get();
    }

    //this will return all existing user from database
    public List<Customer> getAllUsers() {
        List<Customer> customerList = customerRepository.findAll();
        log.info("getAllUsers: called repository to fetch all users details");

        for (Customer lst : customerList) {
            log.debug("Customer list :- {} "+ lst.getId());
        }


        //.orElseThrow(() -> new NoUserFoundException("no user exists !"));

        List<Customer> st = new ArrayList<>();

        for (Customer cust : customerList) {
            // st.add(cust.getId());

        }
        return customerList;
    }

    public Customer getUserDetailsByMobilenumber(String mobile) {
        Customer customer = customerRepository.findByMobile(mobile)
                .orElseThrow(() -> new NotAnyUserFoundException("User with given mobile no doesn't exists!"));

        return customer;
    }

    public List<Object> getUserDetailsByFirstname(String firstName) {
        List<Object> user = new ArrayList<>();
        Iterable<Customer> cust = customerRepository.findByFirstName(firstName);
        for (Customer customer : cust) {
            user.add(customer.getName());
            user.add(customer.getMobile());
            user.add(customer.getEmail());
        }

        return user;

    }
}