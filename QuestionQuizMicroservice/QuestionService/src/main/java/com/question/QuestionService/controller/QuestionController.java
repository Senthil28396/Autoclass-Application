package com.question.QuestionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.question.QuestionService.model.Question;
import com.question.QuestionService.model.QuestionWrapper;
import com.question.QuestionService.model.Response;
import com.question.QuestionService.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionService questionservice;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionservice.getAllQuestion();
	
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionservice.getQuestionByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		return questionservice.addQuestion(question);
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer numQuestions){
		 return questionservice.getQuestionForQuiz(category,numQuestions);
	}
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(@RequestBody List<Integer> questionId){
	    System.out.println(environment.getProperty("local.server.port"));
		return questionservice.getQuestionFromId(questionId);
	}
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return questionservice.getScore(responses);
	}
		
}
