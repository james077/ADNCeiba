package co.com.ceiba.adn.warehouse.infrastructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.adn.warehouse.application.service.ProductService;
import co.com.ceiba.adn.warehouse.domain.model.Product;
import io.swagger.annotations.Api;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
@Api(tags = "product")
public class ProductController {

	
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping("/")
	public List<Product> products() {
		return productService.findAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product get(@PathVariable("id") Integer id) {
		return productService.productById(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product obj = productService.saveProduct(product);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable("id") int id) {
		Product obj = productService.productById(id);
		productService.deleteById(id);
		return new ResponseEntity<>(obj, HttpStatus.OK);		
	}

}
