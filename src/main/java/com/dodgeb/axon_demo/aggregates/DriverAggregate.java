package com.dodgeb.axon_demo.aggregates;

import com.dodgeb.axon_demo.command.ChangeDriverNumber;
import com.dodgeb.axon_demo.command.CreateDriverCommand;
import com.dodgeb.axon_demo.events.DriverCreatedEvent;
import com.dodgeb.axon_demo.events.DriverNumberChanged;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Id;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverAggregate {

    @Id
    @AggregateIdentifier
    private String identifier;

    private String driverNumber;


    @CommandHandler
    public DriverAggregate(CreateDriverCommand command) {

        apply(new DriverCreatedEvent(command.getIdentifier(), command.getDriverNumber()));
    }

    @EventSourcingHandler
    public void handle(DriverCreatedEvent event) {

        this.identifier = event.getIdentifier();
        this.driverNumber = event.getDriverNumber();

    }

    @CommandHandler
    public void on(ChangeDriverNumber command) {

        apply(new DriverNumberChanged(command.getDriverNumber()));
    }

    @EventSourcingHandler
    public void handle(DriverNumberChanged event) {

        this.driverNumber = event.getDriverNumber();
    }


}
