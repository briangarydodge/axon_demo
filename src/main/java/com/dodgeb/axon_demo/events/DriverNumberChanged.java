package com.dodgeb.axon_demo.events;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
@Data
public class DriverNumberChanged extends DomainAbstractEvent {

    private String dateChanged = "changed";

    @Builder
    public DriverNumberChanged(String identifier, String driverNumber) {
        super(identifier, driverNumber);
    }
}
