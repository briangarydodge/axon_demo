package com.dodgeb.axon_demo.aggregates;

import com.dodgeb.axon_demo.commands.ChangeDriverNumber;
import com.dodgeb.axon_demo.commands.CreateDriver;
import com.dodgeb.axon_demo.events.DriverCreatedEvent;
import com.dodgeb.axon_demo.events.DriverNumberChanged;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DriverAggregateTest {

    private static final String IDENTIFIER = "123";

    private static final String DRIVER_NUMBER = "dln123";

    private AggregateTestFixture<DriverAggregate> fixture;


    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(DriverAggregate.class);
    }

    @Test
    @DisplayName("Test Driver Creation")
    void driverCreation() {

        CreateDriver command = CreateDriver.builder()
                .identifier(IDENTIFIER)
                .driverNumber(DRIVER_NUMBER)
                .build();

        DriverCreatedEvent event = null;
//                DriverCreatedEvent.builder()
//                .identifier(IDENTIFIER)
//                .driverNumber(DRIVER_NUMBER)
//                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);
    }

    @Test
    @DisplayName("Test Driver Number Change")
    void changeAddress() {

        DriverCreatedEvent event = null;
//                DriverCreatedEvent.builder()
//                .identifier(IDENTIFIER)
//                .driverNumber(DRIVER_NUMBER)
//                .build();

        fixture.given(event)
                .when(new ChangeDriverNumber("123", "dln444"))
                .expectEvents(new DriverNumberChanged("123", "dln444"));


    }




}