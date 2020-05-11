package net.casesr.springmvc.services.jpaservices;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.casesr.springmvc.config.JpaIntegrationConfig;
import net.casesr.springmvc.domain.Product;
import net.casesr.springmvc.services.ProductService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void testListMethod() throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) productService.listAll();
		
		assertEquals(5, products.size());
	}

}
