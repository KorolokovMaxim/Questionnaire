package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    Answers findByName(String name);

    List<Answers> findAnswersByQuestion(Question question);

    List<Answers> findAllByQuestionId(Long id);

    List<Answers> findAllByUsers(User user);







}
