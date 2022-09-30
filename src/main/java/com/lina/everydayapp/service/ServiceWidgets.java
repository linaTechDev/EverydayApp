package com.lina.everydayapp.service;

import com.lina.everydayapp.dtos.WidgetsDto;
import com.lina.everydayapp.models.Widgets;
import com.lina.everydayapp.repository.WidgetsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceWidgets {

  private WidgetsRepository widgetsRepository;

  public ServiceWidgets(WidgetsRepository widgetsRepository) {
    this.widgetsRepository = widgetsRepository;
  }

  public WidgetsDto createWidgets(
    String name,
    String type
  ) {
    Widgets widgets = widgetsRepository.save(
      new Widgets(
        name,
        type
      )
    );
    return widgets.toWidgetsDto();
  }

  public WidgetsDto saveWidgets(Widgets widgets) {
    return widgetsRepository.save(widgets).toWidgetsDto();
  }

  public List<WidgetsDto> getAllWidgets() {
    List<Widgets> widgetsList = widgetsRepository.findAll();
    List<WidgetsDto> widgetsDtoList = new ArrayList<>();

    for (Widgets widgets : widgetsList) {
      widgetsDtoList.add(widgets.toWidgetsDto());
    }
    return widgetsDtoList;
  }

  public WidgetsDto getWidgets(long widgetsId) {
    return widgetsRepository.findById(widgetsId).get().toWidgetsDto();
  }

  public void deleteWidgets(Widgets widgets) {
    widgetsRepository.delete(widgets);
  }
}
