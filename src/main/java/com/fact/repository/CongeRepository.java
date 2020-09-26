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

    @Query(value = " select c.id ,c.date_depart , c.date_retour, c.nonbre_jour," +
            " c.state, c.utilisateur_id ,  u1.nom, u1.prenom, " +
            "UPPER(CONCAT( u1.nom , ' ' , u1.prenom , ' - ' , u1.matricule)) as fullname  , " +
            " u1.interim_id, concat(u2.nom, ' ', u2.prenom) as interim from conge c " +
            " join utilisateur u1 on u1.id = c. utilisateur_id " +
            "LEFT JOIN utilisateur AS u2 ON u2.id = u1.interim_id ", nativeQuery = true)
    List<CongeInrfaceDto> allConges();

    @Query(value = "select c.id ,c.date_depart , c.date_retour, c.nonbre_jour, c.state, c.utilisateur_id ," +
            "  u1.nom, u1.prenom, UPPER(CONCAT( u1.nom , ' ' , u1.prenom , ' - ' , u1.matricule)) as fullname , " +
            " u1.interim_id, concat(u2.nom, ' ', u2.prenom) as interim from conge c " +
            " join utilisateur u1 on u1.id = c. utilisateur_id " +
            "LEFT JOIN utilisateur AS u2 ON u2.id = u1.interim_id where u1.manager_id  =:id",
            nativeQuery = true)
    List<CongeInrfaceDto> congesByMangerId(@Param("id") Long  id);

    @Query(value = " select c.date_depart , c.date_retour, c.nonbre_jour, c.state, c.utilisateur_id , " +
            "UPPER(CONCAT( u.nom , ' ' , u.prenom , ' - ' , u.matricule)) as fullname  " +
            "from conge c join utilisateur u on u.id = c. utilisateur_id  where c.id =:id", nativeQuery = true)
    CongeItemDto congerById(@Param("id") Long  id);

}
