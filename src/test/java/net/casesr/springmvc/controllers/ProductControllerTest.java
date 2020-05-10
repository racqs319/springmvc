package net.casesr.springmvc.controllers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.casesr.springmvc.domain.Product;
import net.casesr.springmvc.services.ProductService;

public class ProductControllerTest {
	
	@Mock // Mockito mock object
	private ProductService productService;
	
	@InjectMocks // Sets up controller, and injects mock objects into it
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this); // Initializes controller and mocks
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testList() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());
		
		// specific Mockito interaction, tell stub to return list of products
		when(productService.listAll()).thenReturn((List) products);
		
		mockMvc.perform(get("/products"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products/list"))
			   .andExpect(model().attribute("products", hasSize(2)));
	}
	
	@Test
	public void testView() throws Exception {
		Integer id = 1;
		
		when(productService.getById(id)).thenReturn(new Product());
		
		mockMvc.perform(get("/products/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products/productDetails"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testEdit() throws Exception {
		Integer id = 1;
		
		when(productService.getById(id)).thenReturn(new Product());
		
		mockMvc.perform(get("/products/1/edit"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products/createOrUpdateProductForm"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testNewProduct() throws Exception {
		verifyNoInteractions(productService);
		
		mockMvc.perform(get("/products/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products/createOrUpdateProductForm"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Integer id = 1;
		String description = "Test product description";
		BigDecimal price = new BigDecimal("12.99");
		String imageUrl = "sample.com";
		
		Product returnProduct = new Product();
		returnProduct.setId(id);
		returnProduct.setDescription(description);
		returnProduct.setPrice(price);
		returnProduct.setImageUrl(imageUrl);
		
		when(productService.saveOrUpdate(any())).thenReturn(returnProduct);
		
		mockMvc.perform(post("/product")
							.param("id", "1")
							.param("description", description)
							.param("price", "12.99")
							.param("imageUrl", "sample.com"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/products/1"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)))
			   .andExpect(model().attribute("product", hasProperty("id", is(id))))
			   .andExpect(model().attribute("product", hasProperty("description", is(description))))
			   .andExpect(model().attribute("product", hasProperty("price", is(price))))
			   .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));
		
		ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
		verify(productService).saveOrUpdate(boundProduct.capture());
		
		assertEquals(id, boundProduct.getValue().getId());
		assertEquals(description, boundProduct.getValue().getDescription());
		assertEquals(price, boundProduct.getValue().getPrice());
		assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
	}
	
	@Test
	public void testDelete() throws Exception {
		Integer id = 1;
		
		mockMvc.perform(get("/products/1/delete"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/products"));
		
		verify(productService, times(1)).deleteById(id);
	}

}
