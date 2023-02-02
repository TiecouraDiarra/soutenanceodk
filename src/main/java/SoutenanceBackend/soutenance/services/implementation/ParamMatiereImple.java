package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.ParametreMatiere;
import SoutenanceBackend.soutenance.Repository.MatiereQuestionRepository;
import SoutenanceBackend.soutenance.Repository.ParamMatiereRepository;
import SoutenanceBackend.soutenance.services.ParamMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamMatiereImple implements ParamMatiereService {
    @Autowired
    private ParamMatiereRepository paramMatiereRepository;

    //SUPPRIMER UN PARAMETRE
    @Override
    public String Supprimer(Long id_paramatiere) {
        paramMatiereRepository.deleteById(id_paramatiere);
        return "Supprimer avec succès";
    }

    //MODIFIER UN PARAMETRE
    @Override
    public ParametreMatiere Modifier(ParametreMatiere parametreMatiere) {
        return paramMatiereRepository.findById(parametreMatiere.getId())
                .map(p->{
                    p.setValeurPlafond(parametreMatiere.getValeurPlafond());
                    p.setMatiereQuestion(parametreMatiere.getMatiereQuestion());
                    return paramMatiereRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Parametre non trouvé !"));
    }

    //AFFICHER LES PARAMETRES
    @Override
    public List<ParametreMatiere> Afficher() {
        return paramMatiereRepository.findAll();
    }

    //AJOUTER UN PARAMETRE
    @Override
    public Object Ajouter(ParametreMatiere parametreMatiere) {
        return paramMatiereRepository.save(parametreMatiere);
    }
}
