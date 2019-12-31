package com.dodgeb.axon_demo.events;

import com.dodgeb.axon_demo.event_types.MIBIAble;
import com.dodgeb.axon_demo.event_types.Reportable;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class DriverCreatedEvent extends Event implements MIBIAble, Reportable {

    private String dateCreated = "created";
    private String identifier;
    private String driverNumber;

    @Override
    public String reportData() {
        return null;
    }

}
