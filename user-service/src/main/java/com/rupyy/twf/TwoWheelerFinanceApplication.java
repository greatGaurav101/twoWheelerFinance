package com.rupyy.twf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TwoWheelerFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoWheelerFinanceApplication.class, args);
	}

}
