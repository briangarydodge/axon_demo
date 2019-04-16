package com.dodgeb.axon_demo.resources;

import com.dodgeb.axon_demo.command.CreateDriverCommand;
import com.dodgeb.axon_demo.models.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AxonResources {

    private ObjectMapper mapper;

    private CommandGateway commandGateway;

    public AxonResources(CommandGateway commandGateway,
                         ObjectMapper mapper) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/new",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity newDriver(HttpServletRequest request) throws ExecutionException, InterruptedException {

        String jsonString;
        Driver driver = null;
        CompletableFuture<String> result = null;

        try (BufferedReader reader = request.getReader()) {
            jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            driver = new ObjectMapper().readValue(jsonString, Driver.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(driver != null) {
            CreateDriverCommand command = CreateDriverCommand.builder()
                    .identifier(driver.getIdentifier())
                    .driverNumber(driver.getDriverNumber())
                    .build();
            result = commandGateway.send(command);
        }


        return ResponseEntity.ok().body("{\"result\": \"" + result.get() + "\"}");
    }

    @GetMapping("/update")
    public ResponseEntity updateDriver() {


        return ResponseEntity.ok().body("{\"result\": \"ok\"}");
    }


}
