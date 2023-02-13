package SoutenanceBackend.soutenance;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(SoutenanceApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {


			if (userRepository.findAll().size()==0 && roleRepository.findAll().size()==0){
				Role r1=userService.saveRole(new Role(null, ROLE_SUPERADMIN));
				Role r2 = userService.saveRole(new Role(null, ROLE_ADMIN));
				Role r3 = userService.saveRole(new Role(null, ROLE_ELEVE));
				Role r4 = userService.saveRole(new Role(null, ROLE_ETUDIANT));
				Role r5 = userService.saveRole(new Role(null, ROLE_PROFESSIONNEL));
				User u1= userService.saveUser(new User(null,"Tiecoura DIARRA", "70804808", "tiec@gmail.com", "12345678","12345678", new HashSet<>()));

				userService.addRoleToUser(u1.getNumero(), r1.getName());

			}

		};
	}
}
