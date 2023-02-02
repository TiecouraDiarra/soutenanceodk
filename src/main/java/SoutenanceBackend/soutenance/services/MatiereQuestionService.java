package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.MatiereQuestion;
import SoutenanceBackend.soutenance.Models.Niveauparcours;

import java.util.List;

public interface MatiereQuestionService {

    String Supprimer(Long id_matierequestion);  // LA METHODE PERMETTANT DE SUPPRIMER UNE MATIERE

    MatiereQuestion Modifier(MatiereQuestion matiereQuestion);   // LA METHODE PERMETTANT DE MODIFIER UNE MATIERE

    List<MatiereQuestion> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES MATIERES

    Object Ajouter(MatiereQuestion matiereQuestion); // LA METHODE PERMETTANT D'AJOUTER UNE MATIERE
}
