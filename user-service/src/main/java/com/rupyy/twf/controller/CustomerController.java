package com.rupyy.twf.controller;

import com.rupyy.twf.dto.CustomerRequestDTO;
import com.rupyy.twf.dto.APIResponseDTO;
import com.rupyy.twf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    //http://localhost:8085/api/v1/customer/saveCustomer
    @PostMapping("/saveCustomer")
    public ResponseEntity<APIResponseDTO<?>> createCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO){

        APIResponseDTO responseDTO = customerService.createCustomer(customerRequestDTO);

        APIResponseDTO<Object> response = new APIResponseDTO<>();
        response.setMessage("Employee Registered");
        response.setStatusCode(201);
        response.setId(responseDTO.getId());
        response.setLeadsId(responseDTO.getLeadsId());
      //  response.setData(responseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
