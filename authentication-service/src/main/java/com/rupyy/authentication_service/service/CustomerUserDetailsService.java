package com.rupyy.authentication_service.service;

import com.rupyy.authentication_service.entity.SignUpUser;
import com.rupyy.authentication_service.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SignUpUser userdetails = registerRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userdetails.getEmail(),
                userdetails.getPassword(),
                Collections.emptyList());
    }
}
