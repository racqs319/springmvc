package net.casesr.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.casesr.springmvc.domain.Customer;
import net.casesr.springmvc.services.CustomerService;

@Controller
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(final CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping({"/customers", "/customers/list"})
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAll());
		
		return "customers/list";
	}
	
	@GetMapping("/customers/{id}")
	public String showCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getById(id));
		
		return "customers/customerDetails";
	}
	
	@GetMapping("/customers/new")
	public String initCreationForm(Model model) {
		model.addAttribute("customer", new Customer());
		
		return "/customers/createOrUpdateCustomerForm";
	}
	
	@PostMapping("/customer")
	public String saveOrUpdateCustomer(Customer customer) {
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		
		return "redirect:/customers/" + savedCustomer.getId();
	}
	
	@GetMapping("/customers/{id}/edit")
	public String initUpdateForm(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getById(id));
		
		return "/customers/createOrUpdateCustomerForm";
	}
	
	@GetMapping("/customers/{id}/delete")
	public String deleteCustomer(@PathVariable Integer id) {
		customerService.deleteById(id);
		
		return "redirect:/customers";
	}

}
