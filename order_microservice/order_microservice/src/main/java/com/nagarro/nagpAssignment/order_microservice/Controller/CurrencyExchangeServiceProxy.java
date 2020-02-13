package com.nagarro.nagpAssignment.order_microservice.Controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-services",url="localhost:8080")
@FeignClient(name = "products")
@RibbonClient(name = "products")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("products/customer/getProductAvailability/{productId}/quantity/{quantity}")
	public int getProductAvailability(@PathVariable("productId") int productId,
			@PathVariable("quantity") int quantity);
}
