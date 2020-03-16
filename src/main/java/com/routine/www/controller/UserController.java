package com.routine.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.routine.www.entity.User;
import com.routine.www.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	// save user by getting user information from client application
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		
		// save the user in db
		service.save(user);
		
	}
	
}
