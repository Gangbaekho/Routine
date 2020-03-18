package com.routine.www.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.www.dao.SummaryRepository;
import com.routine.www.entity.Summary;
import com.routine.www.service.SummaryService;

@RestController
@CrossOrigin
public class SummaryController {

	@Autowired
	private SummaryService sservice;
	
	@PostMapping("/summary/{username}")
	public void createSummary(@RequestBody Summary summary,
						@PathVariable String username, Principal principal) {
		
		// if authentication is right
		if(principal.getName().equals(username)) {
			
			sservice.saveSummary(summary, username);
		} 
		// if authentication is false
		else {
			throw new RuntimeException("you do not have a right..");
		}
		
	}
	
	
	
	
}
