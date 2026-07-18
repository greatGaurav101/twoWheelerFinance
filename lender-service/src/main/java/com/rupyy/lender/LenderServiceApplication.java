package com.rupyy.lender;

import com.rupyy.customer_events.data.CustomerCreatedEvent;
import com.rupyy.lender.dto.CustomerRequestDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class LenderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LenderServiceApplication.class, args);
	}

	@KafkaListener(topics = "order_topic_3", groupId = "customer-group-3")
	public void consume(CustomerCreatedEvent customerCreatedEvent) {

		System.out.println("*****"+ customerCreatedEvent.getName() + "*****");

	}

}
