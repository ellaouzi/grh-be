package com.fact.controller;

import com.fact.enums.CongeEvent;
import com.fact.enums.CongeState;
import com.fact.model.Logtrace;
import com.fact.repository.LogtraceRepository;
import com.fact.util.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {
    @Autowired
    LogtraceRepository logtraceRepository;
    @Autowired
    StateMachineFactory<CongeState, CongeEvent> factory;

    @GetMapping
    public String securedResource(Authentication auth) {

            StateMachine<CongeState, CongeEvent> sm = factory.getStateMachine(UUID.randomUUID());

            sm.start();

            System.out.println(sm.getState().toString());

            sm.sendEvent(CongeEvent.PRE_AUTHORIZE);

            System.out.println(sm.getState().toString());

            sm.sendEvent(CongeEvent.PRE_AUTH_APPROVED);

            System.out.println(sm.getState().toString());

            sm.sendEvent(CongeEvent.PRE_AUTH_DECLINED);

            System.out.println(sm.getState().toString());
            return "Test Statmachine Called!!";

    }

}