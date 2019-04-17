package com.dodgeb.axon_demo.resources;

import com.dodgeb.axon_demo.command.ChangeDriverNumber;
import com.dodgeb.axon_demo.command.CreateDriverCommand;
import com.dodgeb.axon_demo.models.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class AxonResources {

    private final ObjectMapper mapper;

    private final CommandGateway commandGateway;


    public AxonResources(CommandGateway commandGateway,
                         @Qualifier("AppObjectMapper") ObjectMapper mapper) {
        this.commandGateway = commandGateway;
        this.mapper = mapper;
    }

    @PostMapping(value = "/new",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity newDriver(final HttpServletRequest request)
            throws ExecutionException, InterruptedException, IOException {

        CompletableFuture<String> result = null;
        String aggregateId = "none";

        Driver driver = mapper.readValue(retrieveJsonBody(request), Driver.class);

        if(driver != null) {
            CreateDriverCommand command = CreateDriverCommand.builder()
                    .identifier(driver.getIdentifier())
                    .driverNumber(driver.getDriverNumber())
                    .build();
            result = commandGateway.send(command);
        }

        if (result != null) {
            aggregateId = result.get();
        }

        return ResponseEntity
                .ok()
                .body("{\"AggregateId\": \"" + aggregateId + "\"}");
    }

    private Map<String, String> parsePathParams(HttpServletRequest request) {

        Iterator<String> pathParams = Arrays.asList("/update/{id}".split("/")).iterator();
        Iterator<String> pathValues = Arrays.asList(request.getServletPath().split("/")).iterator();

        Map<String, String> pathMap = new HashMap<>();

        while(pathParams.hasNext() && pathValues.hasNext()) {
            String key = pathParams.next();
            String value = pathValues.next();
            if(key.startsWith("{") && key.endsWith("}")) {
                key = key.replaceAll("\\{", "");
                key = key.replaceAll("}", "");
                pathMap.put(key, value);
            }
        }
        return pathMap;

    }

    @PostMapping(name = "/update/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateDriver(final HttpServletRequest request)
            throws IOException, ExecutionException, InterruptedException {

        Map<String, String> pathParams = parsePathParams(request);
        String id = pathParams.get("id");

        Driver driver = mapper.readValue(retrieveJsonBody(request), Driver.class);

        CompletableFuture<String> result = null;
        if(driver != null) {
            ChangeDriverNumber command = ChangeDriverNumber.builder()
                    .identifier(id)
                    .driverNumber(driver.getDriverNumber())
                    .build();
            result = commandGateway.send(command);
        }

        String aggregateId = "";
        if(result != null) {
            aggregateId = result.get();
        }

        return ResponseEntity
                .ok()
                .body("{\"AggregateId\": \"" + aggregateId + "\"}");
    }


    private String retrieveJsonBody(final HttpServletRequest request) throws IOException {
        String jsonString;
        try (BufferedReader reader = request.getReader()) {
            jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return jsonString;
    }

}
