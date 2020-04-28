package com.routine.www.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routine.www.entity.Summary;


public interface SummaryRepository extends JpaRepository<Summary, Long>{
	
	@Query(value = "select distinct s.folder from Summary s, User u where u.username=?1")
	public List<String> findAllUserFolder(String username);

}
