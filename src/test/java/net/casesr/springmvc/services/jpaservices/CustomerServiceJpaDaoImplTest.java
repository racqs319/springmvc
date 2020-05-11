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
import net.casesr.springmvc.domain.Customer;
import net.casesr.springmvc.domain.User;
import net.casesr.springmvc.services.CustomerService;

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
	
	@Test
	public void testWithUser() throws Exception {
		Customer customer = new Customer();
		User user = new User();
		user.setUsername("myUsername");
		user.setPassword("myPassword");
		customer.setUser(user);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		
		assertNotNull(savedCustomer.getUser().getId());
		assertNotNull(savedCustomer.getUser().getEncryptedPassword());
	}

}
