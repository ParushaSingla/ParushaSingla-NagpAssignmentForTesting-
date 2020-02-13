package com.nagarro.nagpAssignment.order_microservice.service;

import java.util.List;

import com.nagarro.nagpAssignment.order_microservice.model.Order;

public interface OrderProductService {

	

	List<Order> getAllOrderOfUser(int user_id);

	Order orderAProduct(Order order);

	Order changePaymentStatus(int order_id, String payment_status);

	Order changeDeliveryStatus(int order_id, String delivery_status);
	
	

}
