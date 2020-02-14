package com.nagarro.nagpAssignment.product_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagpAssignment.product_microservice.exception.RecordNotFoundException;
import com.nagarro.nagpAssignment.product_microservice.model.Product;
import com.nagarro.nagpAssignment.product_microservice.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping(value = "products/customer")
public class CustomerController {
	@Autowired
	ProductService productService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getProductById/{productId}")
	public Product getProductById(@PathVariable int productId) {
		Product product = productService.getProductById(productId);
		if (product == null) {
			throw new RecordNotFoundException("Product Id not exist" + productId);
		} else {
			return product;
		}
	}

	@HystrixCommand(fallbackMethod = "fallback_method", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	@GetMapping("/getProductAvailability/{productId}/quantity/{quantity}")
	public int getProductAvailability(@PathVariable int productId, @PathVariable int quantity) {
		return productService.getProductAvailability(productId, quantity);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/addProductToCart/{productId}")
	public void addProductToCart(@PathVariable int productId) {
		Product product = getProductById(productId);
		productService.addProductToCart(product);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/removeProductFromCart/{productId}")
	public void removeProductFromCart(@PathVariable int productId) {
		Product product = getProductById(productId);
		productService.removeProductFromCart(product);
	}

	private int fallback_method(int productId, int quantity) {
		return 0;
	}

}
