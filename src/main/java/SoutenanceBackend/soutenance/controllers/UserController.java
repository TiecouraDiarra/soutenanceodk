package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static SoutenanceBackend.soutenance.Models.ERole.ROLE_ADMIN;

@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("user")
public class UserController {

        //private static final Logger LOG = Logger.getLogger(AuthController.class.getName());
        @Autowired
        private UserService userService;

        @Autowired
        private EleveRepository eleveRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfessionnelRepository professionnelRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private UserRepository userRepository;
        @RequestMapping("/user")

        // µµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµ

        //@PreAuthorize("hasRole('USER') or hasRole('COLLABORATEUR') or hasRole('ADMIN')")
        @GetMapping("/afficherUser")
        public List<User> AfficherUsers(){
            // LOG.info("userService.Afficher()");
            return userRepository.findAll();
        }

        // µµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµ   MODIFIER
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping({"/modifier"})
        public String ModierUser(@RequestBody User users){

            userService.Modifier(users);
            // LOG.info("Modification reussie avec succès");
            return "Modification reussie avec succès";
        }

        //@PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/Supprimer/{id_users}")
        public String Supprimer(@PathVariable("id_users") Long id_users){
            userService.Supprimer(id_users);
            //LOG.info("Suppression reussie");
            return "Suppression reussie";
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping ("/ajouter")
        public String Ajouter(@RequestBody User utilisateur){
            userService.Ajouter(utilisateur);
            // LOG.info("Ajouter avec succès");
            return "Ajouter avec succès";
        }

    @GetMapping("/NombreUserTotal")
    public Long getNombreTotalUser(){
        return userRepository.nombreUserTotal();
    }

    @GetMapping("/afficherEleve")
    public List<Eleve> AfficherEleve(){
        // LOG.info("userService.Afficher()");
        return eleveRepository.findAll();
    }

    @GetMapping("/afficherEtudiant")
    public List<Etudiant> AfficherEtudiant(){
        // LOG.info("userService.Afficher()");
        return etudiantRepository.findAll();
    }

    @GetMapping("/afficherRole")
    public List<Role> AfficherRole(){
        // LOG.info("userService.Afficher()");
        return roleRepository.findAll();
    }

    @GetMapping("/afficherProfessionnel")
    public List<professionnel> AfficherProfessionnel(){
        // LOG.info("userService.Afficher()");
        return professionnelRepository.findAll();
    }

    @GetMapping("/afficherAdmin")
    public List<User> AfficherAdmin(){
        // LOG.info("userService.Afficher()");

        return userRepository.findByRoles(new Role(ROLE_ADMIN));
    }

}
