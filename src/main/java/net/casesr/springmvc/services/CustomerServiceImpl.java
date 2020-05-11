package net.casesr.springmvc.services;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import net.casesr.springmvc.domain.Customer;
import net.casesr.springmvc.domain.DomainObject;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {
	
	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}

	@Override
	public Customer getById(Integer id) {
		return (Customer) super.getById(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return (Customer) super.saveOrUpdate(domainObject);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

}
