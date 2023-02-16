package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.TypeMatiere;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import SoutenanceBackend.soutenance.Repository.TypeMatiereRepository;
import SoutenanceBackend.soutenance.services.TypeMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeMatiereImple implements TypeMatiereService {

    @Autowired
    private TypeMatiereRepository typeMatiereRepository;
    @Override
    public String Supprimer(Long id_typematiere) {
        typeMatiereRepository.deleteById(id_typematiere);
        return "Supprimer avec succès";
    }

    @Override
    public TypeMatiere Modifier(TypeMatiere typeQuestion) {
        return null;
    }

    @Override
    public List<TypeMatiere> Afficher() {
        return null;
    }

    @Override
    public Object Ajouter(TypeMatiere typeQuestion) {
        TypeMatiere typeMatiere = typeMatiereRepository.findByNomtypematiere(typeQuestion.getNomtypematiere());
        if (typeMatiere==null){
            typeMatiereRepository.save(typeQuestion);
            return "Ajouter avec suucèss";
        }else {
            return "Existe déjà";
        }
    }
}
