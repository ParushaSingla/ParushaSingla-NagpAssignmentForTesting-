package com.nagarro.nagpAssignment.payment_microservices.payment_microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.nagarro.nagpAssignment.payment_microservices.payment_microservices.model.Order;
import com.nagarro.nagpAssignment.payment_microservices.payment_microservices.status.PaymentStatus;

@Component
public class PaymentChecker {

	public static final Logger log = LoggerFactory.getLogger(PaymentChecker.class);
	private static final Long ACCOUNT_BALANCE = 10L;
	private PaymentProcessor processor;

	@Autowired
	public PaymentChecker(PaymentProcessor processor) {
		this.processor = processor;
	}

	@StreamListener(PaymentProcessor.APPLICATIONS_IN)
	public void checkAndSortLoans(Order order) {
		log.info("{} {} for ${} for {}", order.getPayment_status(), order.getOrder_id(), order.getTotal_bill(),
				order.getProduct_id());
		System.out.println(order.toString());
		if (order.getTotal_bill() > ACCOUNT_BALANCE) {
			order.setPayment_status(PaymentStatus.PAYMENT_DECLINED.name());
			processor.declined().send(message(order));
		} else {
			order.setPayment_status(PaymentStatus.PAYMENT_DONE.name());
			processor.approved().send(message(order));
		}
		System.out.println("I AM IN PAYMENT");
		System.out.println(order.toString());
		log.info("{} {} for ${} for {}", order.getPayment_status(), order.getOrder_id(), order.getTotal_bill(),
				order.getProduct_id());

	}

	private static final <T> Message<T> message(T val) {
		return MessageBuilder.withPayload(val).build();
	}
}
