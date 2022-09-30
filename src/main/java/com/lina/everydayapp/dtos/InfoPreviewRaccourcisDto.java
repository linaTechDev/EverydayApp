package com.lina.everydayapp.dtos;

import com.lina.everydayapp.models.InfoPreviewRaccourcis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPreviewRaccourcisDto {
  private String domain;
  private String url;
  private String title;
  private String description;
  private String imageUrl;
  private String imageAlt;
  private String favIconUrl;
  private String imageBase64;
  private String favIconBase64;
  private String errorMessage;

  public InfoPreviewRaccourcisDto(String errorMessage) {
    this.domain = "";
    this.url = "";
    this.title = "";
    this.description = "";
    this.imageUrl = "";
    this.imageAlt = "";
    this.favIconUrl = "";
    this.imageBase64 = "";
    this.favIconBase64 = "";
    this.errorMessage = errorMessage;
  }

  public InfoPreviewRaccourcis toInfoPreviewRaccourcis() {
    final InfoPreviewRaccourcis infoPreviewRaccourcis = new InfoPreviewRaccourcis(
     domain,
    url,
    title,
    description,
    imageUrl,
    imageAlt,
    favIconUrl,
    imageBase64,
    favIconBase64,
    errorMessage
    );
    return infoPreviewRaccourcis;
  }
}
