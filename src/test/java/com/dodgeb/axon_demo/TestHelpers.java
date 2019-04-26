package com.dodgeb.axon_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelpers {

    /**
     * Boolean checker for UUID formatted String.
     * @param uuid UUID in string format.
     * @return boolean response indicating valid uuid format.
     */
    public static boolean isUUID(final String uuid) {
        boolean result = false;
        try {
            UUID id = UUID.fromString(uuid);
            result = true;
        } catch (IllegalArgumentException exception) {
            // do nothing
        }
        return result;
    }

    /**
     * Retrieve a System Resource Fixture and Serialize to specialised class type.
     * @param path Location of fixture in Test Resources.
     * @param valueType Required class type when generating returned object.
     * @param <T> Generic Template for returned class type.
     * @return specialised object match class type.
     * @throws IOException Exceptions occurring during resource retrieval or serialisation.
     */
    public static <T> T getFixture(final String path, final Class<T> valueType) throws IOException {

        final InputStream resourceFile = TestHelpers.class.getClassLoader().getResourceAsStream(path);
        String resourceAsString = null;
        T result = null;

        if(resourceFile != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile))) {
                resourceAsString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }

        if (resourceAsString != null) {
            result = new ObjectMapper().readValue(resourceAsString, valueType);
        }

        return result;
    }

}
