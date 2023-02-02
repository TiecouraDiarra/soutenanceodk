package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Repository.MatiereRepository;
import SoutenanceBackend.soutenance.Repository.MetierRepository;
import SoutenanceBackend.soutenance.services.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetierImplement implements MetierService {
    @Autowired
    private MetierRepository metierRepository;


    @Override
    public String Supprimer(Long id_metier) {
        metierRepository.deleteById(id_metier);
        return "Metier supprimé avec succès!!";
    }

    @Override
    public Metier Modifier(Metier metier) {
        return metierRepository.findById(metier.getId())
                .map(p->{
                    p.setNommetier(metier.getNommetier());
                    p.setDescriptionmetier(metier.getDescriptionmetier());
                    p.setAvantage(metier.getAvantage());
                    p.setImagemetier(metier.getImagemetier());
                    return metierRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Metier non trouvé !"));
    }

    @Override
    public List<Metier> Afficher() {
        return metierRepository.findAll();
    }

    @Override
    public Object Ajouter(Metier metier) {
        return metierRepository.save(metier);
    }

    @Override
    public Metier RecupererIdMetier(Long idmetier) {
        return metierRepository.findById(idmetier).get();
    }
}
