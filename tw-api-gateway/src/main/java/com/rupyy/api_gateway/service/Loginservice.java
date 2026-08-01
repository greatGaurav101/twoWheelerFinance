package com.rupyy.api_gateway.service;

import com.rupyy.api_gateway.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Loginservice {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    public void loginService(LoginDto loginDto) {


        customerUserDetailsService.loadUserByUsername(loginDto.getUsername());
    }
}
