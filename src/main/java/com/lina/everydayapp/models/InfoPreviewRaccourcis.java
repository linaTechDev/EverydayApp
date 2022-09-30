package com.lina.everydayapp.models;

import com.lina.everydayapp.dtos.InfoPreviewRaccourcisDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoPreviewRaccourcis {
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

  public InfoPreviewRaccourcis(String domain, String url, String title, String description,
                   String imageUrl, String imageAlt, String favIconUrl,
                   String imageBase64, String favIconBase64) {
    this.domain = domain;
    this.url = url;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.imageAlt = imageAlt;
    this.favIconUrl = favIconUrl;
    this.imageBase64 = imageBase64;
    this.favIconBase64 = favIconBase64;
    this.errorMessage = "";
  }

  public InfoPreviewRaccourcisDto toInfoPreviewRaccourcisDto() {
    return new InfoPreviewRaccourcisDto(
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
  }
}
