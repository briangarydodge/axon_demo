package com.dodgeb.axon_demo.projections;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.dodgeb.axon_demo.event_types.Reportable;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportProjector {

    @EventHandler
    public void on (Reportable event) throws JsonProcessingException {

        log.info("Report payload 2: {}", event.reportData());
    }


}
