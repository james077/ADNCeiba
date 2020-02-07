package co.com.ceiba.adn.warehouse.infrastructure.adapter.repository.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.entity.ProductEntity;

@Repository
public interface ProductRepositoryJPA extends CrudRepository<ProductEntity, Integer> {

	/**
	 * Permite crear o actualizar un producto
	 * @param product
	 */
	void save(Product product);

	
	/**
	 * Permite buscar un producto por id
	 * @param id
	 */
	ProductEntity findProductById(Integer id);
	
	/**
	 * Retorna todos los productos
	 * @param void
	 */
	List<ProductEntity> findAll();
	
	/**
	 * Permite eliminar un producto
	 * @param id
	 */
	void deleteById(Integer id);
}
