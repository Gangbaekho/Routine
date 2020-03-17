package com.routine.www.dao;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.routine.www.entity.User;
import com.routine.www.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SummaryTests {

	@Autowired
	private UserService service;

}
