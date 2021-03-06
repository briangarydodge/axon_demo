package com.dodgeb.axon_demo.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverCreatedEvent extends DomainAbstractEvent {

    private String dateCreated = "created";

    public DriverCreatedEvent(String identifier, String driverNumber) {
        super(identifier, driverNumber);
    }
}
