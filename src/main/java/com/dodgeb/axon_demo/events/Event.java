package com.dodgeb.axon_demo.events;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Event {
    private String identifier;
}
