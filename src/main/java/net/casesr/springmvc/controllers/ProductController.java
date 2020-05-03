package net.casesr.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.casesr.springmvc.domain.Product;
import net.casesr.springmvc.services.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAll());
		
		return "products/list";
	}
	
	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		
		return "products/productDetails";
	}
	
	@GetMapping("/products/new")
	public String initCreationForm(Model model) {
		model.addAttribute("product", new Product());
		
		return "products/createOrUpdateProductForm";
	}
	
	@PostMapping("/product")
	public String saveOrUpdateProduct(Product product) {
		Product savedProduct = productService.saveOrUpdate(product);
		
		return "redirect:/products/" + savedProduct.getId();
	}
	
	@GetMapping("/products/{id}/edit")
	public String initUpdateProductForm(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		
		return "products/createOrUpdateProductForm";
	}
	
	@GetMapping("/products/{id}/delete")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteById(id);
		
		return "redirect:/products";
	}
	
}
