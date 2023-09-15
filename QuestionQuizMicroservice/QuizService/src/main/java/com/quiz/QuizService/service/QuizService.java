package com.quiz.QuizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.QuizService.dao.QuizDao;
import com.quiz.QuizService.feign.QuizInterface;
import com.quiz.QuizService.model.QuestionWrapper;
import com.quiz.QuizService.model.Quiz;
import com.quiz.QuizService.model.Response;
@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions= quizInterface.getQuestionForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		Quiz quiz=quizDao.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> question=quizInterface.getQuestionFromID(questionIds);
		return question;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		ResponseEntity<Integer> score=quizInterface.getScore(response);
		return score;
	}

}
