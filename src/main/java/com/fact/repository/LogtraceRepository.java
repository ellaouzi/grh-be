package com.fact.repository;

import com.fact.model.Logtrace;
import org.springframework.data.repository.CrudRepository;


    public interface LogtraceRepository extends CrudRepository<Logtrace, Long> {
        Logtrace saveAndFlush(Logtrace logtrace);

    }
