package com.nagarro.nagpAssignment.order_microservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.nagarro.nagpAssignment.order_microservice.Controller.OrderCreatedSource;
import com.nagarro.nagpAssignment.order_microservice.Status.PaymentStatus;
import com.nagarro.nagpAssignment.order_microservice.model.Order;
import com.nagarro.nagpAssignment.order_microservice.service.OrderProductService;

@Component
@EnableBinding(OrderCreatedSource.class)
public class PaymentListener {
	@Autowired
	OrderProductService orderProductService;

	@Autowired
	OrderCreatedSource orderCreatedSource;

	@StreamListener("output")
	public void orederPaymentStatus(Order order) {
		System.out.println("****On listening from output stream****");
		System.out.println(order.toString());
		orderProductService.changePaymentStatus(order.getOrderId(), order.getPaymentStatus());
		if (order.getPaymentStatus().equals(PaymentStatus.PAYMENT_DONE.name()))
			orderCreatedSource.updateDelivery().send(MessageBuilder.withPayload(order).build());
	}
}
