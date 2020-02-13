package com.nagarro.nagpAssignment.product_microservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
	@NotNull
	@Min(1)
	private int id;
	@NotNull
	private String name;
	@NotEmpty
	private int price;
	@Min(1)
	private int quantity;
	@NotNull
	private int created_by_adminId;

	public Product() {
	}

	public Product(int id, String name, int price, int created_by_adminId, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.created_by_adminId = created_by_adminId;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCreated_by_adminId() {
		return created_by_adminId;
	}

	public void setCreated_by_adminId(int created_by_adminId) {
		this.created_by_adminId = created_by_adminId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
