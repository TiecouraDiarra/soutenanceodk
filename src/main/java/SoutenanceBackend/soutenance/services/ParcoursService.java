package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;

import java.util.List;

public interface ParcoursService {

    String Supprimer(Long id_parcours);  // LA METHODE PERMETTANT DE SUPPRIMER UN PARCOURS

    Parcours Modifier(Parcours parcours);   // LA METHODE PERMETTANT DE MODIFIER UN PARCOURS

    List<Parcours> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES PARCOURS

    Object Ajouter(Parcours parcours); // LA METHODE PERMETTANT D'AJOUTER UN PARCOURS

    Parcours RecupererIdParcours(Long idparcours); // LA METHODE PERMETTANT DE RECUPERER L'ID D'UN PARCOURS
}
