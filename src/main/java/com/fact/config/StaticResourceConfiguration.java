package com.fact.config;


import com.fact.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
class StaticResourceConfiguration implements WebMvcConfigurer {
    @Autowired
    GlobalProperties globalProperties;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String myExternalFilePath = "file:///" + globalProperties.getUPLOADED_FACTURES()+ "/";
        registry.addResourceHandler("/photos/**").addResourceLocations(myExternalFilePath);
// http://localhost:8088/e-facture/factures/file_name.pdf
    }

}