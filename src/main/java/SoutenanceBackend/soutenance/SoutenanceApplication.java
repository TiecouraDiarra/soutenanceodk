package SoutenanceBackend.soutenance;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

import static SoutenanceBackend.soutenance.Models.ERole.*;

@SpringBootApplication

public class SoutenanceApplication  {

	/*private final RoleRepository roleRepository;
	private final UserRepository utilisateurRepository;



	public SoutenanceApplication(RoleRepository roleRepository, UserRepository utilisateurRepository) {
		this.roleRepository = roleRepository;
		this.utilisateurRepository = utilisateurRepository;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SoutenanceApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findAll().size()==0){
			roleRepository.creationRole();
			utilisateurRepository.creationadmin();
		}

	}*/




	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			Role r1=userService.saveRole(new Role(null, ROLE_ADMIN));
			Role r2 = userService.saveRole(new Role(null, ROLE_USER));
			Role r3 = userService.saveRole(new Role(null, ROLE_ELEVE));
			Role r4 = userService.saveRole(new Role(null, ROLE_ETUDIANT));
			Role r5 = userService.saveRole(new Role(null, ROLE_PROFESSIONNEL));

			User u1= userService.saveUser(new User(null, "70804808", "tiec@gmail.com", "1234","1234", new HashSet<>()));

			userService.addRoleToUser(u1.getNumero(), r1.getName());

		};
	}
}
