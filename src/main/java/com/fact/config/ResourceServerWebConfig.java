package com.fact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc

//@ComponentScan({ "com.fact.web" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
    //
}
