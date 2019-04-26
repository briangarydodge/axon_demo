package com.dodgeb.axon_demo.resources;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dodgeb.axon_demo.commands.CreateDriver;
import com.dodgeb.axon_demo.requests.CreateDriverRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.dodgeb.axon_demo.TestHelpers.isUUID;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AxonResourcesTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String UUID_ID = UUID.randomUUID().toString();

    private static final String DRIVER_NUMBER = "123abc";

    private static final CreateDriverRequest CREATE_DRIVER_REQUEST = new CreateDriverRequest(DRIVER_NUMBER);

    private static final String RESULT_STRING = "result";

    @InjectMocks
    AxonResources controller;

    @Mock
    CommandGateway commandGateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create Driver API Test")
    @SuppressWarnings("unchecked")
    void newDriver() throws Exception {

        when(commandGateway.send(any(CreateDriver.class))).thenReturn(CompletableFuture.completedFuture(UUID_ID));

        ResponseEntity result = controller.newDriver(CREATE_DRIVER_REQUEST);
        assumeTrue(result != null, "Response entity not provided.");
        assumeTrue(result.getBody() != null, "Response entity body not available.");
        Map<String, String> resultBody = MAPPER.readValue(result.getBody().toString(), Map.class);

        assertAll( () -> {
            verify(commandGateway).send(any(CreateDriver.class));
            assertThat(result.getStatusCode(), is(HttpStatus.OK));
            assertThat(resultBody.get(RESULT_STRING), instanceOf(String.class));
            assertThat(isUUID(resultBody.get(RESULT_STRING)), is(true));
        });

    }



}