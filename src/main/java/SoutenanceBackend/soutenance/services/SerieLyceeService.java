package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Models.TypeQuestion;

public interface SerieLyceeService {

    SerieLycee trouverSerieParNom(String nomserie);

    Object Ajouter(SerieLycee serieLycee); // LA METHODE PERMETTANT D'AJOUTER UN SERIE
}
