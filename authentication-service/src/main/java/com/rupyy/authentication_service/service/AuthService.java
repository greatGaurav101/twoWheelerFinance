package com.rupyy.authentication_service.service;

import com.rupyy.authentication_service.dto.SignUpUserDTO;
import com.rupyy.authentication_service.entity.SignUpUser;
import com.rupyy.authentication_service.repository.RegisterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private RegisterRepository registerRepository;

    public void registerUser(SignUpUserDTO userDTO) {

        SignUpUser signUpUser = new SignUpUser();
        BeanUtils.copyProperties(userDTO, signUpUser);
        registerRepository.save(signUpUser);
    }
}