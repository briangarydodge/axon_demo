package com.dodgeb.axon_demo.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DriverCreatedEvent {

    private String identifier;

    private String driverNumber;

}
