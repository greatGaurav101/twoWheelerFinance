package com.rupyy.user.controller;

import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.dto.APIResponseDTO;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    /** this will save a new customer in database customer table
    also when a new customer will be created an email,WhatsApp notification will be sent to the customer*/
    //http://localhost:8085/api/v1/customer/saveCustomer
    @PostMapping("/saveCustomer")
    public ResponseEntity<APIResponseDTO<?>> createCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO) {

        log.info("=> Received POST request to create a new customer.");
        // IMPORTANT: Avoiding logging the entire requestDTO at INFO level in production
        // if it contains sensitive data (PII). Use DEBUG or TRACE if needed.
        log.debug("Request body: {}", customerRequestDTO);

        APIResponseDTO responseDTO = customerService.createCustomer(customerRequestDTO);

        APIResponseDTO<Object> response = new APIResponseDTO<>();
        response.setMessage("Employee Registered");
        response.setStatusCode(201);
        response.setId(responseDTO.getId());
        response.setLeadsId(responseDTO.getLeadsId());
        //  response.setData(responseDTO);

        log.info("<= Responded to create customer request with customer ID: {}", response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //this will return all existing user from database
    @GetMapping("/get")
    ResponseEntity<List<Customer>> getAllUsers(){
        List<Customer> userId = customerService.getAllUsers();
       // System.out.println(userId);
        return new ResponseEntity<>(userId,HttpStatus.OK);
    }

    /* to get user by id(UUID) */
    //http://localhost:8085/api/v1/customer/getUserPincode
    @GetMapping("/getUserByid")
    Customer getDetails(@RequestParam UUID id){
        Customer userDetails = customerService.getUserDetails(id);
        return userDetails;
    }

    @GetMapping("/getuserbymobile")
    ResponseEntity<Customer> getUserDetailsByMobilenumber(String mobile){
        Customer userDetails = customerService.getUserDetailsByMobilenumber(mobile);

        return new ResponseEntity<>(userDetails,HttpStatus.OK);

    }

    @GetMapping("/getbyname")
    ResponseEntity<?> getUserDetailsByFirstname(String firstName){ //ResponseEntity is used for controller method return type
        List<Object> userDetailsByFirstname = customerService.getUserDetailsByFirstname(firstName);

        return new ResponseEntity<>(userDetailsByFirstname,HttpStatus.OK);
    }


}
