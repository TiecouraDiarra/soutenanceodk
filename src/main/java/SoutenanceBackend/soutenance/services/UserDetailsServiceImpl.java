package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.Etudiant;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.EtudiantRepository;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String numeroOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByNumeroOrEmail(numeroOrEmail, numeroOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec ce numero ou email:" + numeroOrEmail));
        Etudiant etudiant = etudiantRepository.findByNumero(user.getNumero());
        //user.setUsername(etudiant);
        return UserDetailsImpl.build(user);
    }

}
