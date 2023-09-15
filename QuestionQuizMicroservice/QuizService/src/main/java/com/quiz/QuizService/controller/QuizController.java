package com.quiz.QuizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.QuizService.model.QuestionWrapper;
import com.quiz.QuizService.model.Response;
import com.quiz.QuizService.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizservice;
	
	@PostMapping("/create")
	public 	ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		return quizservice.createQuiz(quizDto.getCategoryName(),quizDto.getNumberOfQuestion(),quizDto.getTitle());
	}
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
		return quizservice.getQuizQuestion(id);
	}
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
		return quizservice.calculateResult(id,response);
	}
}