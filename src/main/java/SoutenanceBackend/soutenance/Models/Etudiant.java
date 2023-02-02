package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuperBuilder
@Entity
@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "etudiant")
public class Etudiant extends User{

    public Etudiant(String nomcomplet, String numero, String email, String password, String confirmpassword) {
        super(nomcomplet, numero, email, password, confirmpassword);
        //this.serieLycee = serie;
    }

    /*@Size(max = 120)
    private String serie;*/

    @ManyToOne
    @JoinColumn(name = "id_serie")
    private SerieLycee serieLycee;
}
