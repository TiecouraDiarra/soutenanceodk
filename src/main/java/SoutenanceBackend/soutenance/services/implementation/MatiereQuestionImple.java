package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.MatiereQuestion;
import SoutenanceBackend.soutenance.Repository.MatiereQuestionRepository;
import SoutenanceBackend.soutenance.Repository.NiveauParcoursRepository;
import SoutenanceBackend.soutenance.services.MatiereQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereQuestionImple implements MatiereQuestionService {

    @Autowired
    private MatiereQuestionRepository matiereQuestionRepository;

    //SUPPRIMER UNE MATIERE
    @Override
    public String Supprimer(Long id_matierequestion) {
        matiereQuestionRepository.deleteById(id_matierequestion);
        return "Matiere supprimée avec succès!!";
    }

    //MODIFIER UNE MATIERE
    @Override
    public MatiereQuestion Modifier(MatiereQuestion matiereQuestion) {
        return matiereQuestionRepository.findById(matiereQuestion.getId())
                .map(p->{
                    p.setMatierequestion(matiereQuestion.getMatierequestion());
                    return matiereQuestionRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Matière non trouvée !"));
    }

    //AFFICHER LES MATIERES
    @Override
    public List<MatiereQuestion> Afficher() {
        return matiereQuestionRepository.findAll();
    }

    //AJOUTER UNE MATIERE
    @Override
    public Object Ajouter(MatiereQuestion matiereQuestion) {
        return matiereQuestionRepository.save(matiereQuestion);
    }
}
