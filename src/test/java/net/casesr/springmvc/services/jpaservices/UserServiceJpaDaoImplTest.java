package net.casesr.springmvc.services.jpaservices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.casesr.springmvc.config.JpaIntegrationConfig;
import net.casesr.springmvc.domain.Customer;
import net.casesr.springmvc.domain.User;
import net.casesr.springmvc.services.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	@Disabled
	public void testSaveUser() throws Exception {
		User user = new User();
		
		user.setUsername("someusername");
		user.setPassword("myPassword");
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		
		System.out.println("Encrypted password");
		System.out.println(savedUser.getEncryptedPassword());
	}
	
	@Test
    public void testSaveOfUserWithCustomer() throws Exception {

        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);
        
        assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getVersion());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;

    }

}
