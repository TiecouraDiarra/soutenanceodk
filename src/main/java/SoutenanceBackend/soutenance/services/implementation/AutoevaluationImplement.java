package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Repository.AutoevaluationRepository;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AutoevaluationImplement implements AutoevaluationService {


    @Autowired
    private AutoevaluationRepository autoevaluationRepository; //LA DEPENDANCE DU REPOSITORY DE AUTOEVALUATION


    @Override
    public String Supprimer(Long id_autoevaluation) {
        //log.info("Affichage de tous les collaborateur");
        autoevaluationRepository.deleteById(id_autoevaluation);
        //log.info("Suppression d'une autoevaluation");
        return "Supprimer avec succes";
    }

    @Override
    public String Modifier(Autoevaluation autoevaluation) {
        Autoevaluation auto = autoevaluationRepository.findByNomautoevaluation(autoevaluation.getNomautoevaluation());
        autoevaluationRepository.findById(autoevaluation.getId())
                .map(p->{
                    p.setNomautoevaluation(autoevaluation.getNomautoevaluation());
                    return autoevaluationRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Autoevaluation non trouvé !"));
        return null;
    }

    @Override
    public List<Autoevaluation> Afficher() {
        return autoevaluationRepository.findAll();
    }

    @Override
    public Object Ajouter(Autoevaluation nomautoevaluation) {
        Autoevaluation auto = autoevaluationRepository.findByNomautoevaluation(nomautoevaluation.getNomautoevaluation());
        if (auto==null){
            nomautoevaluation.getNomautoevaluation();
            autoevaluationRepository.save(nomautoevaluation);
            return "Ajouter avec succèss !!";
        }else {
            return "Ce type d'autoevaluation existe deja!!";
        }

    }
}
