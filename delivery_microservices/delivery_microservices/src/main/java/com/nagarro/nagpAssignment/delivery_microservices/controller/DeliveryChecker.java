package com.nagarro.nagpAssignment.delivery_microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.nagarro.nagpAssignment.delivery_microservices.model.Order;
import com.nagarro.nagpAssignment.delivery_microservices.status.DeliveryStatus;
import com.nagarro.nagpAssignment.delivery_microservices.status.PaymentStatus;

@Component
public class DeliveryChecker {
	public static final Logger log = LoggerFactory.getLogger(DeliveryChecker.class);
	private static final boolean deliveryAvailable = true;

	private DeliveryProcessor processor;

	@Autowired
	public DeliveryChecker(DeliveryProcessor processor) {
		this.processor = processor;
	}

	@StreamListener(DeliveryProcessor.PAYMENT_DONE)
	public void checkAndSortLoans(Order order) {
		log.info("{} {} for ${} for {}", order.getPayment_status(), order.getOrder_id(), order.getTotal_bill(),
				order.getProduct_id());
		System.out.println(order.toString());
		if (deliveryAvailable) {
			order.setDelivery_status(DeliveryStatus.DELIVERED.name());
			processor.delivered().send(message(order));
		} else {
			order.setDelivery_status(DeliveryStatus.UNDELIVERED.name());
			processor.undelivered().send(message(order));
		}
		System.out.println("I AM IN Delivery");
		System.out.println(order.toString());
		log.info("{} {} for ${} for {}", order.getPayment_status(), order.getOrder_id(), order.getTotal_bill(),
				order.getProduct_id());

	}

	private static final <T> Message<T> message(T val) {
		return MessageBuilder.withPayload(val).build();
	}
}
