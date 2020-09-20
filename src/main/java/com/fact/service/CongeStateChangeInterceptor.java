package com.fact.service;

import com.fact.enums.CongeEvent;
import com.fact.enums.CongeState;
import com.fact.model.Conge;
import com.fact.repository.CongeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CongeStateChangeInterceptor extends StateMachineInterceptorAdapter<CongeState, CongeEvent> {

    private final CongeRepository congeRepository;

    @Override
    public void preStateChange(State<CongeState, CongeEvent> state, Message<CongeEvent> message,
                               Transition<CongeState, CongeEvent> transition,
                               StateMachine<CongeState, CongeEvent> stateMachine) {

        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable(
                    Long.class.cast(msg.getHeaders().getOrDefault(CongeServiceImpl.PAYMENT_ID_HEADER, -1L)))
                    .ifPresent(congeId -> {
                        Conge conge = congeRepository.findById(congeId).get();
                        conge.setState(state.getId());
                        congeRepository.save(conge);
                    });
        });
    }
}