package com.dodgeb.axon_demo.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeDriverNumber {

    @TargetAggregateIdentifier
    private String identifier;

    private String driverNumber;
}
