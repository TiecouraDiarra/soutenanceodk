package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.TypeQuestion;

import java.util.List;

public interface TypeQuestionService {
    String Supprimer(Long id_typequestion);  // LA METHODE PERMETTANT DE SUPPRIMER UN TYPE

    TypeQuestion Modifier(TypeQuestion typeQuestion);   // LA METHODE PERMETTANT DE MODIFIER UN TYPE

    List<TypeQuestion> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES TYPES

    Object Ajouter(TypeQuestion typeQuestion); // LA METHODE PERMETTANT D'AJOUTER UN TYPE
}
