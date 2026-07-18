package com.rupyy.user.Test;

import com.rupyy.user.entity.Customer;
import com.rupyy.user.repository.CustomerRepository;
import com.rupyy.user.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//will  enable Mockito in Junit 5
public class CustomerServiceTest {

    @Mock //will mock the dependency layer
    private CustomerRepository customerRepository;

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

}
