package co.com.ceiba.adn.warehouse.domain.repository;

import java.util.List;

import co.com.ceiba.adn.warehouse.domain.model.Product;

public interface ProductRepository  {

	
	/**
	 * Permite crear las producto
	 * @param product
	 */
	Product save(Product product);
	
	/**
	 * Buscar un producto por su id
	 * @param id
	 */
	Product findProductByIdProduct(Integer id);
	
	/**
	 * Permite listar todos los producto
	 */
	List<Product> findAll();

	/**
	 * Elimina el producto asociado por id
	 * @return 
	 */
	 void deleteById(Integer id);
}
