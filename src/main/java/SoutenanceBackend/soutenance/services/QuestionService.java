package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.Question;

import java.util.List;

public interface QuestionService {
    String Supprimer(Long id_question);  // LA METHODE PERMETTANT DE SUPPRIMER UNE QUESTION

    Question Modifier(Question question);   // LA METHODE PERMETTANT DE MODIFIER UNE QUESTION

    List<Question> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES QUESTION

    Object Ajouter(Question question); // LA METHODE PERMETTANT D'AJOUTER UNE QUESTION
}
