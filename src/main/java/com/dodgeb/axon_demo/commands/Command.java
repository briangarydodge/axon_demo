package com.dodgeb.axon_demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Command {
    @TargetAggregateIdentifier
    private String identifier;
}
