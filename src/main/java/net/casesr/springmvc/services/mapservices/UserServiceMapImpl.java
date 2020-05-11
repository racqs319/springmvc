package net.casesr.springmvc.services.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import net.casesr.springmvc.domain.DomainObject;
import net.casesr.springmvc.domain.User;
import net.casesr.springmvc.services.UserService;

@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public User getById(Integer id) {
		return (User) super.getById(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		return (User) super.saveOrUpdate(domainObject);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}

}
