package com.routine.www.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.www.entity.Summary;


public interface SummaryRepository extends JpaRepository<Summary, Long>{

}
