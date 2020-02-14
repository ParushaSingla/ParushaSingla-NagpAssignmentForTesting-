package com.nagarro.nagpAssignment.order_microservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nagarro.nagpAssignment.order_microservice.Status.DeliveryStatus;
import com.nagarro.nagpAssignment.order_microservice.Status.PaymentStatus;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Order implements Serializable {

	@JsonProperty("order_id")
	@NotNull
	private int orderId;
	@Min(1)
	@NotNull
	@JsonProperty("user_id")
	private int userId;
	@Min(1)
	@JsonProperty("product_id")
	private int productId;
	@Min(1)
	@NotNull
	@Digits(fraction = 10, integer = 10, message ="Not a number")
	private int quantity;
	@JsonProperty("total_bill")
	@Digits(fraction = 10, integer = 10, message ="Not a number")
	private int totalBill;
	@JsonProperty("delivery_status")
	private String deliveryStatus;
	@JsonProperty("payment_status")
	private String paymentStatus;

	public Order() {
	}

	public Order(int order_id, int user_id, int product_id, int quantity, int totalBill) {
		this.orderId = order_id;
		this.userId = user_id;
		this.productId = product_id;
		this.setPaymentStatus(PaymentStatus.PAYMENT_PENDING.name());
		this.setDeliveryStatus(DeliveryStatus.UNDELIVERED.name());
	}

	@Override
	public String toString() {
		return "Order [order_id=" + orderId + ", user_id=" + userId + ", product_id=" + productId + ", quantity="
				+ quantity + ", total_bill=" + totalBill + ", delivery_status=" + deliveryStatus + ", payment_status="
				+ paymentStatus + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {

		if (paymentStatus.equals(PaymentStatus.PAYMENT_DONE.name())
				|| paymentStatus.equals(PaymentStatus.PAYMENT_DECLINED.name())
				|| paymentStatus.equals(PaymentStatus.PAYMENT_PENDING.name())) {
			this.paymentStatus = paymentStatus;
		} else {
			throw new IllegalArgumentException("Cannot set the Payment status to " + paymentStatus);
		}
	}

	public int getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
