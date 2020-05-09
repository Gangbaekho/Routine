package com.routine.www.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.www.dao.SummaryRepository;
import com.routine.www.dao.UserRepository;
import com.routine.www.entity.Summary;
import com.routine.www.entity.User;

@Service
@Transactional
public class SummaryService {

	@Autowired
	private SummaryRepository srepo;

	@Autowired
	private UserRepository urepo;

	public Summary findById(long id) {

		// get Optional Summary
		Optional<Summary> optionalSummary = srepo.findById(id);

		// if isPrsent return the real Summary
		if (optionalSummary.isPresent()) {

			return optionalSummary.get();
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("can not find the Summary...");
		}
	}
	
	// find filtered summaries by 1,4,7,14,30 days before
	public List<Summary> findFilteredSummary(String username){
		
		return srepo.findFilteredSummary(username);
	}

	// create summary and mapping with loggedin user
	public void saveSummary(Summary summary, String username) {

		// get real user by helper method
		User retrievedUser = getRealUser(username);
		
		// add summary to user by one to many mapping
		retrievedUser.addSummary(summary);

		// save user and summary by cascading.
		urepo.save(retrievedUser);

	}

	// delete summary
	public void deleteSummary(long id) {

		// get real summary by helper method
		Summary retrievedSummary = getRealSummary(id);
		
		// delete it
		srepo.delete(retrievedSummary);		
		
	}
	
	// update Summary 
	public void updateSummary(Summary summary) {
		
		// if summary's id is not 0, update summary
		if(summary.getId() != 0) {
			srepo.save(summary);
		}
		
		// else throw runtime exception
		else {
			throw new RuntimeException("can not update summary.. because id is 0..");
		}
	}
	
	// find all summary related user
	public Collection<Summary> findAllSummaryByUsername(String username){
		
		// get real user by helper method
		User retrievedUser = getRealUser(username);
		
		return retrievedUser.getSummaries();
		
	}
	
	// find all user folders
	public List<String> findAllUserFolder(String username){
		
		return srepo.findAllUserFolder(username);
		
	}

	
	// helper method for getting real user , not a optional user
	private User getRealUser(String username) {

		// get a optional user by user name
		Optional<User> optionalUser = urepo.findById(username);

		// if Optional User is present, use it.
		if (optionalUser.isPresent())
			return optionalUser.get();

		// else throw runtime exception error
		else
			throw new RuntimeException("can not find the user");

	}
	
	// helper method for getting real summary, not a optional summary
	private Summary getRealSummary(long id) {
		
		// get a optional summary by id
		Optional<Summary> optionalSummary = srepo.findById(id);
		
		// if optional summary is present, use it
		if(optionalSummary.isPresent())
			return optionalSummary.get();
		
		// else throw runtime exception error
		else
			throw new RuntimeException("can not find the summary..");
		
	}
	

}
