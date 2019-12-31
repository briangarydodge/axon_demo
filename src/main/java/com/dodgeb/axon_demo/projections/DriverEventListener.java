package com.dodgeb.axon_demo.projections;

import com.dodgeb.axon_demo.events.DriverCreatedEvent;
import com.dodgeb.axon_demo.events.DriverNumberChanged;
import com.dodgeb.axon_demo.models.Driver;
import com.dodgeb.axon_demo.repositories.MongoDriverRespository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DriverEventListener {

    private MongoDriverRespository repository;

    public DriverEventListener(MongoDriverRespository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(DriverCreatedEvent event) {
        repository.save(Driver.builder()
                .identifier(event.getIdentifier())
                .driverNumber(event.getDriverNumber())
                .build()
        );
    }

    @EventHandler
    public void handle(DriverNumberChanged event) {

        Driver driver = repository.findByIdentifier(event.getIdentifier()).get(0);
        driver.setDriverNumber(event.getDriverNumber());
        repository.save(driver);

    }

}
