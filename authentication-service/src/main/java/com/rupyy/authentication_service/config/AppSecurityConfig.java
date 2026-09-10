package com.rupyy.authentication_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

@Bean
    public SecurityFilterChain securityConfig(HttpSecurity http){
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                req -> {
                    req.requestMatchers("/api/v1/auth/reg").permitAll()
                            .anyRequest().authenticated();
                }
        );
        return http.build();
    }

}
