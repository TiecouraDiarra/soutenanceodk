package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.Parcours;

import java.util.List;

public interface NiveauParcoursService {
    String Supprimer(Long id_niveauparcours);  // LA METHODE PERMETTANT DE SUPPRIMER UN NIVEAUPARCOURS

    Niveauparcours Modifier(Niveauparcours niveauparcours);   // LA METHODE PERMETTANT DE MODIFIER UN NIVEAUPARCOURS

    List<Niveauparcours> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES NIVEAUPARCOURS

    Object Ajouter(Niveauparcours niveauparcours); // LA METHODE PERMETTANT D'AJOUTER UN NIVEAUPARCOURS
}
