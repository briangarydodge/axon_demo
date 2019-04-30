package com.dodgeb.axon_demo.core;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnonymizeTest {

    private static Map<String, Object> source;

    @BeforeAll
    static void setup() {
        source = new HashMap<>();
        Map<String, String> address = new HashMap<>();
        Map<String, Object> drivingLicence = new HashMap<>();
        address.put("addressLine1", "address1");
        address.put("addressLine2", "address2");
        address.put("postcode", "SA194AW");
        drivingLicence.put("yearsActive", 5);
        drivingLicence.put("driverNumber", "abc123");
        source.put("name", "a name");
        source.put("address", address);
        source.put("age", 100.1);
        source.put("drivingLicence", drivingLicence);
    }

    @Test
    @SuppressWarnings("unchecked")
    void unmanagedAnonymise() throws Exception {

        List<String> hitList = Arrays.asList("address.addressLine2", "name", "age", "drivingLicence", "foo.bar");

        String jsonString = Anonymize.getInstance().anonymize(source, hitList, Anonymize.UNMANAGED_ANONYMIZATION);

        Map<String, Object> result = new ObjectMapper().readValue(jsonString, Map.class);

        assertThat(((String)result.get("name")).length(), is(5));
    }

    @Test
    @SuppressWarnings("unchecked")
    void unmanagedAnonymiseWithoutResultSet() throws Exception {

        List<String> hitList = Arrays.asList("address.addressLine2", "name", "age", "drivingLicence", "foo.bar");

        Anonymize anonymize = Anonymize.getInstance();
        anonymize.setRuleSet(Anonymize.UNMANAGED_ANONYMIZATION);

        String jsonString = anonymize.anonymize(source, () -> hitList);

        Map<String, Object> result = new ObjectMapper().readValue(jsonString, Map.class);

        assertThat(((String)result.get("name")).length(), is(5));
    }

    @Test
    @SuppressWarnings("unchecked")
    void managedAnonymised() throws Exception {

        Map<String, Object> hitList = Stream.of(
                new AbstractMap.SimpleEntry<>("name", ""),
                new AbstractMap.SimpleEntry<>("drivingLicence.yearsActive", 10),
                new AbstractMap.SimpleEntry<>("address.addressLine1", ""),
                new AbstractMap.SimpleEntry<>("foo", "bar")
        ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        String jsonString = Anonymize.getInstance().anonymize(source, hitList, Anonymize.MANAGED_ANONYMIZATION);

        Map<String, Object> result = new ObjectMapper().readValue(jsonString, Map.class);

        assertThat(result.get("name"), is(""));

    }

    @Test
    @SuppressWarnings("unchecked")
    void managedAnonymisedWithoutRuleSet() throws Exception {

        Map<String, Object> hitList = Stream.of(
                new AbstractMap.SimpleEntry<>("name", ""),
                new AbstractMap.SimpleEntry<>("drivingLicence.yearsActive", 10),
                new AbstractMap.SimpleEntry<>("address.addressLine1", ""),
                new AbstractMap.SimpleEntry<>("foo", "bar")
        ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        Anonymize anonymize = Anonymize.getInstance();
        anonymize.setRuleSet(Anonymize.MANAGED_ANONYMIZATION);

        String jsonString = Anonymize.getInstance().anonymize(source, () -> hitList);

        Map<String, Object> result = new ObjectMapper().readValue(jsonString, Map.class);

        assertThat(result.get("name"), is(""));

    }

    @Test
    void incorrectRulesTypeException() throws Exception {

        Anonymize anonymize = Anonymize.getInstance().setRuleSet(Anonymize.MANAGED_ANONYMIZATION);

        assertThrows(IllegalArgumentException.class, () -> anonymize.anonymize(source, () -> "error"));

    }

    @Test
    void predicateRulesNotImplemented() throws Exception {

        Anonymize anonymize = Anonymize.getInstance().setRuleSet(Anonymize.PREDICATE_ANONYMIZATION);

        assertThrows(UnsupportedOperationException.class, () -> anonymize.anonymize(source, ArrayList::new));

    }

}