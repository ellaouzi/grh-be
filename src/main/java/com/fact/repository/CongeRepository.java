package com.fact.repository;

import com.fact.dto.CongeInrfaceDto;
import com.fact.dto.CongeItemDto;
import com.fact.model.Conge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CongeRepository extends CrudRepository<Conge, Long> {
    Conge saveAndFlush(Conge conge);
    @Query(value = " select c.id ,c.date_depart , c.date_retour, c.nonbre_jour, c.state, c.utilisateur_id ," +
            " u.nom, u.prenom,  UPPER(CONCAT( u.nom , ' ' , u.prenom , ' - ' , u.matricule)) as fullname  " +
            "  from conge c join utilisateur u on u.id = c. utilisateur_id ", nativeQuery = true)
    List<CongeInrfaceDto> allConges();

    @Query(value = " select c.date_depart , c.date_retour, c.nonbre_jour, c.state, c.utilisateur_id , " +
            "UPPER(CONCAT( u.nom , ' ' , u.prenom , ' - ' , u.matricule)) as fullname  " +
            "from conge c join utilisateur u on u.id = c. utilisateur_id  where c.id =:id", nativeQuery = true)
    CongeItemDto congerById(@Param("id") Long  id);

}
