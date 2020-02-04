package co.com.ceiba.adn.warehouse.infrastructure.adapter.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.domain.repository.ProductRepository;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.entity.ProductEntity;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.mapper.ProductMapper;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.repository.jpa.ProductRepositoryJPA;

@Repository
@Transactional
public class ProductImplementation implements ProductRepository{
	
	
	private ProductRepositoryJPA productRepositoryJPA;
	
	public ProductImplementation(ProductRepositoryJPA productRepositoryJPA) {
		this.productRepositoryJPA = productRepositoryJPA;
	}
	
	@Override
	public Product save (Product product) {
		return ProductMapper.toDomain(
				productRepositoryJPA.save(ProductMapper.toEntity(product))
				);
	}
	

	@Override
	public Product findProductByIdProduct(Integer id) {
		return ProductMapper.toDomain(productRepositoryJPA.findProductById(id));	
	}



	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		List<ProductEntity> entities= productRepositoryJPA.findAll();
		
		for(ProductEntity pe: entities) {
			products.add(ProductMapper.toDomain(pe));
		}
		
		return products;
	}

	@Override
	public void deleteById(Integer id) {		
		productRepositoryJPA.deleteById(id);
				
	}
}
	
	
