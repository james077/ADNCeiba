package co.com.ceiba.adn.warehouse.infrastructure.adapter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.ceiba.adn.warehouse.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_invoice")
	private Integer id;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
//	@OneToOne
//	@JoinColumn(name="id_product",nullable=false)
//	private Product product;
	
	
}
