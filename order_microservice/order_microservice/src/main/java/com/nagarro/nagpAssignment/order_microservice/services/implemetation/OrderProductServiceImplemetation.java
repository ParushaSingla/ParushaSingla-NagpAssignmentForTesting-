package com.nagarro.nagpAssignment.order_microservice.services.implemetation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nagarro.nagpAssignment.order_microservice.model.Order;
import com.nagarro.nagpAssignment.order_microservice.service.OrderProductService;

@Service
public class OrderProductServiceImplemetation implements OrderProductService {
	List<Order> ordersList = new ArrayList<Order>();

	@Override
	public Order orderAProduct(Order newOrder) {
		int orderId = ordersList.size();
		Order order = new Order(orderId + 1, newOrder.getUser_id(), newOrder.getProduct_id(), newOrder.getQuantity(),
				newOrder.getTotal_bill());
		ordersList.add(order);
		return order;
	}

	@Override
	public List<Order> getAllOrderOfUser(int user_id) {

//		ordersList.stream().filter((e) ->e.getUser_id()==user_id).collect(Collectors.toList());
		List<Order> userOrder = new ArrayList<Order>();
		for (Order order : ordersList) {
			if (order.getUser_id() == user_id) {
				userOrder.add(order);
			}
		}
		return userOrder;
	}

	@Override
	public Order changePaymentStatus(int order_id, String payment_status) {
//		ordersList.stream().filter((e) -> e.getOrder_id() == order_id).collect(Collectors.toList());
//		System.out.println("In Change Payment " + ordersList.get(1).toString());
		for (Order order : ordersList) {
			if (order.getOrder_id() == order_id) {
				order.setPayment_status(payment_status);
				return order;
			}
		}
		return null;

	}

	@Override
	public Order changeDeliveryStatus(int order_id, String delivery_status) {
		for (Order order : ordersList) {
			if (order.getOrder_id() == order_id) {
				order.setDelivery_status(delivery_status);
				;
				return order;
			}
		}
		return null;
	}

}
