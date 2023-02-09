package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.EtudiantRepository;
import SoutenanceBackend.soutenance.Repository.ProfessionnelRepository;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.request.SignupEtudiant;
import SoutenanceBackend.soutenance.request.SignupProfessionnel;
import SoutenanceBackend.soutenance.response.MessageResponse;
import SoutenanceBackend.soutenance.services.DomaineProfService;
import SoutenanceBackend.soutenance.services.SerieLyceeService;
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
@RequestMapping("professionnel")
public class ProfessionnelController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProfessionnelRepository professionnelRepository;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    UserRepository userRepository;

    @Autowired
    private DomaineProfService domaineProfService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signup/{nomdomaine}")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupProfessionnel signupProfessionnel, @PathVariable("nomdomaine") String nomdomaine) {
        if (userRepository.existsByNumero(signupProfessionnel.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Numéro déjà utilisé !"));
        }

        if (userRepository.existsByNumero(signupProfessionnel.getNumero())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email déja utilisé !"));
        }


        // Create new user's account
        if (signupProfessionnel.getPassword().equals(signupProfessionnel.getConfirmpassword())){
            professionnel professionnel = new professionnel(signupProfessionnel.getNomcomplet(), signupProfessionnel.getNumero()
                    ,signupProfessionnel.getEmail(),encoder.encode(signupProfessionnel.getPassword()), encoder.encode(signupProfessionnel.getConfirmpassword()));

            Set<String> strRoles = signupProfessionnel.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_PROFESSIONNEL);
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
                        case "professionnel":
                            Role eleveRole = roleRepository.findByName(ERole.ROLE_PROFESSIONNEL);
                            if(eleveRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(eleveRole);
                            }

                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_PROFESSIONNEL);
                            if(userRole==null){
                                new RuntimeException("Erreur: Role non trouvé.");
                            }else {
                                roles.add(userRole);

                            }
                            //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    }
                });
            }

            DomaineProf domaineProf = domaineProfService.trouverDomaineParNom(nomdomaine);
            professionnel.setDomaineProf(domaineProf);
            professionnel.setRoles(roles);
            professionnelRepository.save(professionnel);
            mailSender.send(emailConstructor.constructNewUserEmail(professionnel));

            return ResponseEntity.ok(new MessageResponse(professionnel.getNomcomplet() + " ajouté avec succès !"));
        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas mêmes "));
        }

    }
}
