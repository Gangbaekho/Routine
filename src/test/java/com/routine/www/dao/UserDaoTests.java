package com.routine.www.dao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.routine.www.entity.Authority;
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
		User user = new User("kim2","jinsoo",true);
		
		//setting username;
		String userId = "kim2";
		
		//create authority for one to one mapping in user
		Authority authority = new Authority(userId,"ROLE_USER");
		
		// setAuthority for oneToOne mapping.
		user.setAuthority(authority);
				
		// save in db
		repo.save(user);
		
		// get user by id
		Optional<User> optionalUser = repo.findById(userId);
		User retrievedUser = null;
		if(optionalUser.isPresent()) {
			retrievedUser = optionalUser.get();
		} else {
			throw new RuntimeException("can not find the user id : " + userId);
		}
		
		// equal test
		assertEquals(retrievedUser.toString(), user.toString());
	}
	
}
