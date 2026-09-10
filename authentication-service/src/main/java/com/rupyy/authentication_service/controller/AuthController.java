package com.rupyy.authentication_service.controller;

import com.rupyy.authentication_service.dto.SignUpUserDTO;
import com.rupyy.authentication_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/reg")
    public String registerUser(@RequestBody SignUpUserDTO userDTO){
        authService.registerUser(userDTO);
        return "saved";

    }
}
