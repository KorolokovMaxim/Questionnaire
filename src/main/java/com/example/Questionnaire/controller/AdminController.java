package com.example.Questionnaire.controller;

import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import com.example.Questionnaire.entity.User;
import com.example.Questionnaire.entity.enums.Role;
import com.example.Questionnaire.repository.QuestionRepository;
import com.example.Questionnaire.repository.QuestionnaireRepository;
import com.example.Questionnaire.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;

    public AdminController(UserRepository userRepository, QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }


    @GetMapping("/questionnairesList")
    public String questionnaires(Model model){
        model.addAttribute("questionnaires" , questionnaireRepository.findAll() );
        return "/admin/questionnairesList";
    }


    @GetMapping("/questionnaires")
    public String addQuestionnaires(){
        return "/admin/addQuestionnaires";
    }




    @PostMapping("/saveQuestionnaires")
    public String saveQuestionnaires(@RequestParam String questionnaireName ,
                                     @RequestParam(required=false , name = "questions") String...questions){


        Long questionnaireId = saveQuestionaire(questionnaireName);

        //TODO
        //Сдлеать обработку формы для Question через ajax

//        List<Question> questionList = new ArrayList<>();
//        for (String name  :questions){
//            questionList.add(new Question(name, questionnaireRepository.getById(questionnaireId)));
//        }
//        questionRepository.saveAll(questionList);



        return "redirect:/admin/questionnaires";

    }

    private Long saveQuestionaire(String questionnaireName) {
        Questionnaire createdQuestionnaire = new Questionnaire();
        createdQuestionnaire.setName(questionnaireName);
        questionnaireRepository.save(createdQuestionnaire);
        return createdQuestionnaire.getId();
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/admin/userList";
    }

    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "/admin/userEdit";
    }


    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).
                map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/admin";
    }




}
