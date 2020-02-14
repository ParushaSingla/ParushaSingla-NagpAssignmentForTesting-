package com.nagarro.nagpAssignment.order_microservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.nagarro.nagpAssignment.order_microservice.Controller.OrderCreatedSource;
import com.nagarro.nagpAssignment.order_microservice.Status.DeliveryStatus;
import com.nagarro.nagpAssignment.order_microservice.model.Order;
import com.nagarro.nagpAssignment.order_microservice.service.OrderProductService;

@Component
public class DeliveryListener {
	@Autowired
	OrderProductService orderProductService;

	@Autowired
	OrderCreatedSource orderCreatedSource;

	@StreamListener("deliveryStatusUpdated")
	public void orederPaymentStatus(Order order) {
		System.out.println("****On listening from delivery output stream****");
		System.out.println(order.toString());
		orderProductService.changeDeliveryStatus(order.getOrderId(), order.getDeliveryStatus());

	}
}
