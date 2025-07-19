package com.tekup.revision.services;

import com.tekup.revision.entities.Utilisateur;
import com.tekup.revision.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    public void ajouterUser(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }
}
