package com.nagarro.nagpAssignment.order_microservice.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagpAssignment.order_microservice.exception.RecordNotFoundException;
import com.nagarro.nagpAssignment.order_microservice.model.Order;
import com.nagarro.nagpAssignment.order_microservice.service.OrderProductService;

@RestController
@RequestMapping("order")
public class OrderProductController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	OrderProductService orderProductService;

	@Autowired
	CurrencyExchangeServiceProxy proxy;

	@PostMapping("/createOrder")
	public String createOrder(@Valid @RequestBody Order newOrder) {
//		System.out.println(newOrder.toString());
//		return new ResponseEntity(newOrder, HttpStatus.OK);
		int price = proxy.getProductAvailability(newOrder.getProductId(), newOrder.getQuantity());
		if (price > 0) {
			newOrder.setTotalBill(newOrder.getQuantity() * price);
			Order order = orderProductService.orderAProduct(newOrder);
			orderProductService.sendForPayment(order);
			return "order submitted";
		} else {
			throw new RecordNotFoundException("Product is out of stock");
		}

//		Order order = orderProductService.orderAProduct(newOrder);
//		orderCreatedSource.employeeRegistration().send(MessageBuilder.withPayload(order).build());
//		System.out.println("IN ORDER");
//		System.out.println(order.toString());
//		return "order submitted";

	}

	@GetMapping("/getAllOrders/{userId}")
	public ResponseEntity<List<Order>> getAllorder(@PathVariable int userId) {

		List<Order> orderList = orderProductService.getAllOrderOfUser(userId);
		if (orderList.size() == 0) {
			throw new RecordNotFoundException("No order exist for user :" + userId);
		} else {
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		}
	}

	// @PostMapping("/{user_id}/order/product/{product_id}/quantity/{quantity}")

//	@GetMapping("/product/{product_id}/quantity/{quantity}")
//	public Boolean OrderProduct(@PathVariable("product_id") int product_id, @PathVariable("quantity") int quantity) {
//		int response = proxy.getProductAvailability(product_id, quantity);
//		logger.info("{}", response);
//		return response; // if (response == true) //
//		orderProductService.orderAProduct(user_id, product_id);
//	}

//	  @PostMapping("/getAllOrder/{user_id}") public List<Order>
//	  getAllOrder(@PathVariable int user_id) { return
//	  orderProductService.getAllOrderOfUser(user_id); }

}
