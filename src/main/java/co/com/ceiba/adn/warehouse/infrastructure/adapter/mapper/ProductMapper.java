package co.com.ceiba.adn.warehouse.infrastructure.adapter.mapper;

import org.springframework.stereotype.Component;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.infrastructure.adapter.entity.ProductEntity;

@Component
public final class ProductMapper {

	private ProductMapper() {

	}

	public static Product toDomain(ProductEntity entity) {
		return new Product(entity.getId(), entity.getNameProduct(), entity.getExpirationDate(), entity.getDateIn(),
				entity.getDateOut(), entity.getLength(), entity.getWidth(), entity.getHeight());
	}

	public static ProductEntity toEntity(Product domain) {
		ProductEntity productEntity = new ProductEntity();

		productEntity.setId(domain.getIdProduct());
		productEntity.setNameProduct(domain.getNameProduct());
		productEntity.setExpirationDate(domain.getExpirationDate());
		productEntity.setDateIn(domain.getDateIn());
		productEntity.setDateOut(domain.getDateOut());
		productEntity.setLength(domain.getLength());
		productEntity.setWidth(domain.getWidth());
		productEntity.setHeight(domain.getHeight());

		return productEntity;
	}

}
