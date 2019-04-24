package com.dodgeb.axon_demo.projections;


import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.dodgeb.axon_demo.events.MIBIAble;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MIBIProjector {

    @EventHandler
    public void on (MIBIAble event) throws JsonProcessingException {

        log.info("MIBI payload 2: {}", event.payload());
    }
}
