package com.fact.repository;

import com.fact.model.Entite;
import com.fact.model.Fonction;
import org.springframework.data.repository.CrudRepository;


public interface FonctionRepository extends CrudRepository<Fonction, Long> {
    Fonction saveAndFlush(Fonction fonction);

}
