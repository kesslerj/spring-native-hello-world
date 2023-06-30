package de.aclue.springnativehelloworld.rest;

import de.aclue.springnativehelloworld.persistence.Customer;
import de.aclue.springnativehelloworld.persistence.CustomerRepository;
import de.aclue.springnativehelloworld.rest.CustomerController.ResourceNotFoundException;
import java.beans.PropertyEditorSupport;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerPropertyEditor extends PropertyEditorSupport  {

	@Autowired
	private CustomerRepository customerRepository;
			
	  @Override
	  public void setAsText(String text) throws IllegalArgumentException {
		  long id = Long.parseLong(text);
		  Optional<Customer> customerOptional = customerRepository.findById(id);
		 Customer customer = customerOptional.orElseThrow(() -> new ResourceNotFoundException());
		 setValue(customer);
	  }

}
