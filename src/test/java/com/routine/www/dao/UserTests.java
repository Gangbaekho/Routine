package com.routine.www.dao;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.routine.www.entity.Authority;
import com.routine.www.entity.Question;
import com.routine.www.entity.Summary;
import com.routine.www.entity.User;
import com.routine.www.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTests {

	
	@Autowired
	private UserService uservice;
	
	@Test
	public void test() {
		uservice.saveTest();
	}
	
}
