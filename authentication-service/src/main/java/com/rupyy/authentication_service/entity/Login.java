package com.rupyy.authentication_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "logindb")
@Getter @Setter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "email",nullable = false,unique = false)
    private String email;

    private String password;
}
