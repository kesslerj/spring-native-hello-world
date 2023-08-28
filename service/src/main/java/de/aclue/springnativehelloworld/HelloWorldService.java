package de.aclue.springnativehelloworld;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

  public String hello() {
    return "Moin!";
  }
}
