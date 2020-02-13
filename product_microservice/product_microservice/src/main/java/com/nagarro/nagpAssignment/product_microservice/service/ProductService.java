package com.nagarro.nagpAssignment.product_microservice.service;

import java.util.List;

import com.nagarro.nagpAssignment.product_microservice.model.Product;

public interface ProductService {
	public void addProductToInventory(Product product);

	public Product deleteProductFromInventory(int productId);

	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public void addProductToCart(Product product);

	public void removeProductFromCart(Product product);

	public int getProductAvailability(int productId, int quantity);

}
