package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "numero"),
                @UniqueConstraint(columnNames = "email")
        })
        public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String nomcomplet;

    @NotBlank
    @Size(max = 20)
    private String numero;

    @NotBlank
    @Size(max = 50)
    @Email(message = "S'il vous plaît veuillez entrer un email valide")
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 120)
    private String confirmpassword;

    /*@Column(name = "reset_token")
    private String resetToken;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String nomcomplet, String numero, String email, String password, String confirmpassword) {
        this.nomcomplet = nomcomplet;
        this.numero = numero;
        this.email = email;
        this.password = password;
        this.confirmpassword=confirmpassword;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setUsername(String numero) {
        this.numero = numero;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String numero) {
        this.nomcomplet = nomcomplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    /*public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }*/

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}


