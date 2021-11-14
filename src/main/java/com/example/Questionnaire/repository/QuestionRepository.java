package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByName(String name);

    List<Question> findByQuestionnaire(Questionnaire q);

}
