package com.nagarro.nagpAssignment.delivery_microservices.controller;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface DeliveryProcessor {
	String PAYMENT_DONE = "paymentDone";
	String DELIVERED_OUT = "delivered";
	String UNDELIVERED_OUT = "undelivered";

	@Input(PAYMENT_DONE)
	SubscribableChannel sourceOfOrderItem();

	@Output(DELIVERED_OUT)
	MessageChannel delivered();

	@Output(UNDELIVERED_OUT)
	MessageChannel undelivered();
}
