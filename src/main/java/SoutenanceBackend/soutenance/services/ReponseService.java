package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Models.Reponse;
import SoutenanceBackend.soutenance.Models.User;

import java.util.List;

public interface ReponseService {
    String Supprimer(Long id_reponse);  // LA METHODE PERMETTANT DE SUPPRIMER UNE REPONSE

    Reponse Modifier(Reponse reponse);   // LA METHODE PERMETTANT DE MODIFIER UNE REPONSE

    List<Reponse> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES REPONSES

    Object Ajouter(Reponse reponse); // LA METHODE PERMETTANT D'AJOUTER UNE REPONSE

    List<Reponse> AfficherListeReponseParUser(User user);       // LA METHODE PERMETTANT D'AFFICHER LES REPONSES

    //Long TotaleScience(Long iduser, String nomtypematiere); // LA METHODE PERMETTANT D'AFFICHER LE TOTAL DES REPONSES(SCIENCE)
}
