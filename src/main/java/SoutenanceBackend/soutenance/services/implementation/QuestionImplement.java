package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Repository.AutoevaluationRepository;
import SoutenanceBackend.soutenance.Repository.QuestionRepository;
import SoutenanceBackend.soutenance.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionImplement implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository; //LA DEPENDANCE DU REPOSITORY DE QUESTION

    @Autowired
    private AutoevaluationRepository autoevaluationRepository;
    @Override
    public String Supprimer(Long id_question) {
        questionRepository.deleteById(id_question);
        return "Question supprimée avec succès !!";
    }

    @Override
    public Question Modifier(Question question) {
        return questionRepository.findById(question.getId())
                .map(p->{
                    p.setTypeMatiere(question.getTypeMatiere());
                    p.setTypeQuestion(question.getTypeQuestion());
                    p.setQuestion(question.getQuestion());
                    return questionRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Question non trouvée !"));
    }

    @Override
    public Question modifier(Long id_question, Question question) {
        return questionRepository.findById(id_question)
                .map(p->{
                    p.setQuestion(question.getQuestion());
                    p.setTypeQuestion(question.getTypeQuestion());
                    p.setTypeMatiere(question.getTypeMatiere());
                    return questionRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Question non trouvée !"));
    }

    @Override
    public List<Question> Afficher() {
        return questionRepository.findAll();
    }

    @Override
    public Object Ajouter(Question question) {
        //question.getAutoevaluation();

        return questionRepository.save(question);

    }
}
