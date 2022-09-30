package com.lina.everydayapp.dtos;

import com.lina.everydayapp.models.Raccourcis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaccourcisDto {
  private String id;
  private String nameSite;
  private String urlSite;
  private UtilisateurDto utilisateur;

  public Raccourcis toRaccourcis() {
    final Raccourcis raccourcis = new Raccourcis(
      nameSite,
      urlSite,
      utilisateur.toUtilisateur()
    );
    long oldId;
    try {
      oldId = Integer.parseInt(id);
      if (oldId > 0)
        raccourcis.setId(oldId);
    } catch (NumberFormatException ignored) {}
    return raccourcis;
  }
}
