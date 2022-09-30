package com.lina.everydayapp.models;

import com.lina.everydayapp.dtos.WidgetsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "widgets")
public class Widgets {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String type;

  public Widgets(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public WidgetsDto toWidgetsDto() {
    return new WidgetsDto(
      String.valueOf(id),
      name,
      type
    );
  }

  @Override
  public String toString() {
    return "Widgets{" +
      "type='" + type + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
