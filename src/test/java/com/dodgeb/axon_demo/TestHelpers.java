package com.dodgeb.axon_demo;

import java.util.UUID;

public class TestHelpers {


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

}
