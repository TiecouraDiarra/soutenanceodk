package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //Optional<User> findByNumero(String numero);

    User findByNumero(String numero);

    /*Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String resetToken);*/

    Optional<User> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (numero,email,password,confirmpassword) VALUES ('70804808','tiec@c.com', 'tiec1234', 'tiec1234');",nativeQuery = true)
    void creationadmin();
}
