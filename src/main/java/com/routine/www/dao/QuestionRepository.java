package com.routine.www.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.www.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
