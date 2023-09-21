package de.aclue.springnativehelloworld.rest;

import de.aclue.springnativehelloworld.persistence.CustomerEntity;
import de.aclue.springnativehelloworld.persistence.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository customerRepository;

  @GetMapping
  public List<CustomerEntity> readCustomers() {
    return customerRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<CustomerEntity> readCustomer(@PathVariable("id") UUID id) {
    return customerRepository.findById(id);
  }

  @PostMapping
  public CustomerEntity writeCustomer(@RequestBody CustomerEntity customerEntity) {
    this.customerRepository.save(customerEntity);
    return customerEntity;
  }

}
