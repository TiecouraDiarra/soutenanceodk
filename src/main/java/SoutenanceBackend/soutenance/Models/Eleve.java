package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Entity
@Getter
@Setter
@Component
@AllArgsConstructor
@Table(name = "eleve")
public class Eleve extends User{
    public Eleve(String nomcomplet, String numero, String email, String password, String confirmpassword) {
        super(nomcomplet, numero, email, password, confirmpassword);
    }
}
