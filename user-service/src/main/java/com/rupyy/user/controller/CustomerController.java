package com.rupyy.user.controller;

import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.dto.APIResponseDTO;
import com.rupyy.user.entity.Customer;
import com.rupyy.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    //http://localhost:8085/api/v1/customer/saveCustomer
    @PostMapping("/saveCustomer")
    public ResponseEntity<APIResponseDTO<?>> createCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO) {

        APIResponseDTO responseDTO = customerService.createCustomer(customerRequestDTO);

        APIResponseDTO<Object> response = new APIResponseDTO<>();
        response.setMessage("Employee Registered");
        response.setStatusCode(201);
        response.setId(responseDTO.getId());
        response.setLeadsId(responseDTO.getLeadsId());
        //  response.setData(responseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    String getUserId(){
        List<UUID> userId = customerService.getUserId();
        System.out.println(userId);
        return null;
    }

    /* to get user by id(UUID) */
    //http://localhost:8085/api/v1/customer/getUserPincode
    @GetMapping("/getUserByid")
    Customer getDetails(@RequestParam UUID id){
        Customer userDetails = customerService.getUserDetails(id);
        return userDetails;
    }

}
