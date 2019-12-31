package com.dodgeb.axon_demo.commands;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@SuperBuilder
@Getter
public class CreateDriver extends Command {
    private String driverNumber;
}
