package com.dodgeb.axon_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class AppConfig {

    @Bean(name = "AppObjectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }



    @Bean
    public SpringAMQPPublisher publisher() {

        return null;
    }

}
