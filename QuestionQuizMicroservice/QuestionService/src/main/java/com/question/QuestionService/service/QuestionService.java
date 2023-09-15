package com.question.QuestionService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.question.QuestionService.dao.QuestionDao;
import com.question.QuestionService.model.Question;
import com.question.QuestionService.model.QuestionWrapper;
import com.question.QuestionService.model.Response;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questiondao;

	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
		 return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {

		questiondao.save(question);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQuestions) {

		List<Integer> question=questiondao.findRandomQuestionsByCategory(category,numQuestions);
		return new ResponseEntity<List<Integer>>(question,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
		
		List<QuestionWrapper> wrapper=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		for(Integer id:questionId)
		{
			questions.add(questiondao.findById(id).get());
		}
		
		for(Question question:questions)
		{
			QuestionWrapper wrap=new QuestionWrapper();
			wrap.setId(question.getId());
			wrap.setQuestionTitle(question.getQuestionTitle());
			wrap.setOption1(question.getOption1());
			wrap.setOption2(question.getOption2());
			wrap.setOption3(question.getOption3());
			wrap.setOption4(question.getOption4());
			wrapper.add(wrap);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(wrapper,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int answer=0;
		for(Response response:responses)
		{
			Question question=questiondao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())){
				answer++;
			}
		}
		return new ResponseEntity<Integer>(answer,HttpStatus.OK);
	}

}
