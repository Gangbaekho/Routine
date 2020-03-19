package com.routine.www.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.routine.www.entity.Question;
import com.routine.www.service.QuestionService;

@RestController
@CrossOrigin
public class QuestionController {

	@Autowired
	private QuestionService qservice;

	// create question by authenticated user and summary id
	@PostMapping("/question/{username}/{summaryId}")
	public void createQuestion(@RequestBody Question question, @PathVariable String username,
			@PathVariable long summaryId, Principal principal) {

		// if user is authenticated create question
		if (principal.getName().equals(username)) {

			qservice.saveQuestion(question, summaryId);
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("you do not have a right...");
		}
	}

	// get questions related summary by authenticated user and summaryId
	@GetMapping("/questions/{username}/{summaryId}")
	public Collection<Question> getQuestionsBySummaryId(@PathVariable String username, @PathVariable long summaryId,
			Principal principal) {

		// if user is authenticated return Questions
		if (principal.getName().equals(username)) {

			return qservice.findAllQuestionsRelatedSummary(summaryId);
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("you do not have a right...");
		}

	}

	// get question by question id
	@GetMapping("/question/{username}/{questionId}")
	public Question getQuestionByQuestionId(@PathVariable String username, @PathVariable long questionId,
			Principal principal) {
		// if user is authenticated return Question
		if (principal.getName().equals(username)) {

			return qservice.findQuestionById(questionId);
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("you do not have a right...");
		}

	}

	// update question
	@PutMapping("/question/{username}")
	public void updateQuestion(@RequestBody Question question, @PathVariable String username, Principal principal) {

		// if user is authenticated update Question
		if (principal.getName().equals(username)) {

			qservice.updateQuestion(question);
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("you do not have a right...");
		}

	}

	// delete question
	@DeleteMapping("/question/{username}/{questionId}")
	public void deleteQuestion(@PathVariable String username, @PathVariable long questionId, Principal principal) {

		// if user is authenticated delete Question
		if (principal.getName().equals(username)) {

			qservice.deleteQuestion(questionId);
		}

		// else throw runtime exception error
		else {
			throw new RuntimeException("you do not have a right...");
		}

	}
}
