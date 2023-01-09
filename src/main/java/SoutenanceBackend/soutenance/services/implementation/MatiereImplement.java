package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Repository.MatiereRepository;
import SoutenanceBackend.soutenance.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatiereImplement implements MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public String Supprimer(Long id_matiere) {
        matiereRepository.deleteById(id_matiere);
        return "Matière supprimer avec succès!!";
    }

    @Override
    public Matiere Modifier(Matiere matiere) {
        return matiereRepository.findById(matiere.getId())
                .map(p->{
                    p.setNommatiere(matiere.getNommatiere());
                    p.setDescriptionmatiere(matiere.getDescriptionmatiere());
                    p.setParcours(matiere.getParcours());
                    return matiereRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Matière non trouvée !"));
    }

    @Override
    public List<Matiere> Afficher() {
        return matiereRepository.findAll();
    }

    @Override
    public Object Ajouter(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
}
