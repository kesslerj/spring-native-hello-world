package de.aclue.springnativehelloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @Autowired
  private HelloWorldService helloWorldService;

  @GetMapping("/")
  String home() {
    return helloWorldService.hello();
  }
}
