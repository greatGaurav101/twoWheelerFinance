package com.rupyy.user.Test.service;

import com.rupyy.customer_events.data.CustomerCreatedEvent;
import com.rupyy.user.constants.AppConstants;
import com.rupyy.user.controller.utils.EmailSender;
import com.rupyy.user.dto.APIResponseDTO;
import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.exception.NotAnyUserFoundException;
import com.rupyy.user.exception.UserNotFoundException;
import com.rupyy.user.repository.CustomerRepository;
import com.rupyy.user.scheduler.UserScheduler;
import com.rupyy.user.service.CustomerService;
import com.rupyy.user.service.WhatsappService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)//will  enable Mockito in Junit 5
public class CustomerServiceTest {

    @Mock //will mock the dependency layer
    private CustomerRepository customerRepository;

    @Mock
    private EmailSender emailSender;

    @Mock
    private WhatsappService whatsappService;

    @Mock
    private UserScheduler userScheduler;

    @Mock
    private KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getUserDetails_Test(){
        UUID id = UUID.fromString("2f3b28fc-6a5d-46d3-ba6b-8ec00c6768a6");

        Customer customer = new Customer();
        customer.setName("gaurav paul");
        customer.setPanNumber("CQCPD1230M");

        //provide
       when(customerRepository.findById(id))
               .thenReturn(Optional.of(customer));
        //act
        Customer userDetails = customerService.getUserDetails(id);

        //assert
        assertEquals("gaurav paul",userDetails.getName());
    }

    @Test
    void getUserDetails_UserNotFound_Test() {

        UUID id = UUID.randomUUID();

        when(customerRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> customerService.getUserDetails(id));
    }

    @Test
    void getUserdetailsByMobileNo_Test(){

        Customer customer = new Customer();
        customer.setName("gaurav paul");
        customer.setPanNumber("CQCPD1230M");
        customer.setEmail("Pranay@example.com");

        when(customerRepository.findByMobile("ENCRYPTED_9876543211"))
                .thenReturn(Optional.of(customer));

        Customer details = customerService.getUserDetailsByMobilenumber("ENCRYPTED_9876543211");

        assertEquals("Pranay@example.com",details.getEmail());
    }

    @Test
    void getUserDetailsByMobile_NotFound_Test() {

        when(customerRepository.findByMobile("9999999999"))
                .thenReturn(Optional.empty());

        assertThrows(NotAnyUserFoundException.class,
                () -> customerService.getUserDetailsByMobilenumber("9999999999"));
    }

    @Test
    void getAllUsers_Test(){
        List<Customer> customer = Arrays.asList(
                new Customer(),
                new Customer(),
                new Customer()
        );

        when(customerRepository.findAll())
                .thenReturn(customer);

        List<Customer> allUsers = customerService.getAllUsers();

        assertEquals(3,allUsers.size());
    }

    @Test
    void getAllUsers_NotFound_Test(){
        when(customerRepository.findAll());
             //   .thenReturn(Optional.empty());
    }

    @Test
    void createCustomer_Test() {

        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setName("Amit");
        dto.setEmail("amit@gmail.com");
        dto.setMobile("9999999999");
        dto.setPanNumber("ABCDE1234F");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(UUID.randomUUID());
        savedCustomer.setName("Amit");

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(savedCustomer);

        APIResponseDTO response =
                customerService.createCustomer(dto);

        assertNotNull(response);

        verify(customerRepository, times(1))
                .save(any(Customer.class));

        verify(emailSender, times(1))
                .sendEmail(anyString(), anyString(), anyString());

        verify(whatsappService, times(1))
                .sendWhatsAppMessage(anyString(), anyString());

        verify(userScheduler, times(1))
                .sendScheduledLeads();

        verify(kafkaTemplate, times(1))
                .send(eq(AppConstants.TOPIC), any(CustomerCreatedEvent.class));
    }

}
