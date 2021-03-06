package com.dodgeb.axon_demo.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateDriverCommand {

    @TargetAggregateIdentifier
    private String identifier;

    private String driverNumber;

}
