package de.aclue.springnativehelloworld.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

}
