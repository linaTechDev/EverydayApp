package com.lina.everydayapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lina.everydayapp.utilJwt.UtilJwt;
import com.lina.everydayapp.dtos.UtilisateurDto;
import com.lina.everydayapp.service.ServiceUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/utilisateur")
public class UtilisateurController {

  class ConnectedUser {
    public String token;
    public String prenom;
    public String nomFamille;
    public String nomUtilisateur;

    public ConnectedUser(String token, String prenom, String nomFamille, String nomUtilisateur) {
      this.token = token;
      this.prenom = prenom;
      this.nomFamille = nomFamille;
      this.nomUtilisateur = nomUtilisateur;
    }
  }

  class CreatedUser {
    public String prenom;
    public String nomFamille;
    public String nomUtilisateur;

    public CreatedUser(String prenom, String nomFamille, String nomUtilisateur) {
      this.prenom = prenom;
      this.nomFamille = nomFamille;
      this.nomUtilisateur = nomUtilisateur;
    }
  }

  private ServiceUtilisateur serviceUtilisateur;

  @PostMapping("/creation")
  public ResponseEntity<?> createUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
    if (serviceUtilisateur.existsByNomUtilisateur(utilisateurDto.getNomUtilisateur())) {
      return ResponseEntity
        .badRequest()
        .body("le nom d'utlisateur est déjà pris");
    }

    try {
      utilisateurDto = serviceUtilisateur.saveUtilisateur(utilisateurDto.toUtilisateur());

      CreatedUser createdUser = new CreatedUser(utilisateurDto.getPrenom(), utilisateurDto.getNomFamille(), utilisateurDto.getNomUtilisateur());
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String jsonCreatedUser = ow.writeValueAsString(createdUser);

      return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(jsonCreatedUser);
    }
    catch(Exception e) {
      return ResponseEntity
        .badRequest()
        .body(e.getMessage());
    }
  }

  @PostMapping("/connexion")
  public ResponseEntity<?> connexionUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {
    if (!serviceUtilisateur.existsByNomUtilisateur(utilisateurDto.getNomUtilisateur())) {
      return ResponseEntity
        .badRequest()
        .body("le nom d'utlisateur n'existe pas");
    }

    try {
      Boolean valide = serviceUtilisateur.validateAuthentification(utilisateurDto.getNomUtilisateur(), utilisateurDto.getMotPasse());
      if (valide) {

        utilisateurDto = serviceUtilisateur.findByNomUtilisateur(utilisateurDto.getNomUtilisateur());

        String token = UtilJwt.genereJWT(
          utilisateurDto.getNomUtilisateur(),
          utilisateurDto.getPrenom(),
          utilisateurDto.getNomFamille()
        );

        ConnectedUser connectedUser = new ConnectedUser(token, utilisateurDto.getPrenom(), utilisateurDto.getNomFamille(), utilisateurDto.getNomUtilisateur());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonConnectedUser = ow.writeValueAsString(connectedUser);

        return ResponseEntity
          .accepted()
          .body(jsonConnectedUser);

      } else {
        return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body("Mot de passe invalide");
      }
    }
    catch(Exception e) {
      return ResponseEntity
        .badRequest()
        .body(e.getMessage());
    }
  }
}
