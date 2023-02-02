package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.DomaineProf;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Repository.DomaineProfRepository;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import SoutenanceBackend.soutenance.services.DomaineProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DomaineProfImple implements DomaineProfService {

    @Autowired
    private DomaineProfRepository domaineProfRepository;
    @Override
    public DomaineProf trouverDomaineParNom(String nomdomaine) {
        return domaineProfRepository.findByNomdomaine(nomdomaine);
    }
}
