package com.fact.util;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Data
@PropertySource("classpath:global.properties")
public class GlobalProperties {



    @Value("${UPLOADED_FACTURES}")
    private String UPLOADED_FACTURES;
    @Value("${maxSize}")
    private long maxSize;



}
