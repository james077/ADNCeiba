package co.com.ceiba.adn.warehouse.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.adn.warehouse.application.service.ProductService;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.repository.ProductImplementation;

@Configuration
public class BeanConfiguration {

	@Bean
	public ProductService getProductService(ProductImplementation productImplementation) {
		return new ProductService(productImplementation);
	}
}
