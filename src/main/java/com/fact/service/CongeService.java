package com.fact.service;


import com.fact.enums.CongeEvent;
import com.fact.enums.CongeState;
import com.fact.model.Conge;
import org.springframework.statemachine.StateMachine;

public interface CongeService {
    Conge newConge(Conge conge);

    StateMachine<CongeState, CongeEvent> preAuth(Long congeId);

    StateMachine<CongeState, CongeEvent> authorizeConge(Long congeId);

    StateMachine<CongeState, CongeEvent> declineAuth(Long congeId);
}