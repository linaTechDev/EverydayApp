package com.lina.everydayapp.controllers;

import com.lina.everydayapp.dtos.RaccourcisDto;
import com.lina.everydayapp.dtos.RaccourcisForm;
import com.lina.everydayapp.dtos.UtilisateurDto;
import com.lina.everydayapp.service.ServiceRaccourcis;
import com.lina.everydayapp.service.ServiceUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/raccourcis")
public class RaccourcisController {
  private ServiceRaccourcis serviceRaccourcis;
  private ServiceUtilisateur serviceUtilisateur;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{nomUtilisateur}")
  public List<RaccourcisDto> getAllRaccourcisUtilisateur(@PathVariable String nomUtilisateur) {
    UtilisateurDto utilisateurDto = serviceUtilisateur.findByNomUtilisateur(nomUtilisateur);
    if (utilisateurDto == null) {
      throw new NullPointerException();
    }
    else {
      return serviceRaccourcis.getAllRaccourcis(utilisateurDto);
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public RaccourcisDto createRaccourcis(@RequestBody RaccourcisForm raccourcisForm) {
    UtilisateurDto utilisateurDto = serviceUtilisateur.findByNomUtilisateur(raccourcisForm.getNomUtilisateur());
    RaccourcisDto raccourcisDto = new RaccourcisDto(
      null,
      raccourcisForm.getNameSite(),
      raccourcisForm.getUrlSite(),
      utilisateurDto);
    return serviceRaccourcis.saveRaccourcis(raccourcisDto.toRaccourcis());
  }

  @PutMapping("/{id}")
  public RaccourcisDto updateRaccourcis(@PathVariable long id,
                                        @RequestBody RaccourcisDto raccourcisDtoDetail) {
    RaccourcisDto raccourcis = serviceRaccourcis.getRaccourcis(id);

    raccourcis.setNameSite(raccourcisDtoDetail.getNameSite());
    raccourcis.setUrlSite(raccourcisDtoDetail.getUrlSite());

    return serviceRaccourcis.saveRaccourcis(raccourcis.toRaccourcis());
  }

  @DeleteMapping("/{id}")
  public void deleteRaccourcis(@PathVariable long id) {
    RaccourcisDto raccourcis = serviceRaccourcis.getRaccourcis(id);
    serviceRaccourcis.deleteRaccourcis(raccourcis.toRaccourcis());
  }
}
