package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    Answers findByName(String name);

    Optional<Answers> findById(Long id);
    List<Answers> findAnswersByQuestion(Question question);

    List<Answers> findAllByQuestionId(Long id);

    List<Answers> findAllByUsers(User user);







}
