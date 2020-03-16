package com.routine.www.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.www.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
