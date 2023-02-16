package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import SoutenanceBackend.soutenance.services.SerieLyceeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieLyceeImple implements SerieLyceeService {

    @Autowired
    private SerieLyceeRepository serieLyceeRepository;
    @Override
    public SerieLycee trouverSerieParNom(String nomserie) {
        return serieLyceeRepository.findByNomserie(nomserie);
    }

    @Override
    public Object Ajouter(SerieLycee serieLycee) {
        return serieLyceeRepository.save(serieLycee);
    }
}
