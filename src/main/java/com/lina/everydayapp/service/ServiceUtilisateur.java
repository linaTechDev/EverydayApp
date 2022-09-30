package com.lina.everydayapp.service;

import com.lina.everydayapp.dtos.UtilisateurDto;
import com.lina.everydayapp.models.Raccourcis;
import com.lina.everydayapp.models.Utilisateur;
import com.lina.everydayapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtilisateur {

  private UtilisateurRepository utilisateurRepository;
  private ServiceRaccourcis serviceRaccourcis;

  public ServiceUtilisateur(UtilisateurRepository utilisateurRepository,
                            ServiceRaccourcis serviceRaccourcis) {
    this.utilisateurRepository = utilisateurRepository;
    this.serviceRaccourcis = serviceRaccourcis;
  }

  public UtilisateurDto saveUtilisateur(
    String prenom,
    String nomFamille,
    String nomUtilisateur,
    String motPasse
  ) {
    Utilisateur utilisateur = utilisateurRepository.save(new Utilisateur(
      prenom,
      nomFamille,
      nomUtilisateur,
      motPasse
    ));
    return utilisateur.toUtilisateurDto();
  }

  public UtilisateurDto saveUtilisateur(Utilisateur utilisateur) {
    UtilisateurDto utilisateurDto = utilisateurRepository.save(utilisateur).toUtilisateurDto();
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "google",
      "https://www.google.ca/?hl=fr",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "google traduction",
      "https://translate.google.com/?hl=fr",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "stackoverflow",
      "https://stackoverflow.com/",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "Looka",
      "https://looka.com/",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "Trello",
      "https://trello.com/fr",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "Pixabay",
      "https://pixabay.com/fr/photos/",
      utilisateurDto.toUtilisateur()
    ));
    serviceRaccourcis.saveRaccourcis(new Raccourcis(
      "Guide étudiant - AL",
      "https://etudiantcollegial.claurendeau.qc.ca/",
      utilisateurDto.toUtilisateur()
    ));
    return utilisateurDto;
  }

  public boolean existsByNomUtilisateur(String nomUtilisateur) {
    return utilisateurRepository.existsByNomUtilisateur(nomUtilisateur);
  }

  public UtilisateurDto findByNomUtilisateur(String nomUtilisateur) {
    Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur);
    if (utilisateur != null) {
      return utilisateur.toUtilisateurDto();
    }
    return null;
  }

  public boolean validateAuthentification(String nomUtilisateur, String motPasse) {
    Utilisateur utilisateur = findByNomUtilisateur(nomUtilisateur).toUtilisateur();
    if (utilisateur != null) {
      return motPasse.equals(utilisateur.getMotPasse());
    }
    return false;
  }
}
