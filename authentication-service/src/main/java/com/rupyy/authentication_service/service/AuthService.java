package com.rupyy.authentication_service.service;

import com.rupyy.authentication_service.dto.SignUpUserDTO;
import com.rupyy.authentication_service.entity.SignUpUser;
import com.rupyy.authentication_service.repository.RegisterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private AuthService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private RegisterRepository registerRepository;

    public void registerUser(SignUpUserDTO userDTO) {

        SignUpUser signUpUser = new SignUpUser();
        BeanUtils.copyProperties(userDTO, signUpUser);

        signUpUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        registerRepository.save(signUpUser);
    }
}