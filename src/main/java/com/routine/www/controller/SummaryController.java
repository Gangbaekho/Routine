package com.routine.www.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// create summary by authenticated user
	@PostMapping("/summary/{username}")
	public void createSummary(@RequestBody Summary summary, @PathVariable String username, Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {

			sservice.saveSummary(summary, username);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}

	}

	// get summary by authenticated user
	@GetMapping("/summary/{username}")
	public Collection<Summary> getSummaryByUsername(@PathVariable String username, Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {

			return sservice.findAllSummaryByUsername(username);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}

	}

	// get one summary mapped by id
	@GetMapping("/summary/{username}/{id}")
	public Summary getOneSummaryByUsernameAndId(@PathVariable String username, @PathVariable long id,
			Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {

			return sservice.findById(id);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}

	}

	// update Summary by user name and id
	@PutMapping("/summary/{username}/{id}")
	public void updateSummaryByUsernameAndId(@RequestBody Summary summary, @PathVariable String username,
			@PathVariable long id, Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {

			sservice.updateSummary(summary);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}

	}

	// delete summary by user name and id
	@DeleteMapping("/summary/{username}/{id}")
	public void deleteSummaryByUsernameAndId(@PathVariable String username, @PathVariable long id,
			Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {
			sservice.deleteSummary(id);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}
	}

	// get all user's folders name
	@GetMapping("/summary/{username}/folders")
	public List<String> findAllUserFolder(@PathVariable String username, Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {
			return sservice.findAllUserFolder(username);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}
	}

	// select filtered summaries by 1,4,7,14,30 days before
	@GetMapping("/summary/{username}/routine")
	public List<Summary> findFilteredSummary(@PathVariable String username, Principal principal) {

		// if user is authenticated
		if (principal.getName().equals(username)) {
			return sservice.findFilteredSummary(username);
		}
		// if user is not authenticated
		else {
			throw new RuntimeException("you do not have a right..");
		}
	}

}
