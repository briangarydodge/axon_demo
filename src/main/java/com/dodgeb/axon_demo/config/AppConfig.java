package com.dodgeb.axon_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "AppObjectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
