package com.example.Employee;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")//origins="http://localhost:4500")
@RestController
@RequestMapping("/login")
public class BaicAuthController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean authBean() {
		return new AuthenticationBean("You are authenticated");
	}	
}
