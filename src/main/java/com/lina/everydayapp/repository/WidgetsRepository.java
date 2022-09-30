package com.lina.everydayapp.repository;

import com.lina.everydayapp.models.Widgets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetsRepository extends JpaRepository<Widgets, Long> {
}
