package com.nagarro.nagpAssignment.product_microservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagpAssignment.product_microservice.exception.RecordNotFoundException;
import com.nagarro.nagpAssignment.product_microservice.model.Product;
import com.nagarro.nagpAssignment.product_microservice.service.ProductService;

@RestController
@RequestMapping(value = "products/admin/")
public class AdminController {
	@Autowired
	ProductService productService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/addProduct")
	public void addProduct(@Valid @RequestBody Product product) {
		productService.addProductToInventory(product);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/deleteProduct/{productId}")
	public void deleteProduct(@PathVariable int productId) {

		Product p = productService.deleteProductFromInventory(productId);
		if (p == null) {
			throw new RecordNotFoundException("Product Id not exist" + productId);
		}

	}
}
