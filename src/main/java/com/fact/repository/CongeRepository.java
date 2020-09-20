package com.fact.repository;

import com.fact.model.Conge;
import org.springframework.data.repository.CrudRepository;

public interface CongeRepository extends CrudRepository<Conge, Long> {
    Conge saveAndFlush(Conge conge);

}
