package co.com.ceiba.adn.warehouse.infrastructure.adapter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Integer id;

	@Column(name = "name_product")
	private String nameProduct;

	@Column(name = "expiration_Date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "date_in")
	@Temporal(TemporalType.DATE)
	private Date dateIn;

	@Column(name = "date_out")
	@Temporal(TemporalType.DATE)
	private Date dateOut;

	@Column(name = "length")
	private float length;

	@Column(name = "width")
	private float width;

	@Column(name = "height")
	private float height;

}
