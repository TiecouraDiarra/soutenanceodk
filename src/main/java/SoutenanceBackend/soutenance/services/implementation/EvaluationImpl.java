package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Answer;
import SoutenanceBackend.soutenance.Models.EvaluationResult;
import SoutenanceBackend.soutenance.Models.QuestionProfessionnel;
import SoutenanceBackend.soutenance.Repository.AnswerRepository;
import SoutenanceBackend.soutenance.Repository.QuestionProfRepository;
import SoutenanceBackend.soutenance.Repository.QuestionRepository;
import SoutenanceBackend.soutenance.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EvaluationImpl implements EvaluationService {

    /*@Autowired
    private QuestionProfRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public List<QuestionProfessionnel> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void submitAnswers(List<Answer> answers) {
        answers.forEach(answer -> {
            answerRepository.save(answer);
        });
    }

    @Override
    public EvaluationResult evaluateAnswers(List<Answer> answers) {
        EvaluationResult result = new EvaluationResult();

        double score = 0;
        int totalQuestions = 0;
        for (Answer answer : answers) {
            QuestionProfessionnel question = questionRepository.findById(answer.getQuestionprof()).orElse(null);
            *//*if (question != null && question.isCorrectAnswer(answer.getContent())) {
                score++;
            }*//*
            totalQuestions++;
        }
        result.setScore(score / totalQuestions);
        return result;
    }*/
}
