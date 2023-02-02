package SoutenanceBackend.soutenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "questioN")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "id_typequestion")
    private TypeQuestion typeQuestion;

    @ManyToOne
    @JoinColumn(name = "id_typeMatiere")
    private TypeMatiere typeMatiere;

/*
    @OneToOne
    @JoinColumn(name = "id_matierequestion")
    private MatiereQuestion matiereQuestion;
*/



    /*@JsonIgnore
    @ManyToMany(mappedBy = "questions")
    List<Autoevaluation> autoevaluations = new ArrayList<>();*/
    /*@NotBlank
    @Size(max = 400)
    private String question;

    @ManyToOne
    @JoinColumn(name = "id_autoevaluation")
    private Autoevaluation autoevaluation;*/
}
