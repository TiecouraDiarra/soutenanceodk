package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Models.Reponse;

import java.util.List;

public interface ReponseService {
    String Supprimer(Long id_reponse);  // LA METHODE PERMETTANT DE SUPPRIMER UNE REPONSE

    Reponse Modifier(Reponse reponse);   // LA METHODE PERMETTANT DE MODIFIER UNE REPONSE

    List<Reponse> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES REPONSES

    Object Ajouter(Reponse reponse); // LA METHODE PERMETTANT D'AJOUTER UNE REPONSE
}
