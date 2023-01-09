package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.User;

import java.util.List;

public interface AutoevaluationService {
    String Supprimer(Long id_autoevaluation);  // LA METHODE PERMETTANT DE SUPPRIMER UNE AUTOEVALUATION

    Autoevaluation Modifier(Autoevaluation autoevaluation);   // LA METHODE PERMETTANT DE MODIFIER UNE AUTOEVALUATION

    List<Autoevaluation> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES AUTOEVALUATIONS

    Object Ajouter(Autoevaluation autoevaluation); // LA METHODE PERMETTANT D'AJOUTER UNE AUTOEVALUATION

}
