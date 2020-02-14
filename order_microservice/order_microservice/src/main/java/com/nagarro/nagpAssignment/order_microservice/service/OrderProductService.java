package com.nagarro.nagpAssignment.order_microservice.service;

import java.util.List;

import com.nagarro.nagpAssignment.order_microservice.model.Order;

public interface OrderProductService {


    List<Order> getAllOrderOfUser(int userId);

    Order orderAProduct(Order order);

    void sendForPayment(Order order);

    Order changePaymentStatus(int orderId, String paymentStatus);

    Order changeDeliveryStatus(int orderId, String deliveryStatus);


}
