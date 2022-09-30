package com.lina.everydayapp.repository;

import com.lina.everydayapp.models.Raccourcis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaccourcisRepository extends JpaRepository<Raccourcis, Long> {
  @Query(value = "SELECT r FROM Raccourcis r WHERE r.utilisateur.nomUtilisateur = :nomUtilisateur")
  List<Raccourcis> getRaccourcisUtilisateur(@Param("nomUtilisateur") String nomUtilisateur);
}
