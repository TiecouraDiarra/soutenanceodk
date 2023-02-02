package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.EmailConstructor;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public User timfa;

    @Autowired
    EmailConstructor emailConstructor;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JavaMailSender mailSender;

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

    @Override
    public User RecupererIdUser(Long iduser) {
        return userRepo.findById(iduser).get();
    }

    @Override
    public void FatimMethode(Long id_user) {
        timfa= userRepo.getReferenceById(id_user);

    }

    @Override
    public User ff() {
        return timfa;
    }

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepo.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
    }


    /*@Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return userRepo.findByResetToken(resetToken);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }*/

    /*@Override
    public User getUser(String numero) {
        return null;
    }*/
}
