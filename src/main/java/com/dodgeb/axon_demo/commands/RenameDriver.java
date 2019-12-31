package com.dodgeb.axon_demo.commands;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class RenameDriver extends Command {
    private String name;
}
