package net.casesr.springmvc.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import net.casesr.springmvc.domain.Customer;
import net.casesr.springmvc.services.CustomerService;
import net.casesr.springmvc.services.security.EncryptionService;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {
	
	private EncryptionService encryptionService;
	
	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public List<?> listAll() {
		EntityManager em = emf.createEntityManager();
		
		return em.createQuery("from Customer", Customer.class).getResultList();
	}

	@Override
	public Customer getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		return em.find(Customer.class, id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
			domainObject.getUser().setEncryptedPassword(
					encryptionService.encryptString(domainObject.getUser().getPassword()));
		}
		
		Customer savedCustomer = em.merge(domainObject);
		
		em.getTransaction().commit();
		
		return savedCustomer;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Customer.class, id));
		em.getTransaction().commit();
	}

}
