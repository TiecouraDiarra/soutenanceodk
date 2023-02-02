package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.ParametreMatiere;

import java.util.List;

public interface ParamMatiereService {

    String Supprimer(Long id_paramatiere);  // LA METHODE PERMETTANT DE SUPPRIMER UN PARAMETRE

    ParametreMatiere Modifier(ParametreMatiere parametreMatiere);   // LA METHODE PERMETTANT DE MODIFIER UN PARAMETRE

    List<ParametreMatiere> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES PARAMETRES

    Object Ajouter(ParametreMatiere parametreMatiere); // LA METHODE PERMETTANT D'AJOUTER UN PARAMETRE
}
