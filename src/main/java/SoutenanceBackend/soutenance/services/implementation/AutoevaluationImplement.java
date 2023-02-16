package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class AutoevaluationImplement implements AutoevaluationService {


    @Autowired
    private AutoevaluationRepository autoevaluationRepository; //LA DEPENDANCE DU REPOSITORY DE AUTOEVALUATION


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private TypeQuestionRepository typeQuestionRepository;


    @Override
    public String Supprimer(Long id_autoevaluation) {
        //log.info("Affichage de tous les collaborateur");
        autoevaluationRepository.deleteById(id_autoevaluation);
        //log.info("Suppression d'une autoevaluation");
        return "Supprimer avec succes";
    }

    @Override
    public Autoevaluation Modifier(Autoevaluation autoevaluation) {
        //Autoevaluation auto = autoevaluationRepository.findByNomautoevaluation(autoevaluation.getNomautoevaluation());
        return autoevaluationRepository.findById(autoevaluation.getId())
                .map(p->{
                    //p.setNomautoevaluation(autoevaluation.getNomautoevaluation());
                    p.setDateauto(autoevaluation.getDateauto());
                    //p.setNombreauto(autoevaluation.getNombreauto());
                    //p.setQuestions(autoevaluation.getQuestions());
                    return autoevaluationRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Autoevaluation non trouvé !"));

    }

    @Override
    public List<Autoevaluation> Afficher() {
        return autoevaluationRepository.findAll();
    }

    @Override
    public Object Ajouter(Autoevaluation nomautoevaluation) {
        //Autoevaluation auto = autoevaluationRepository.findByNomautoevaluation(nomautoevaluation.getNomautoevaluation());
        //if (auto==null){
            //nomautoevaluation.getNomautoevaluation();
            nomautoevaluation.setDateauto(new Date());
            nomautoevaluation.getReponses().forEach(reponse1 -> {
                reponseRepository.save(reponse1);
            });
            autoevaluationRepository.save(nomautoevaluation);
            return "Ajouter avec succèss !!";
        /*}else {
            return "Ce type d'autoevaluation existe deja!!";
        }*/

    }

    @Override
    public Iterable<Object[]> IdAutoevaluation(Long id) {
        return autoevaluationRepository.idAutoevaluation(id);
    }

    /*@Override
    public String ARET( Reponse reponse, Long idutil, Long idmatier, Long idtypequestion) {
        Autoevaluation autoevaluation = new Autoevaluation();
        Question question = new Question();
        autoevaluation.setDateauto(new Date());
        autoevaluation.setNomautoevaluation("azert");
        autoevaluation.setNombreauto(3L);
        User user = userRepository.getReferenceById(idutil);
        autoevaluation.setUtilisateur(user);

        question.setReponse(reponseRepository.save(reponse));
        TypeQuestion typeQuestion = typeQuestionRepository.getReferenceById(idtypequestion);
        question.setTypeQuestion(typeQuestion);

        MatiereQuestion matiereQuestion = matiereQuestionRepository.getReferenceById(idmatier);
        question.setMatiereQuestion(matiereQuestion);

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        autoevaluation.setQuestions(questions);
        autoevaluationRepository.save(autoevaluation);
        return "Ajouter avec sucèss";
    }*/
}
