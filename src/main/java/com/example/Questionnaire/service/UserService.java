package com.example.Questionnaire.service;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.User;
import com.example.Questionnaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public User saveUserAnswers(Set<Answers> answersSet, com.example.Questionnaire.entity.User user) {

        com.example.Questionnaire.entity.User saveUser = new com.example.Questionnaire.entity.User();
        saveUser = user;
        for (Answers a : answersSet) {
            saveUser.getAnswers().add(a);
        }
        return saveUser;


    }
}
