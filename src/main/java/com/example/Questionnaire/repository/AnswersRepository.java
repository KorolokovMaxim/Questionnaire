package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    Answers findByName(String name);

    List<Answers> findAnswersByQuestion(Question question);

}
