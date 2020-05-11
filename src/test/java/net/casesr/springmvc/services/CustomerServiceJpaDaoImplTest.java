package net.casesr.springmvc.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.casesr.springmvc.config.JpaIntegrationConfig;
import net.casesr.springmvc.domain.Customer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {
	
	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Test
	public void testList() throws Exception {
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) customerService.listAll();
		
		assertEquals(3, customers.size());
	}

}
