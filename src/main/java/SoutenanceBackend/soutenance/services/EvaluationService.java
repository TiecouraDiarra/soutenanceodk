package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Answer;
import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.EvaluationResult;
import SoutenanceBackend.soutenance.Models.QuestionProfessionnel;
import SoutenanceBackend.soutenance.Repository.AnswerRepository;
import SoutenanceBackend.soutenance.Repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface EvaluationService {

    /*List<QuestionProfessionnel> getQuestions();

    void submitAnswers(List<Answer> answers);

    EvaluationResult evaluateAnswers(List<Answer> answers);*/


}
