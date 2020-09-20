package com.fact.config;


import com.fact.enums.CongeEvent;
import com.fact.enums.CongeState;
import com.fact.service.CongeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.Random;

@Slf4j
@EnableStateMachineFactory
@Configuration
public class StateMachineConfig extends StateMachineConfigurerAdapter<CongeState, CongeEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<CongeState, CongeEvent> states) throws Exception {
        states.withStates()
                .initial(CongeState.NEW)
                .states(EnumSet.allOf(CongeState.class))
                .end(CongeState.AUTH)
                .end(CongeState.PRE_AUTH_ERROR)
                .end(CongeState.AUTH_ERROR);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<CongeState, CongeEvent> transitions) throws Exception {
        transitions.withExternal().source(CongeState.NEW).target(CongeState.NEW).event(CongeEvent.PRE_AUTHORIZE)
                .action(preAuthAction()).guard(congeIdGuard())
                .and()
                .withExternal().source(CongeState.NEW).target(CongeState.PRE_AUTH).event(CongeEvent.PRE_AUTH_APPROVED)
                .and()
                .withExternal().source(CongeState.NEW).target(CongeState.PRE_AUTH_ERROR).event(CongeEvent.PRE_AUTH_DECLINED)
                //preauth to auth
                .and()
                .withExternal().source(CongeState.PRE_AUTH).target(CongeState.PRE_AUTH).event(CongeEvent.AUTHORIZE)
                .action(authAction())
                .and()
                .withExternal().source(CongeState.PRE_AUTH).target(CongeState.AUTH).event(CongeEvent.AUTH_APPROVED)
                .and()
                .withExternal().source(CongeState.PRE_AUTH).target(CongeState.AUTH_ERROR).event(CongeEvent.AUTH_DECLINED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<CongeState, CongeEvent> config) throws Exception {
        StateMachineListenerAdapter<CongeState, CongeEvent> adapter = new StateMachineListenerAdapter<CongeState, CongeEvent>() {
            @Override
            public void stateChanged(State<CongeState, CongeEvent> from, State<CongeState, CongeEvent> to) {
                //   log.info(String.format("stateChanged(from: %s, to: %s)", from, to));
            }
        };

        config.withConfiguration()
                .listener(adapter);
    }

    public Action<CongeState, CongeEvent> preAuthAction() {
        return context -> {
            System.out.println("PreAuth was called!!!");

            if (new Random().nextInt(10) < 5) {
                System.out.println("Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(CongeEvent.PRE_AUTH_APPROVED)
                        .setHeader(CongeServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(CongeServiceImpl.PAYMENT_ID_HEADER))
                        .build());

            } else {
                System.out.println("Declined! No Credit!!!!!!");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(CongeEvent.PRE_AUTH_DECLINED)
                        .setHeader(CongeServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(CongeServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }

    public Action<CongeState, CongeEvent> authAction(){
        return context -> {
            System.out.println("Auth was called!!!");

            if (new Random().nextInt(10) < 5) {
                System.out.println("Auth Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(CongeEvent.AUTH_APPROVED)
                        .setHeader(CongeServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(CongeServiceImpl.PAYMENT_ID_HEADER))
                        .build());

            } else {
                System.out.println("Auth Declined! No Credit!!!!!!");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(CongeEvent.AUTH_DECLINED)
                        .setHeader(CongeServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(CongeServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }

    public Guard<CongeState, CongeEvent> congeIdGuard() {
        return context ->
                context.getMessageHeader(CongeServiceImpl.PAYMENT_ID_HEADER) != null;

    }
}