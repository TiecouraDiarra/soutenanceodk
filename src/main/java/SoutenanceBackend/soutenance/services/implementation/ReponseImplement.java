package SoutenanceBackend.soutenance.services.implementation;

import SoutenanceBackend.soutenance.Models.Reponse;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.ReponseRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseImplement implements ReponseService {

    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public String Supprimer(Long id_reponse) {
        reponseRepository.deleteById(id_reponse);
        return "Reponse supprimée avec succès !!";
    }

    @Override
    public Reponse Modifier(Reponse reponse) {
        return reponseRepository.findById(reponse.getId())
                .map(p->{
                    p.setQuestion(reponse.getQuestion());

                    /*p.setIntReponse(reponse.getIntReponse());
                    p.setStringReponse(reponse.getStringReponse());*/

                    return reponseRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Reponse non trouvée !"));
    }

    @Override
    public List<Reponse> Afficher() {
        return reponseRepository.findAll();
    }

    @Override
    public Object Ajouter(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> AfficherListeReponseParUser(User user) {
        //reponseRepository.findByUtilisateur(user);
        return null;
    }

    /*@Override
    public Long TotaleScience(Long iduser, String nomtypematiere) {
        return reponseRepository.TotaleScience(iduser, nomtypematiere);
    }*/
}
