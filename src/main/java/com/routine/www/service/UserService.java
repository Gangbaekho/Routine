package com.routine.www.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.www.dao.UserRepository;
import com.routine.www.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public void saveOrUpdate(User user) {
		
		repo.save(user);
	}
	
	public User findUserByUsername(String username) {
		
		// get Optional<User> from user repository
		Optional<User> optionalUser = repo.findById(username);
		
		// if isPresent is true return real User
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		
		// else throw runtime exception error
		else {
			throw new RuntimeException("can not find the user...");
		}
	
	}
	
	public void deleteUser(User user) {
		
		repo.delete(user);
	}
	

	
	
}
