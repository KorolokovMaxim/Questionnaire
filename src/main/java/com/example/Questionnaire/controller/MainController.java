package com.example.Questionnaire.controller;

import com.example.Questionnaire.entity.Answers;
import com.example.Questionnaire.entity.Question;
import com.example.Questionnaire.entity.Questionnaire;
import com.example.Questionnaire.repository.AnswersRepository;
import com.example.Questionnaire.repository.QuestionRepository;
import com.example.Questionnaire.repository.QuestionnaireRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;

    public MainController(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswersRepository answersRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }


    @GetMapping("/allQuestionnaire")
    public String getAllQuestionnaire(Model model) {

        model.addAttribute("questionnaires", questionnaireRepository.findAll());

        return "allQuestionnaires";

    }


    @GetMapping("/questionnaire/{questionnaire}")
    public String getOneQuestionnaire(@PathVariable Questionnaire questionnaire, Model model) {

        model.addAttribute("questionnaire", questionnaire);
        List<Question> questionList = questionRepository.findByQuestionnaire(questionnaire);
        model.addAttribute("questions", questionList);
        Map<Question, List<Answers>> questionAnswersMap = new HashMap<>();
        for (Question q : questionList) {

            questionAnswersMap.put(q, answersRepository.findAllByQuestionId(q.getId()));
        }

        model.addAttribute("qam" , questionAnswersMap);


//        for (Map.Entry<Question, List<Answers>> qam : questionAnswersMap.entrySet()) {
//
//            System.out.println(qam.getKey().getName());
//            for (Answers answers : qam.getValue()) {
//                System.out.println(answers.getName());
//            }
//
//        }


        return "oneQuestionnaire";
    }


}
