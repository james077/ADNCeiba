package co.com.ceiba.adn.warehouse.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.com.ceiba.adn.warehouse.MainApplication;
import co.com.ceiba.adn.warehouse.domain.model.Product;
import co.com.ceiba.adn.warehouse.domain.repository.ProductRepository;
import co.com.ceiba.adn.warehouse.testdatabuilder.ProductTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yaml")
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ProductRepository productRepository;
	
	String nameProduct;
	Product p;
	Integer id;
	
	@Before
	public void createTestProduct() {
		nameProduct = "test Product";
		p = new ProductTestDataBuilder().conNombre(nameProduct).build();
		p=productRepository.save(p);
		id=p.getIdProduct();
	}
	
	@Test
	public void testCreateProduct() throws Exception {
		//Arrange
		String nombre = "Producto Creado via test Rest";
		p.setNameProduct(nombre);
		ObjectMapper objMap = new ObjectMapper();
		objMap.registerModule(new JavaTimeModule());
		objMap.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objMap.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		ObjectWriter objectWriter = objMap.writer().withDefaultPrettyPrinter();
		String payLoad = objectWriter.writeValueAsString(p);
		
		//Act and Assert
		mvc.perform(post("/api/products/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(payLoad))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nameProduct", is(nombre))
			);
	}
		
	@Test
	public void testGivenProducts() throws Exception{
		//Arrange @Before
				
		//act and assert
		mvc.perform(get("/api/products/")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	public void testFindById() throws Exception{
		//Arrange @Before
		
		//act and assert
		mvc.perform(get("/api/products/1")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));	
	}
	
	@Test
	public void testDelete() throws Exception{
		//Arrange @Before
		
		//act and assert
				mvc.perform(delete("/api/products/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	} 
	
}
