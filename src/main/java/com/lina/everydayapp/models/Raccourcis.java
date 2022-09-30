package com.lina.everydayapp.models;

import com.lina.everydayapp.dtos.RaccourcisDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "raccourcis")
public class Raccourcis {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nameSite;
  private String urlSite;

  @ManyToOne
  @JoinColumn(name = "utilisateur")
  @ToString.Exclude
  private Utilisateur utilisateur;

  public Raccourcis(String nameSite, String urlSite, Utilisateur utilisateur) {
    this.nameSite = nameSite;
    this.urlSite = urlSite;
    this.utilisateur = utilisateur;
  }

  public RaccourcisDto toRaccourcisDto() {
    return new RaccourcisDto(
      String.valueOf(id),
      nameSite,
      urlSite,
      utilisateur.toUtilisateurDto()
    );
  }

  @Override
  public String toString() {
    return "Raccourcis{" +
      "nameSite='" + nameSite + '\'' +
      ", urlSite='" + urlSite + '\'' +
      '}';
  }
}
