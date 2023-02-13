package SoutenanceBackend.soutenance.controllers;


import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Security.jwt.JwtUtils;
import SoutenanceBackend.soutenance.request.LoginRequest;
import SoutenanceBackend.soutenance.request.SignupRequest;
import SoutenanceBackend.soutenance.response.JwtResponse;
import SoutenanceBackend.soutenance.response.MessageResponse;
import SoutenanceBackend.soutenance.services.SerieLyceeService;
import SoutenanceBackend.soutenance.services.UserDetailsImpl;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private UserService userService;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private SerieLyceeService serieLyceeService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getNumeroOrEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    //SerieLycee serieLycee = serieLyceeService.trouverSerieParNom(userDetails.getNomserie());
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    //userService.FatimMethode(userDetails.getId());


    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(),
                         userDetails.getNomcomplet(),
                         userDetails.getUsername(), 
                         userDetails.getEmail(),
                         userDetails.getSerie(),
                         roles));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByNumero(signUpRequest.getNumero())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Numéro déjà utilisé !"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Email déja utilisé !"));
    }


    // Creation d'un utilisateur
    if (signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
      User user = new User(signUpRequest.getNomcomplet(), signUpRequest.getNumero(),
              signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword()), encoder.encode(signUpRequest.getConfirmpassword()));

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
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
            case "admin":
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
              if(adminRole==null){
                 new RuntimeException("Erreur: Role non trouvé.");
              }else {
                roles.add(adminRole);
              }

              break;
            case "eleve":
              Role eleveRole = roleRepository.findByName(ERole.ROLE_ELEVE);
              if(eleveRole==null){
                new RuntimeException("Erreur: Role non trouvé.");
              }else {
                roles.add(eleveRole);
              }

              break;

            case "etudiant":
              Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT);
              if(etudiantRole==null){
                new RuntimeException("Erreur: Role non trouvé.");
              }else {
                roles.add(etudiantRole);
              }

              break;
            case "professionnel":
              Role professionnelRole = roleRepository.findByName(ERole.ROLE_PROFESSIONNEL);
              if(professionnelRole==null){
                new RuntimeException("Erreur: Role non trouvé.");
              }else {
                roles.add(professionnelRole);
              }

              break;

            default:
              Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
              if(userRole==null){
                new RuntimeException("Erreur: Role non trouvé.");
              }else {
                roles.add(userRole);

              }
                      //.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          }
        });
      }

      user.setRoles(roles);
      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse(user.getNomcomplet() + " ajouté avec succès !"));
    }else {
      return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas mêmes "));
    }
  }

  //MODIFIER UTILISATEUR
  @PutMapping("/user/{id}")
  public ResponseEntity<?> updateUser(@Valid @RequestBody User updateUser, @PathVariable("id") Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Utilisateur non trouvé."));
    }
    User user = optionalUser.get();
    user.setEmail(updateUser.getEmail());
    user.setUsername(updateUser.getNumero());
    user.setNomcomplet(updateUser.getNomcomplet());
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("Utilisateur mis à jour avec succès"));
  }


  //MODIFIER LE MOT DE PASSE
  @PutMapping("/modifierpassword/{numero}")
  public ResponseEntity<?> updateUserPassword(@PathVariable("numero") String numero,
                                              @RequestBody ModifierMotDePasse modifierMotDePasse) {
    // Trouver utilisateur par son numéro
    User user = userRepository.findByNumero(numero);
    if (user==null){
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: Utilisateur non trouvé."));
    }

    // Verifier si l'actuel mot de passe est correct
    if (!encoder.matches(modifierMotDePasse.getAncienmdp(), user.getPassword())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: mot de passe actuel incorrect."));
    }

    // Verifier si le nouveau mot de passe est correct est egal à la confirmation
    if(!modifierMotDePasse.getNouveaumdp().equals(modifierMotDePasse.getConfirmNewmdp())){
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: les mots de passe ne correspondent pas."));
    }

    // Modifier le mot de passe
    user.setPassword(encoder.encode(modifierMotDePasse.getNouveaumdp()));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Mot de passe mis à jour avec succès."));
  }


  //::::::::::::::::::::::::::::::REINITIALISER PASSWORD::::::::::::::::::::::::::::::::::::::::::://
  @GetMapping("/resetpassword/{email}")
  public ResponseEntity<String> resetPassword(@PathVariable("email") String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      return new ResponseEntity<String>("Email non fourni", HttpStatus.BAD_REQUEST);
    }
    userService.resetPassword(user);
    return new ResponseEntity<String>("Email envoyé!", HttpStatus.OK);
  }



}
