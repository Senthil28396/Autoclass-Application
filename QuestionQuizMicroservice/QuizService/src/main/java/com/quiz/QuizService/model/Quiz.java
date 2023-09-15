package com.quiz.QuizService.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)	
	private Integer id;
	private String title;
	
	@ElementCollection
	private List<Integer> questionIds;
}
