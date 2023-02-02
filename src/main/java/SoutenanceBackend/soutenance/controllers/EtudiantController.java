package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.request.SignupEleve;
import SoutenanceBackend.soutenance.request.SignupEtudiant;
import SoutenanceBackend.soutenance.response.MessageResponse;
import SoutenanceBackend.soutenance.services.SerieLyceeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("etudiant")
public class EtudiantController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private SerieLyceeService serieLyceeService;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    //CREATION D'UN ETUDIANT
    @PostMapping("/signup/{nomserie}")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupEtudiant signupEtudiant, @PathVariable("nomserie") String nomserie) {
        if (userRepository.existsByNumero(signupEtudiant.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Numéro déjà utilisé !"));
        }

        if (userRepository.existsByNumero(signupEtudiant.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email déja utilisé !"));
        }


        // Create new user's account
        if (signupEtudiant.getPassword().equals(signupEtudiant.getConfirmpassword())){
            Etudiant etudiant = new Etudiant(signupEtudiant.getNomcomplet(),
                    signupEtudiant.getNumero(),signupEtudiant.getEmail(),
                    encoder.encode(signupEtudiant.getPassword()),
                    encoder.encode(signupEtudiant.getConfirmpassword()));

            Set<String> strRoles = signupEtudiant.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT);
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
                        case "etudiant":
                            Role eleveRole = roleRepository.findByName(ERole.ROLE_ETUDIANT);
                            if(eleveRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(eleveRole);
                            }

                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT);
                            if(userRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(userRole);

                            }
                            //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    }
                });
            }
            //System.out.println(signupEtudiant.getIdserie());
            SerieLycee serieLycee = serieLyceeService.trouverSerieParNom(nomserie);
            etudiant.setSerieLycee(serieLycee);
            etudiant.setRoles(roles);
            etudiantRepository.save(etudiant);
            mailSender.send(emailConstructor.constructNewUserEmail(etudiant));

            return ResponseEntity.ok(new MessageResponse(etudiant.getNomcomplet() + " ajouté avec succès !"));
        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas mêmes "));
        }

    }

    @GetMapping("/afficher/{numero}")
    public Etudiant AfficherEtudiant(@PathVariable("numero") String numero){
        return etudiantRepository.findByNumero(numero);
    }

    @GetMapping("/RetournerIdEtudiant/{idetudiant}")
    public User RetournerIdEtudiant(@PathVariable("idetudiant") Long idetudiant){
        return userRepository.findById(idetudiant).get();
    }


}
