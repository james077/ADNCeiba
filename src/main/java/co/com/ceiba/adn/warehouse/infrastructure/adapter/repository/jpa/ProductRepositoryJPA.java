package co.com.ceiba.adn.warehouse.infrastructure.adapter.repository.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.entity.ProductEntity;

@Repository
public interface ProductRepositoryJPA extends CrudRepository<ProductEntity, Integer> {

	void save(Product product);

	ProductEntity findProductById(Integer id);
	
	List<ProductEntity> findAll();
	
	void deleteById(Integer id);
}
