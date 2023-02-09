package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.Security.jwt.JwtUtils;
import SoutenanceBackend.soutenance.request.SignupEleve;
import SoutenanceBackend.soutenance.request.SignupRequest;
import SoutenanceBackend.soutenance.response.MessageResponse;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("eleve")
public class EleveController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EleveRepository eleveRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    ProfessionnelRepository professionnelRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupEleve signupEleve) {


        if (userRepository.existsByNumero(signupEleve.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Numéro déjà utilisé !"));
        }

        if (userRepository.existsByNumero(signupEleve.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email déja utilisé !"));
        }



        // Create new user's account
        if (signupEleve.getPassword().equals(signupEleve.getConfirmpassword())){
            Eleve eleve = new Eleve(signupEleve.getNomcomplet(), signupEleve.getNumero(),signupEleve.getEmail(),
                    encoder.encode(signupEleve.getPassword()), encoder.encode(signupEleve.getConfirmpassword()));
            User user= new User(signupEleve.getNomcomplet(), signupEleve.getNumero(),
                    signupEleve.getEmail(),
                    encoder.encode(signupEleve.getPassword()), encoder.encode(signupEleve.getConfirmpassword()));

            Set<String> strRoles = signupEleve.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_ELEVE);
                if(userRole==null){
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Erreur: Role non trouvé."));
                }else {
                    roles.add(userRole);
                }
                //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "eleve":
                            Role eleveRole = roleRepository.findByName(ERole.ROLE_ELEVE);
                            if(eleveRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(eleveRole);
                            }

                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_ELEVE);
                            if(userRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(userRole);

                            }
                            //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    }
                });
            }


            eleve.setRoles(roles);
            //user.setRoles(roles);
            //userRepository.save(user);
            eleveRepository.save(eleve);
            mailSender.send(emailConstructor.constructNewUserEmail(eleve));

            return ResponseEntity.ok(new MessageResponse(eleve.getNomcomplet() + " ajouté avec succès !"));
        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas mêmes "));
        }

    }

}
