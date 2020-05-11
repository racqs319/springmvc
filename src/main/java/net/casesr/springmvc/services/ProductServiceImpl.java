package net.casesr.springmvc.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import net.casesr.springmvc.domain.DomainObject;
import net.casesr.springmvc.domain.Product;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}

	@Override
	public Product getById(Integer id) {
		return (Product) super.getById(id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		return (Product) super.saveOrUpdate(domainObject);
	}
	
	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

}
