package com.quiz.QuizService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.QuizService.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
