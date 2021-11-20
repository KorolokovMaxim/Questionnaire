package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import com.example.Questionnaire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Long> {


    Questionnaire findByName(String name);

    Questionnaire findById(long id);

    Questionnaire findByQuestions(Question q);



}
