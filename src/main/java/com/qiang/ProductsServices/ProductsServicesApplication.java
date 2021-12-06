package com.qiang.ProductsServices;

import com.qiang.ProductsServices.command.api.events.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServicesApplication.class, args);
	}
	@Autowired
	public void configure(EventProcessingConfigurer configurer){
		configurer.registerListenerInvocationErrorHandler(
				"product",
				configuration -> new ProductServiceEventsErrorHandler()
		);
	}
}
