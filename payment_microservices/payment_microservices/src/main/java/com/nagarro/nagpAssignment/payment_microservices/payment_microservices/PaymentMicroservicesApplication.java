package com.nagarro.nagpAssignment.payment_microservices.payment_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import com.nagarro.nagpAssignment.payment_microservices.payment_microservices.controller.PaymentProcessor;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableBinding(PaymentProcessor.class)
public class PaymentMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroservicesApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
