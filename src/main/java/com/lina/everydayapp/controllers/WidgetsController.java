package com.lina.everydayapp.controllers;

import com.lina.everydayapp.dtos.WidgetsDto;
import com.lina.everydayapp.service.ServiceWidgets;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/widgets")
public class WidgetsController {
  private ServiceWidgets serviceWidgets;

  @GetMapping
  public List<WidgetsDto> getAllWidgets() {
    return serviceWidgets.getAllWidgets();
  }

  @GetMapping("/{id}")
  public WidgetsDto getWidgets(@PathVariable long id) {
    return serviceWidgets.getWidgets(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public WidgetsDto createWidgets(@RequestBody WidgetsDto widgetsDto) {
    return serviceWidgets.saveWidgets(widgetsDto.toWidgets());
  }

  @PutMapping("/{id}")
  public WidgetsDto updateWidgets(@PathVariable long id,
                                  @RequestBody WidgetsDto widgetsDtoDetail) {
    WidgetsDto widgetsDto = serviceWidgets.getWidgets(id);

    widgetsDto.setName(widgetsDtoDetail.getName());
    widgetsDto.setType(widgetsDtoDetail.getType());

    return serviceWidgets.saveWidgets(widgetsDto.toWidgets());
  }

  @DeleteMapping("/{id}")
  public void deleteWidgets(@PathVariable long id) {
    WidgetsDto widgets = serviceWidgets.getWidgets(id);
    serviceWidgets.deleteWidgets(widgets.toWidgets());
  }
}
