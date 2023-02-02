package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;

import java.util.List;

public interface MetierService {
    String Supprimer(Long id_metier);  // LA METHODE PERMETTANT DE SUPPRIMER UN METIER

    Metier Modifier(Metier metier);   // LA METHODE PERMETTANT DE MODIFIER UN METIER

    List<Metier> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES METIERS

    Object Ajouter(Metier metier); // LA METHODE PERMETTANT D'AJOUTER UN METIER

    Metier RecupererIdMetier(Long idmetier); // LA METHODE PERMETTANT DE RECUPERER L'ID D'UN METIER
}
