package de.aclue.springnativehelloworld.rest;

import de.aclue.springnativehelloworld.persistence.Customer;
import de.aclue.springnativehelloworld.persistence.CustomerRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerPropertyEditor customerPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(CustomerPropertyEditor.class, customerPropertyEditor);
	}

	@GetMapping
	public List<Customer> readCustomers() {
		return customerRepository.findAll();
	}
	@GetMapping("/{id}")
	public Customer readCustomer(@PathVariable("id") Customer customer) {
		return customer;
	}

	@PostMapping
	public Customer writeCustomer(@RequestBody Customer customer) {
		this.customerRepository.save(customer);
		return customer;
	}

	static class ResourceNotFoundException extends RuntimeException {

	}

}
