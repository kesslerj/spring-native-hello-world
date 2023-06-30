package de.aclue.springnativehelloworld;

import de.aclue.springnativehelloworld.persistence.CustomerEntity;
import de.aclue.springnativehelloworld.persistence.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringNativeHelloWorldApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldSuccessfullyPersistEntity(){
		CustomerEntity entity = new CustomerEntity();
		entity.setFirstName("foo");
		entity.setLastName("bar");
		entity.setAge(1);
		customerRepository.save(entity);
	}

}
