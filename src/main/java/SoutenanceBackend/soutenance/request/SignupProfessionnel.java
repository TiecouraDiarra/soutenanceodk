package SoutenanceBackend.soutenance.request;

import SoutenanceBackend.soutenance.Models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SignupProfessionnel {
    @Size(min = 3, max = 20)
    private String numero;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;




    @Size(min = 6, max = 40)
    private String password;


    @Size(min = 6, max = 40)
    private String confirmpassword;


    @Size(min = 6, max = 40)
    private String nomcomplet;

    /*@Size(min = 6, max = 40)
    private String domaine;*/

    private Set<String> role;
}
