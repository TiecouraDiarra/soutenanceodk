package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserImplement implements UserService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;
    @Override
    public String Supprimer(Long id_users) {
        return null;
    }

    @Override
    public String Modifier(User users) {
        return null;
    }

    @Override
    public List<User> Afficher() {
        return null;
    }

    @Override
    public User Ajouter(User utilisateur) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getNumero());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmpassword(passwordEncoder.encode(user.getConfirmpassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String numero, ERole roleName) {
        log.info("Adding role {} to user {}", numero,roleName);
        User user = userRepo.findByNumero(numero);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    /*@Override
    public User getUser(String numero) {
        return null;
    }*/
}
