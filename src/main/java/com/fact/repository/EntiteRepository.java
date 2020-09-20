package com.fact.repository;

import com.fact.model.Entite;
import org.springframework.data.repository.CrudRepository;


public interface EntiteRepository extends CrudRepository<Entite, Long> {
    Entite saveAndFlush(Entite entite);

}
