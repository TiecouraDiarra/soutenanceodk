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
@Table(name = "professionnel")
public class professionnel extends User{
    public professionnel(String nomcomplet, String numero, String email, String password, String confirmpassword) {
        super(nomcomplet, numero, email, password, confirmpassword);
        //this.domaine = domaine;
    }

    /*@NotBlank
    @Size(max = 120)
    private String domaine;*/

    @ManyToOne
    @JoinColumn(name = "id_domaine")
    private DomaineProf domaineProf;
}
