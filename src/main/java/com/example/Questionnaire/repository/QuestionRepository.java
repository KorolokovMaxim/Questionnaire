package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByName(String name);

    List<Question> findByQuestionnaire(Questionnaire q);

    Question findByAnswers(Answers answers);

    Optional<Question> findById(Long id);




}
