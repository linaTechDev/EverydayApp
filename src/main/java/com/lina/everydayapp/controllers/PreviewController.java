package com.lina.everydayapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lina.everydayapp.dtos.InfoPreviewRaccourcisDto;
import com.lina.everydayapp.utilJwt.PreviewUtil;
import lombok.AllArgsConstructor;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/preview")
public class PreviewController {
  @PostMapping
  public ResponseEntity<?> getRaccourcisInfos(@Valid @RequestBody String raccourcisUrl) throws JsonProcessingException {
    try {
      InfoPreviewRaccourcisDto InfoPreviewRaccourcis = PreviewUtil.getInfoPreviewRaccourcis(raccourcisUrl).toInfoPreviewRaccourcisDto();

      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String jSonRaccourcisInfos = ow.writeValueAsString(InfoPreviewRaccourcis);

      return ResponseEntity
        .status(HttpStatus.OK)
        .body(jSonRaccourcisInfos);
    }
    catch(HttpStatusException e) {
      /*
      Pour gérer le cas de site comme "https://pixabay.com"
      qui retourne "FORBIDDuN (403) : HTTP error fetching URL"
       */
      InfoPreviewRaccourcisDto InfoPreviewRaccourcis = new InfoPreviewRaccourcisDto(
        HttpStatus.valueOf(e.getStatusCode()).name() +
          " (" + e.getStatusCode() + ")" +
          " : " + ((e.getCause() == null) ? e.getMessage() : e.getCause().getMessage())
      );

      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String jSonRaccourcisInfos = ow.writeValueAsString(InfoPreviewRaccourcis);

      return ResponseEntity
        .status(HttpStatus.OK)
        .body(jSonRaccourcisInfos);
    }
    catch(Exception e) {
      return ResponseEntity
        .badRequest()
        .body(e.getMessage());
    }
  }
}
