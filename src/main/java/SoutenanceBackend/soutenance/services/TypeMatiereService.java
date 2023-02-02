package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.TypeMatiere;
import SoutenanceBackend.soutenance.Models.TypeQuestion;

import java.util.List;

public interface TypeMatiereService {
    String Supprimer(Long id_typematiere);  // LA METHODE PERMETTANT DE SUPPRIMER UN TYPE

    TypeMatiere Modifier(TypeMatiere typeQuestion);   // LA METHODE PERMETTANT DE MODIFIER UN TYPE

    List<TypeMatiere> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES TYPES

    Object Ajouter(TypeMatiere typeQuestion); // LA METHODE PERMETTANT D'AJOUTER UN TYPE
}
