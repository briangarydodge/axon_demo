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
     * Load a Json file into memory and map to specific object.
     * Use:
     *  Save a Json file inside the test resources folder (src->test->resources).
     *  Files can be in sub folders, as long as the path supplied to the function
     *  clearly references the sub path.
     *  Example; if <b>example.json</b> is stored in the folder <i>fixtures/input</i>,
     *  the function would be:
     *      <code>
     *          Map<String, String> result = getFixture("fixtures/input/test.json", Map.class);
     *      </code>
     *  <b>Note</b>
     *  You do not need to explicitly provide the full path of the file i.e.
     *      {project}/src/test/resources/fixtures/input/test.json
     *  this would result in null being returned as a result of a missing file.
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
