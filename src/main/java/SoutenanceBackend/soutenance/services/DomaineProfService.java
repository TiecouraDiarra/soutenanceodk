package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.DomaineProf;
import SoutenanceBackend.soutenance.Models.SerieLycee;

public interface DomaineProfService {
    DomaineProf trouverDomaineParNom(String nomdomaine);
}
