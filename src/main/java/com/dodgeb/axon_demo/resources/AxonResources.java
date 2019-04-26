package com.dodgeb.axon_demo.resources;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dodgeb.axon_demo.commands.ChangeDriverNumber;
import com.dodgeb.axon_demo.commands.CreateDriver;
import com.dodgeb.axon_demo.requests.CreateDriverRequest;
import com.dodgeb.axon_demo.requests.UpdateDriverRequest;

import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class AxonResources {

    private final CommandGateway commandGateway;

    @Autowired
    public AxonResources(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/new", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity newDriver(@RequestBody final CreateDriverRequest driver)
            throws ExecutionException, InterruptedException {

        CreateDriver command = CreateDriver.builder()
                .identifier(UUID.randomUUID().toString())
                .driverNumber(driver.getDriverNumber())
                .build();

        CompletableFuture<String> commandResponse = commandGateway.send(command);
        Map.Entry<String, String> result = new AbstractMap.SimpleEntry<>("result", commandResponse.get());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/new/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity newDriver(@RequestBody final CreateDriverRequest driver, @PathVariable("id") String id)
            throws ExecutionException, InterruptedException {

        CreateDriver command = CreateDriver.builder()
                .identifier(id)
                .driverNumber(driver.getDriverNumber())
                .build();

        CompletableFuture<String> commandResponse = commandGateway.send(command);

        Map.Entry<String, String> result = new AbstractMap.SimpleEntry<>("result", commandResponse.get());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/{id}/update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateDriver(@RequestBody UpdateDriverRequest driver, @PathVariable("id") String id) {
        ChangeDriverNumber command = ChangeDriverNumber.builder()
                .identifier(id)
                .driverNumber(driver.getDriverNumber())
                .build();

        commandGateway.send(command);
        return ResponseEntity.ok().body("{ \"result\": \"completed\"}");
    }

}