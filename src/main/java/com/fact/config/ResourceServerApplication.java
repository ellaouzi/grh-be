package com.fact.config;

import com.fact.enums.OrderStatus;
import com.fact.enums.OrderStatusChangeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@SpringBootApplication
@ComponentScan({"com.fact"})
@EnableJpaRepositories("com.fact.repository")
@EntityScan({"com.fact.model"})

public class ResourceServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

}