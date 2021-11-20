package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Questionnaire;
import com.example.Questionnaire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Long findById(User user);


}
