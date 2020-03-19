package com.routine.www.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.www.dao.QuestionRepository;
import com.routine.www.dao.SummaryRepository;
import com.routine.www.entity.Question;
import com.routine.www.entity.Summary;

@Service
@Transactional
public class QuestionService {

	@Autowired
	private QuestionRepository qrepo;

	@Autowired
	private SummaryRepository srepo;

	// find all questions related summary by summary id
	public Collection<Question> findAllQuestionsRelatedSummary(long summaryId) {

		// get real summary by helper method
		Summary retrievedSummary = getRealSummary(summaryId);
		
		// return questions related to the summary
		return retrievedSummary.getQuestions();
	}
	
	// find a question by question id
	public Question findQuestionById(long questionId) {
		
		Question retrievedQuestion = getRealQuestion(questionId);
		
		return retrievedQuestion;
		
	}
	
	// create question and insert it to related summary by summary id
	public void saveQuestion(Question question, long summaryId) {
		
		// get real summary by helper method
		Summary retrievedSummary = getRealSummary(summaryId);
		
		// add question to summary's questions collection
		retrievedSummary.addQuestion(question);
		
		// save the summary
		srepo.save(retrievedSummary);
		
	}
	
	// update question
	public void updateQuestion(Question question) {
		
		// if question's id != 0 update question
		if(question.getId() != 0) {
			qrepo.save(question);
		}
		
		// else throw runtime exception error
		else {
			throw new RuntimeException("id is 0 , this question is new.. you can't update question..");
		}
	}
	
	// find question by questionId and delete it
	public void deleteQuestion(long questionId) {
		
		// get real summary by helper method
		Question question = getRealQuestion(questionId);
		
		// if question's id != 0 delete question
		if(question.getId() != 0) {
			qrepo.delete(question);
		}
		
		// else throw runtime exception error
		else {
			throw new RuntimeException("id is 0, this question is new.. you can't update question..");
		}
	}

	// helper method for getting real summary, not a optional summary
	private Summary getRealSummary(long id) {

		// get a optional summary by id
		Optional<Summary> optionalSummary = srepo.findById(id);

		// if optional summary is present, use it
		if (optionalSummary.isPresent())
			return optionalSummary.get();

		// else throw runtime exception error
		else
			throw new RuntimeException("can not find the summary..");

	}
	
	// helper method for getting real question, not a optional question
	private Question getRealQuestion(long id) {
		
		// get a optional summary by id
		Optional<Question> optionalQuestion = qrepo.findById(id);
		
		// if optional summary is preset, use it
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		}
		
		// else throw runtime exception error
		else 
			throw new RuntimeException("can not find the question...");
		
	}
}
