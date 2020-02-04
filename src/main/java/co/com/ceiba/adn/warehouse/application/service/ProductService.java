package co.com.ceiba.adn.warehouse.application.service;

import java.util.List;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.domain.repository.ProductRepository;

public class ProductService  {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Product saveProduct(Product product) {
		product.validateExpirationDate(product.getExpirationDate());
		product.validateDateIn();
		return productRepository.save(product);
	}

	
	public Product productById(Integer id) {
		return productRepository.findProductByIdProduct(id);
	}

	public void deleteById(Integer id) {	
		productRepository.deleteById(id);
	}
	
	
}
