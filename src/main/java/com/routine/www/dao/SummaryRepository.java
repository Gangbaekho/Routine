package com.routine.www.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routine.www.entity.Summary;


public interface SummaryRepository extends JpaRepository<Summary, Long>{
	
	@Query(value = "select distinct s.folder from Summary s, User u where u.username=?1")
	public List<String> findAllUserFolder(String username);
	
	@Query(value = "select s.* from summary s, users u where u.username=?1 and s.createdate like date_format(now(),'%Y-%m-%d%') or "
			+ "createdate like date_format(DATE_SUB(NOW(), INTERVAL 1 DAY),'%Y-%m-%d%') or "
			+ "createdate like date_format(DATE_SUB(NOW(), INTERVAL 4 DAY),'%Y-%m-%d%') or "
			+ "createdate like date_format(DATE_SUB(NOW(), INTERVAL 7 DAY),'%Y-%m-%d%') or "
			+ "createdate like date_format(DATE_SUB(NOW(), INTERVAL 14 DAY),'%Y-%m-%d%') or "
			+ "createdate like date_format(DATE_SUB(NOW(), INTERVAL 30 DAY),'%Y-%m-%d%')",
			nativeQuery = true)
	public List<Summary> findFilteredSummary(String username);

}
