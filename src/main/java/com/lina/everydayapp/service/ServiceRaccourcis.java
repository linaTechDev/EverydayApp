package com.lina.everydayapp.service;

import com.lina.everydayapp.dtos.RaccourcisDto;
import com.lina.everydayapp.dtos.UtilisateurDto;
import com.lina.everydayapp.models.Raccourcis;
import com.lina.everydayapp.models.Utilisateur;
import com.lina.everydayapp.repository.RaccourcisRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceRaccourcis {

  private RaccourcisRepository raccourcisRepository;

  public ServiceRaccourcis(RaccourcisRepository raccourcisRepository) {
    this.raccourcisRepository = raccourcisRepository;
  }

  public RaccourcisDto createRaccourcis(
    String nameSite,
    String urlSite,
    Utilisateur utilisateur
  ) {
    Raccourcis raccourcis = raccourcisRepository.save(
      new Raccourcis(
        nameSite,
        urlSite,
        utilisateur
      )
    );
    return raccourcis.toRaccourcisDto();
  }

  public RaccourcisDto saveRaccourcis(Raccourcis raccourcis) {
    return raccourcisRepository.save(raccourcis).toRaccourcisDto();
  }

  public List<RaccourcisDto> getAllRaccourcis(UtilisateurDto utilisateurDto) {
    List<Raccourcis> raccourcisList = raccourcisRepository.getRaccourcisUtilisateur(utilisateurDto.getNomUtilisateur());
    List<RaccourcisDto> raccourcisDtoList = new ArrayList<>();

    for (Raccourcis raccourcis : raccourcisList) {
      raccourcisDtoList.add(raccourcis.toRaccourcisDto());
    }
    return raccourcisDtoList;
  }

  public RaccourcisDto getRaccourcis(long raccourcisId) {
    return raccourcisRepository.findById(raccourcisId).get().toRaccourcisDto();
  }

  public void deleteRaccourcis(Raccourcis raccourcis) {
    raccourcisRepository.delete(raccourcis);
  }
}
