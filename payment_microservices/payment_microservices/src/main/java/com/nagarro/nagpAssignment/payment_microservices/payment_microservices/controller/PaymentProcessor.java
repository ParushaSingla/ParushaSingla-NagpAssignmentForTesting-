package com.nagarro.nagpAssignment.payment_microservices.payment_microservices.controller;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface PaymentProcessor {
	String APPLICATIONS_IN = "output";
	String PAYMENTDONE_OUT = "approved";
	String PAYMENTDECLINED_OUT = "declined";

	@Input(APPLICATIONS_IN)
	SubscribableChannel sourceOfOrderItem();

	@Output(PAYMENTDONE_OUT)
	MessageChannel approved();

	@Output(PAYMENTDECLINED_OUT)
	MessageChannel declined();
}
