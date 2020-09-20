package com.fact.repository;

import com.fact.dto.UtilisateurDto;
import com.fact.model.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    Utilisateur saveAndFlush(Utilisateur utilisateur);
    @Query(value = "  select u.id as id, u.nom as nom, u.prenom as prenom, e.nom as entite , u.enabled as actif from utilisateur u join entite e on u.entite_id = e.id", nativeQuery = true)
    List<UtilisateurDto> allUtilisateurs();

    //
}
