package com.lina.everydayapp.dtos;

import com.lina.everydayapp.models.Widgets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WidgetsDto {
  private String id;
  private String name;
  private String type;

  public Widgets toWidgets() {
    final Widgets widgets = new Widgets(
      name,
      type
    );
    long oldId;
    try {
      oldId = Integer.parseInt(id);
      if (oldId > 0)
        widgets.setId(oldId);
    } catch (NumberFormatException ignored) {}
    return widgets;
  }
}
