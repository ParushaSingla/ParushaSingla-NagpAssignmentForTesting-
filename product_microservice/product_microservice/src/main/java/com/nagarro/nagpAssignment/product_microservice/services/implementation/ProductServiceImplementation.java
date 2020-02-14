package com.nagarro.nagpAssignment.product_microservice.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.nagpAssignment.product_microservice.model.Product;
import com.nagarro.nagpAssignment.product_microservice.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	List<Product> inventory = new ArrayList<Product>();
	List<Product> cart = new ArrayList<Product>();

	@Override
	public void addProductToInventory(Product product) {

		inventory.add(product);
	}

	@Override
	public Product deleteProductFromInventory(int productId) {
		for (Product p : inventory) {
			if (p.getId() == productId) {
				inventory.remove(p);
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		return inventory;
	}

	@Override
	public Product getProductById(int productId) {
		for (Product p : inventory) {
			if (p.getId() == productId) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void addProductToCart(Product product) {

		cart.add(product);

	}

	@Override
	public void removeProductFromCart(Product product) {
		for (Product p : cart) {
			if (p.equals(product)) {
				cart.remove(p);
				break;
			}
		}
	}

	@Override
	public int getProductAvailability(int productId, int quantity) {
		Product pr = new Product(1, "Beauty Product", 100, 1, 2);
		inventory.add(pr);
		for (Product p : inventory) {
			if (p.getId() == productId) {
				if (p.getQuantity() >= quantity) {
					return p.getPrice();
				}
			}
		}
		return 0;
	}

}
