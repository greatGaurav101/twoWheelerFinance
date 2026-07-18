package com.rupyy.lender.controller;

import com.rupyy.lender.client.UserClient;
import com.rupyy.lender.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    //http://localhost:8087/api/v1/user/getUserByid?id=29202a17-bd11-4307-8264-198a4a4b07cf
    @GetMapping("/getUserByid")
    Customer getDetails(@RequestParam UUID id){
        Customer customerDetails = userClient.getDetails(id);

        System.out.println(customerDetails.getMobile());
        System.out.println(customerDetails.getName());
        System.out.println(customerDetails.getEmail());


        return customerDetails;
    }


}
