package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Question;

import java.util.List;

public interface MatiereService {
    String Supprimer(Long id_matiere);  // LA METHODE PERMETTANT DE SUPPRIMER UNE MATIERE

    Matiere Modifier(Matiere matiere);   // LA METHODE PERMETTANT DE MODIFIER UNE MATIERE

    List<Matiere> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES MATIERE

    Object Ajouter(Matiere matiere); // LA METHODE PERMETTANT D'AJOUTER UNE MATIERE
}
