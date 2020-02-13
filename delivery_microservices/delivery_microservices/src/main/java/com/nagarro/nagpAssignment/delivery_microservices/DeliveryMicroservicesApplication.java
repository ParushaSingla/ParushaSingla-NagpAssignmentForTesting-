package com.nagarro.nagpAssignment.delivery_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.nagarro.nagpAssignment.delivery_microservices.controller.DeliveryProcessor;

@SpringBootApplication
@EnableBinding(DeliveryProcessor.class)
public class DeliveryMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryMicroservicesApplication.class, args);
	}

}
