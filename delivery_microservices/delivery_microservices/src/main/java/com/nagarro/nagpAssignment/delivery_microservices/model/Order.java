package com.nagarro.nagpAssignment.delivery_microservices.model;

import com.nagarro.nagpAssignment.delivery_microservices.status.DeliveryStatus;
import com.nagarro.nagpAssignment.delivery_microservices.status.PaymentStatus;

public class Order {

	private int order_id;
	private int user_id;
	private int product_id;
	private int quantity;
	private int total_bill;
	private String delivery_status;
	private String payment_status;

	public Order() {
	}

	public Order(int order_id, int user_id, int product_id, int quantity, int total_bill) {
		this.order_id = order_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.setPayment_status(PaymentStatus.PAYMENT_PENDING.name());
		this.setDelivery_status(DeliveryStatus.UNDELIVERED.name());
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", product_id=" + product_id + ", quantity="
				+ quantity + ", total_bill=" + total_bill + ", delivery_status=" + delivery_status + ", payment_status="
				+ payment_status + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {

		if (payment_status.equals(PaymentStatus.PAYMENT_DONE.name())
				|| payment_status.equals(PaymentStatus.PAYMENT_DECLINED.name())
				|| payment_status.equals(PaymentStatus.PAYMENT_PENDING.name())) {
			this.payment_status = payment_status;
		} else {
			throw new IllegalArgumentException("Cannot set the Payment status to " + payment_status);
		}
	}

	public int getTotal_bill() {
		return total_bill;
	}

	public void setTotal_bill(int total_bill) {
		this.total_bill = total_bill;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
