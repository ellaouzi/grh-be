package com.fact.service;


import com.fact.enums.CongeEvent;
import com.fact.enums.CongeState;
import com.fact.model.Conge;
import com.fact.repository.CongeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CongeServiceImpl implements CongeService {
    public static final String PAYMENT_ID_HEADER = "conge_id";

    private final CongeRepository congeRepository;
    private final StateMachineFactory<CongeState, CongeEvent> stateMachineFactory;
    private final CongeStateChangeInterceptor congeStateChangeInterceptor;

    @Override
    public Conge newConge(Conge conge) {
        conge.setState(CongeState.NEW);
        return congeRepository.save(conge);
    }

    @Transactional
    @Override
    public StateMachine<CongeState, CongeEvent> preAuth(Long congeId) {
        StateMachine<CongeState, CongeEvent> sm = build(congeId);

        sendEvent(congeId, sm, CongeEvent.NOUVEAU);

        return sm;
    }

    @Transactional
    @Override
    public StateMachine<CongeState, CongeEvent> authorizeConge(Long congeId) {
        StateMachine<CongeState, CongeEvent> sm = build(congeId);

        sendEvent(congeId, sm, CongeEvent.NOUVEAU);

        return sm;
    }

    @Transactional
    @Override
    public StateMachine<CongeState, CongeEvent> declineAuth(Long congeId) {
        StateMachine<CongeState, CongeEvent> sm = build(congeId);

        sendEvent(congeId, sm, CongeEvent.NOUVEAU);

        return sm;
    }

    private void sendEvent(Long congeId, StateMachine<CongeState, CongeEvent> sm, CongeEvent event) {
        Message msg = MessageBuilder.withPayload(event)
                .setHeader(PAYMENT_ID_HEADER, congeId)
                .build();

        sm.sendEvent(msg);
    }

    private StateMachine<CongeState, CongeEvent> build(Long congeId) {
        Conge conge = congeRepository.findById(congeId).get();

        StateMachine<CongeState, CongeEvent> sm =
                stateMachineFactory.getStateMachine(Long.toString(conge.getId()));

        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(congeStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(conge.getState(), null, null, null));
                });

        sm.start();

        return sm;
    }
}
