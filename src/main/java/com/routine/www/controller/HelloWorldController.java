package com.routine.www.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloWorldController {

	@GetMapping("/helloworld")
	public String helloworld() {
		return "helloWorld!";
	}
}
