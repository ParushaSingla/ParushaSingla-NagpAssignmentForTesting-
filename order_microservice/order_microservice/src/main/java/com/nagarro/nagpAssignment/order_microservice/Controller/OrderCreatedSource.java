package com.nagarro.nagpAssignment.order_microservice.Controller;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCreatedSource {

	@Input("output")
	SubscribableChannel sourceOfOrderItem();

	@Input("deliveryStatusUpdated")
	SubscribableChannel deliveryStatusUpdated();

	@Output("employeeRegistrationChannel")
	MessageChannel employeeRegistration();

	@Output("updateDeliveryChannel")
	MessageChannel updateDelivery();
}