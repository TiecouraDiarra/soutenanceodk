package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.TypeQuestion;
import SoutenanceBackend.soutenance.Repository.TypeQuestionRepository;
import SoutenanceBackend.soutenance.services.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeQuestionImple implements TypeQuestionService {
    @Autowired
    private TypeQuestionRepository typeQuestionRepository;

    //SUPPRIMER UN TYPE DE QUESTION
    @Override
    public String Supprimer(Long id_typequestion) {
        typeQuestionRepository.deleteById(id_typequestion);
        return "Type supprimé avec succès!!";
    }

    //MODIFIER UN TYPE DE QUESTION
    @Override
    public TypeQuestion Modifier(TypeQuestion typeQuestion) {
        return typeQuestionRepository.findById(typeQuestion.getId())
                .map(p->{
                    p.setNomtypequestion(typeQuestion.getNomtypequestion());
                    return typeQuestionRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("type non trouvé !"));
    }

    //AFFICHER LES TYPES DE QUESTION
    @Override
    public List<TypeQuestion> Afficher() {
        return typeQuestionRepository.findAll();
    }

    //AJOUTER UN TYPE DE QUESTION
    @Override
    public Object Ajouter(TypeQuestion typeQuestion) {
        TypeQuestion typeQuestion1 = typeQuestionRepository.findByNomtypequestion(typeQuestion.getNomtypequestion());
        if (typeQuestion1==null){
            typeQuestionRepository.save(typeQuestion);
            return "Ajouter avec suucèss";
        }else {
            return "Existe déjà";
        }

    }
}
