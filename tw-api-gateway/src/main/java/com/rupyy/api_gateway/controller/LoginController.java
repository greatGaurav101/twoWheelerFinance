package com.rupyy.api_gateway.controller;

import com.rupyy.api_gateway.dto.LoginDto;
import com.rupyy.api_gateway.service.Loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    Loginservice authservice;

    //handler method
    @PostMapping()
    public void loginController(@RequestBody LoginDto loginDto){
        authservice.loginService(loginDto);
    }
}
