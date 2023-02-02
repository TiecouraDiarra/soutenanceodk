package SoutenanceBackend.soutenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Size(max = 200)
    private String reponse;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;



    /*@ManyToOne
    @JoinColumn(name = "id_stringreponse")
    private StringReponse stringReponse;

    @OneToOne
    @JoinColumn(name = "id_intreponse")
    private IntReponse intReponse;*/
}
