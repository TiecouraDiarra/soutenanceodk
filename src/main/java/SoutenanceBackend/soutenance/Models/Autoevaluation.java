package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "Autoevaluation")
@NoArgsConstructor
@AllArgsConstructor
public class Autoevaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotBlank
    @Size(max = 500)
    private String nomautoevaluation;*/

    private Date dateauto;


    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "id_utilisateur")
    private User utilisateur;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "auto_question",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    List<Question> questions = new ArrayList<>();*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "auto_reponse",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "reponse_id"))
    List<Reponse> reponses = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "auto_parcours",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "parcours_id"))
    List<Parcours> parcours = new ArrayList<>();
}
