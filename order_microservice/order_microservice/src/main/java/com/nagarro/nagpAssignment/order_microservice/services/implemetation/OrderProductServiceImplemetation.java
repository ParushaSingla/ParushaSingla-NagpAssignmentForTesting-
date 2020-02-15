package com.nagarro.nagpAssignment.order_microservice.services.implemetation;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.nagpAssignment.order_microservice.Controller.OrderCreatedSource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.nagarro.nagpAssignment.order_microservice.model.Order;
import com.nagarro.nagpAssignment.order_microservice.service.OrderProductService;

@Service
public class OrderProductServiceImplemetation implements OrderProductService {
	List<Order> ordersList = new ArrayList<Order>();

	@Autowired
	OrderCreatedSource orderCreatedSource;

	@Override
	public Order orderAProduct(Order newOrder) {
		int orderId = ordersList.size();
		Order order = new Order(orderId + 1, newOrder.getUserId(), newOrder.getProductId(), newOrder.getQuantity(),
				newOrder.getTotalBill());
		ordersList.add(order);
		return order;
	}

	@Override
	public List<Order> getAllOrderOfUser(int userId) {

//		ordersList.stream().filter((e) ->e.getUser_id()==user_id).collect(Collectors.toList());
		List<Order> userOrder = new ArrayList<Order>();
		for (Order order : ordersList) {
			if (order.getUserId() == userId) {
				userOrder.add(order);
			}
		}
		return userOrder;
	}

	@Override
	public Order changePaymentStatus(int orderId, String paymentStatus) {
//		ordersList.stream().filter((e) -> e.getOrder_id() == order_id).collect(Collectors.toList());
//		System.out.println("In Change Payment " + ordersList.get(1).toString());
		for (Order order : ordersList) {
			if (order.getOrderId() == orderId) {
				order.setPaymentStatus(paymentStatus);
				return order;
			}
		}
		return null;

	}

	@Override
	public Order changeDeliveryStatus(int orderId, String deliveryStatus) {
		for (Order order : ordersList) {
			if (order.getOrderId() == orderId) {
				order.setDeliveryStatus(deliveryStatus);
				;
				return order;
			}
		}
		return null;
	}

	@Override
	@HystrixCommand(fallbackMethod = "sendPaymentFallback")
	public void sendForPayment(Order order) {
		System.out.println("IN ORDER");
		orderCreatedSource.employeeRegistration().send(MessageBuilder.withPayload(order).build());
		System.out.println(order.toString());
	}

	public void sendPaymentFallback(Order order){
		System.out.println("Unable to send payment message ... rollback db changes");
		System.out.println(order.toString());
		ordersList.remove(order);
	}
}
