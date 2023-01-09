package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.services.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParcoursImplement implements ParcoursService {

    @Autowired
    private ParcoursRepository parcoursRepository;


    @Override
    public String Supprimer(Long id_parcours) {
        parcoursRepository.deleteById(id_parcours);
        return "Parcours supprimé avec succès!!";
    }

    @Override
    public Parcours Modifier(Parcours parcours) {
        return parcoursRepository.findById(parcours.getId())
                .map(p->{
                    p.setNomparcours(parcours.getNomparcours());
                    p.setDescription(parcours.getDescription());
                    p.setAvantage(parcours.getAvantage());
                    p.setFiliere(parcours.getFiliere());
                    p.setDomaine(parcours.getDomaine());
                    p.setImageparcours(parcours.getImageparcours());
                    return parcoursRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Parcours non trouvé !"));
    }

    @Override
    public List<Parcours> Afficher() {
        return parcoursRepository.findAll();
    }

    @Override
    public Object Ajouter(Parcours parcours) {
        return parcoursRepository.save(parcours);
    }
}
