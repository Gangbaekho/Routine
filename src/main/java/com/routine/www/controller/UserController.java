package com.routine.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.routine.www.entity.Authority;
import com.routine.www.entity.Question;
import com.routine.www.entity.Summary;
import com.routine.www.entity.User;
import com.routine.www.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService service;
	
	// save user by getting user information from client application
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {

		// create Authorities and set role : ROLE_USER
		Authority authority = new Authority(user.getUsername(),"ROLE_USER");
		
		// mapping authority to User by setAuthority method
		user.setAuthority(authority);
		
		// save the user
		service.saveOrUpdate(user);
	
	}
	
	
}
