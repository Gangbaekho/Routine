package com.routine.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.www.dao.UserRepository;
import com.routine.www.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public void save(User user) {
		
		repo.save(user);
	}
}
