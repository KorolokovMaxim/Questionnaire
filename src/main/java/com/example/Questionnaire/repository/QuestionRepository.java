package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByName(String name);

}
