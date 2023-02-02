package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR

    String Modifier(User users);   // LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR

    List<User> Afficher();       // LA METHODE PERMETTANT D'AFFICHER UN UTILISATEUR

    User Ajouter(User utilisateur); // LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR

    User saveUser(User user); // LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR
    Role saveRole(Role role);  // LA METHODE PERMETTANT D'AJOUTER UN ROLE
    void addRoleToUser(String numero, ERole roleName); // LA METHODE PERMETTANT D'AJOUTER UN ROLE A UN UTILISATEUR
    //User getUser(String numero);

    /*public Optional<User> findUserByResetToken(String resetToken);
    public Optional<User> findUserByEmail(String email);*/

    User RecupererIdUser(Long iduser); // LA METHODE PERMETTANT DE RECUPERER L'ID D'UN UTILISATEUR


    void FatimMethode(Long id_user);

    User ff();


    void resetPassword(User user);
}
