package com.example.Employee;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Employee.Model.Users;
import com.example.Employee.Repo.UsersRepo;


@CrossOrigin(origins = "http://localhost:4500")
@RestController
@RequestMapping("/login")
public class UsersController {
	@Autowired
	UsersRepo usrpo;


	@PostMapping("/authenticate")
public String validateLogin(Users user) {
			Users usr=usrpo.findByUserName(user.getUserName());
	String passwrd=usr.getPassword();
	byte[] decodedBytes = java.util.Base64.getDecoder().decode(passwrd);
	String decodedString = new String(decodedBytes);

	
	if(decodedString.equals(user.getPassword()))
	{
		return "sucess";

	}
	else
	{
		return "failure";

	}
	}

	@PostMapping("/register")

public String RegisterUser(String UserName , String password,String role) {
	Users usr=usrpo.findByUserName(UserName);
	String Oldusername=usr.getUserName();
		
	if(!Oldusername.equals(UserName))
	{
		String encodedString = Base64.getEncoder().encodeToString(UserName.getBytes());
		Users newUsers=new Users();
		newUsers.setUserName(UserName);
		newUsers.setPassword(password);
		newUsers.setRole(role);
		usrpo.save(newUsers);
		return "sucess";

	}
	else
	{
		return "failure";

	}
	}

}