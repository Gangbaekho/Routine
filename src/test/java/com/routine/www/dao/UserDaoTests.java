package com.routine.www.dao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.routine.www.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {

	@Autowired
	private UserRepository repo;
	
	@Test
	// save in db and get user with db
	public void saveAndGetUser() {
		
		// create new user
		User user = new User("kim1","jinsoo",1);
		
		//setting the ID
		String theId = "kim1";
		
		// save in db
		repo.save(user);
		
		// get user by id
		Optional<User> optionalUser = repo.findById(theId);
		User retrievedUser = null;
		if(optionalUser.isPresent()) {
			retrievedUser = optionalUser.get();
		} else {
			throw new RuntimeException("can not find the user id : " + theId);
		}
		
		// equal test
		assertEquals(retrievedUser.toString(), user.toString());
	}
	
}
