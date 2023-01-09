package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Repository.NiveauParcoursRepository;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.services.NiveauParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauParcoursImpl implements NiveauParcoursService {
    @Autowired
    private NiveauParcoursRepository niveauParcoursRepository;



    @Override
    public String Supprimer(Long id_niveauparcours) {
        niveauParcoursRepository.deleteById(id_niveauparcours);
        return "Niveau Parcours supprimé avec succès!!";
    }

    @Override
    public Niveauparcours Modifier(Niveauparcours niveauparcours) {
        return niveauParcoursRepository.findById(niveauparcours.getId())
                .map(p->{
                    p.setNomniveau(niveauparcours.getNomniveau());
                    return niveauParcoursRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Niveau Parcours non trouvé !"));
    }

    @Override
    public List<Niveauparcours> Afficher() {
        return niveauParcoursRepository.findAll();
    }

    @Override
    public Object Ajouter(Niveauparcours niveauparcours) {
        return niveauParcoursRepository.save(niveauparcours);
    }
}
