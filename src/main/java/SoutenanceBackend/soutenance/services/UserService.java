package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;

import java.util.List;

public interface UserService {
    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN COLLABORATEUR

    String Modifier(User users);   // LA METHODE PERMETTANT DE MODIFIER UN COLLABORATEUR

    List<User> Afficher();       // LA METHODE PERMETTANT D'AFFICHER UN COLLABORATEUR

    User Ajouter(User utilisateur); // LA METHODE PERMETTANT D'AJOUTER UN COLLABORATEUR

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String numero, ERole roleName);
    User getUser(String numero);
}
