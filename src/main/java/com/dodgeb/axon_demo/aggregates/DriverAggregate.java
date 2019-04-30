package com.dodgeb.axon_demo.aggregates;

import javax.persistence.Id;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.dodgeb.axon_demo.commands.ChangeDriverNumber;
import com.dodgeb.axon_demo.commands.CreateDriver;
import com.dodgeb.axon_demo.events.DriverCreatedEvent;
import com.dodgeb.axon_demo.events.DriverNumberChanged;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Slf4j
@Aggregate
@NoArgsConstructor
public class DriverAggregate {

    @Id
    @AggregateIdentifier
    private String identifier;

    private String driverNumber;

    /**
     * Handle CreateDriver.
     * @param command Command Object with required properties.
     */
    @CommandHandler
    public DriverAggregate(CreateDriver command) {
        apply(new DriverCreatedEvent(command.getIdentifier(), command.getDriverNumber()));
    }

    @EventSourcingHandler
    public void handle(DriverCreatedEvent event) {
        this.identifier = event.getIdentifier();
        this.driverNumber = event.getDriverNumber();
    }

    @CommandHandler
    public void on(ChangeDriverNumber command) {
        apply(new DriverNumberChanged(command.getIdentifier(), command.getDriverNumber()));
    }

    @EventSourcingHandler
    public void handle(DriverNumberChanged event) {
        this.driverNumber = event.getDriverNumber();
    }

}
