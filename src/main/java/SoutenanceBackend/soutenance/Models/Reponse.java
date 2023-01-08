package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@Table(name = "reponse")
@NoArgsConstructor
@AllArgsConstructor
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 200)
    private String reponse;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "id_utilisateur")
    private User utilisateur;
}
