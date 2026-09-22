package com.rupyy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;


@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class UserServiceApplication {  //Bootstarping class

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
