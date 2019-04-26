package com.dodgeb.axon_demo.events;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class DriverCreatedEvent extends DomainAbstractEvent {

    private String dateCreated = "created";

    @Builder
    public DriverCreatedEvent(String identifier, String driverNumber) {
        super(identifier, driverNumber);
    }
}
