package com.example.Questionnaire.controller;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import com.example.Questionnaire.entity.User;
import com.example.Questionnaire.entity.enums.Role;
import com.example.Questionnaire.repository.AnswersRepository;
import com.example.Questionnaire.repository.QuestionRepository;
import com.example.Questionnaire.repository.QuestionnaireRepository;
import com.example.Questionnaire.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.PushBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;


    public AdminController(UserRepository userRepository, QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswersRepository answersRepository) {
        this.userRepository = userRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }


    @GetMapping("/questionnairesList")
    public String questionnaires(Model model) {
        model.addAttribute("questionnaires", questionnaireRepository.findAll());
        return "/admin/questionnairesList";
    }

    @GetMapping("/questionnaires/{questionnaire}")
    public String questionnairesEdit(@PathVariable Questionnaire questionnaire,
                                     Model model) {

        model.addAttribute("questionnaire", questionnaireRepository.findById(questionnaire.getId()));
        model.addAttribute("questions", questionRepository.findByQuestionnaire(questionnaire));


        return "/admin/editQuestionnary";
    }

    @GetMapping("/questionnaires/{questionnaire}/{question}")
    public String questionEdit(@PathVariable Questionnaire questionnaire,
                               @PathVariable Question question,
                               Model model) {

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("question", question);
        model.addAttribute("answers", answersRepository.findAnswersByQuestion(question));

        return "/admin/editQuestion";

    }

    @PostMapping("/saveAnswers")
    public String createAnswers(@RequestParam(name = "answers[]") List<String> answers,
                                @RequestParam(name = "questionId") Question question) {

        Questionnaire questionnaire = questionnaireRepository.findByQuestions(question);

        List<Answers> answersList = new ArrayList<>();
        for (String name : answers) {
            Answers answersElem = new Answers();
            answersElem.setName(name);
            answersElem.setQuestion(question);
            answersList.add(answersElem);
        }

        answersRepository.saveAll(answersList);

        return "redirect:questionnaires/" + questionnaire.getId()+ "/" +question.getId();
    }

    @PostMapping("/deleteAnswers")
    public String deleteAnswers(@RequestParam(name = "answers[]") List<String> answers,
                                @RequestParam(name = "questionId") Question question ){

        Questionnaire questionnaire = questionnaireRepository.findByQuestions(question);

        List<Answers> deleteAnswersList = new ArrayList<>();
        for (String answer : answers){
            Optional<Answers> answersDelete = answersRepository.findById(Long.parseLong(answer));
            deleteAnswersList.add(answersDelete.get());
        }

        answersRepository.deleteAll(deleteAnswersList);

        return "redirect:questionnaires/" + questionnaire.getId()+ "/" +question.getId();

    }


    @GetMapping("/questionnaires")
    public String addQuestionnaires() {
        return "/admin/addQuestionnaires";
    }


    @PostMapping("/saveQuestionnaires")
    public String saveQuestionnaires(@RequestParam String questionnaireName,
                                     @RequestParam(name = "questions[]") List<String> questions) {


        long questionnaireId = saveQuestionaire(questionnaireName);

        Questionnaire q = questionnaireRepository.findById(questionnaireId);


        List<Question> questionListToSave = new ArrayList<>();
        for (String nameQuest : questions) {
            Question question = new Question();
            question.setName(nameQuest);
            question.setQuestionnaire(q);
            questionListToSave.add(question);
        }

        questionRepository.saveAll(questionListToSave);

        return "redirect:/admin/questionnaires";

    }
//@RequestParam(value="answers[][]") String[][] answers
//            for (String answer : answers[i]){
//                createAnswer(answer, q);
//            }

//    @RequestParam List<String>  questionId,
//    @RequestParam(name = "answers[]") String answers,
//    @RequestParam List<String> questionAnswerId,
//    Model model


    private Long saveQuestionaire(String questionnaireName) {
        Questionnaire createdQuestionnaire = new Questionnaire();
        createdQuestionnaire.setName(questionnaireName);
        questionnaireRepository.save(createdQuestionnaire);
        return createdQuestionnaire.getId();
    }

//    private Question saveQuestions(String name, Questionnaire questionnaire) {
//        Question question = new Question();
//        question.setName(name);
//        question.setQuestionnaire(questionnaire);
//        questionRepository.save(question);
//        return question;
//    }
//
//    private void createAnswer(String name, Question question) {
//        Answers answer = new Answers();
//        answer.setName(name);
//        answer.setQuestion(question);
//        answersRepository.save(answer);
//
//    }


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

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/admin";
    }


}
