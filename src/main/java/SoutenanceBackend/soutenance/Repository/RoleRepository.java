package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Optional<Role> findByName(ERole name);

    Role findByName(ERole name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');",nativeQuery = true)
    void creationRole();


    @Transactional
    @Query(value = "INSERT INTO roles (name) VALUES ('ROLE_ADMIN');",nativeQuery = true)
    Role creationRoleAdmin();
}
