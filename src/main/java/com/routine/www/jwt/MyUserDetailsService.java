package com.routine.www.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.routine.www.dao.UserRepository;
import com.routine.www.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user =  repo.findById(username);
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not found : " + username));
		
		return user.map(MyUserDetails::new).get();
	}

}
