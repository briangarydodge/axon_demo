package com.dodgeb.axon_demo.core;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class Anonymize {

    /**
     * Constant representing the character 'a'
     */
    private static final int LOWER_BOUNDS = 97;

    /**
     * Constant representing the character 'z'
     */
    private static final int UPPER_BOUNDS = 122;

    /**
     * Singleton instance of the Anonymize Class.
     */
    private static Anonymize instance;

    /**
     * Current state for ruleset.
     * Takes the Anonymizable Functional Interface.
     * If set will be used in anonymizing data when the
     * anonymize function called without implementation
     * of ruleset.
     */
    private Anonymizable ruleSet;

    /**
     * Lambda for Anonymizable using a collection of Predicates.
     * ** Currently Not Implemented **
     */
    public static final Anonymizable<List<Predicate<String>>> PREDICATE_ANONYMIZATION = (source, rules) -> {
        throw new UnsupportedOperationException("Not Implemented");
    };

    /**
     * Lambda for Anonymizable using a Map of Json Path and Supplied property values.
     */
    public static final Anonymizable<Map<String, Object>> MANAGED_ANONYMIZATION = (source, rules) -> {
        DocumentContext result = JsonPath.parse(new ObjectMapper().writeValueAsString(source));
        for (Map.Entry<String, Object> entry : rules.entrySet()) {
            Map.Entry<String, String> pathProperty = parsePathAndKey(entry.getKey());
            try {
                result.read(pathProperty.getValue() + "." + pathProperty.getKey()); // Ensure key exists, throws PathNotFoundException
                result = result.put(pathProperty.getValue(), pathProperty.getKey(), entry.getValue());
            } catch (PathNotFoundException exception) {
                log.debug("Unable to parse path: {}.{} Ignoring...", pathProperty.getValue(), pathProperty.getKey());
            }
        }
        return result.jsonString();
    };

    /**
     * Lambda for Anonymizable using a collection of Json Paths.
     * Values will be auto-generated.
     */
    public static final Anonymizable<List<String>> UNMANAGED_ANONYMIZATION = (source, rules) -> {
        DocumentContext result = JsonPath.parse(new ObjectMapper().writeValueAsString(source));
        for (String rule : rules) {
            Map.Entry<String, String> pathProperty = parsePathAndKey(rule);
            try {
                Object anonValue = anonymiseValue(result.read(pathProperty.getValue() + "." + pathProperty.getKey()));
                result = result.put(pathProperty.getValue(), pathProperty.getKey(), anonValue);
            } catch (PathNotFoundException exeception) {
                log.debug("Unable to parse path: {}.{} Ignoring...", pathProperty.getValue(), pathProperty.getKey());
            }
        }
        return result.jsonString();
    };

    /**
     * Private Constructor reqd for Singleton Instance.
     */
    private Anonymize () {}

    /**
     * Public method returning existing or new instance of the Anonymize class.
     * @return
     */
    public static Anonymize getInstance() {
        instance = Optional.ofNullable(instance).orElseGet(Anonymize::new);
        return instance;
    }

    /**
     * Public setter for storing required ruleset (Anonymizable interface)
     * @param ruleSet Implementation of the Anonymizable functional interface.
     * @return current instance of the Anonymize class.
     */
    public Anonymize setRuleSet(final Anonymizable ruleSet) {
        this.ruleSet = ruleSet;
        return this;
    }

    /**
     * Generalised method for taking source and result for anonymisation.
     * @param source Object represent any object to be mapped to json. (Jackson)
     * @param rules Supplier
     * @return String represent Json after anonymizing.
     * @throws JsonProcessingException if rules not recognised.
     */
    public String anonymize(final Object source, final Supplier rules) throws JsonProcessingException {
        Object suppliedRules = rules.get();
        if (suppliedRules instanceof List) {
            return this.anonymize(source, (List) suppliedRules, this.ruleSet);
        } else if (suppliedRules instanceof Map) {
            return this.anonymize(source, (Map) suppliedRules, this.ruleSet);
        } else {
            throw new IllegalArgumentException("Unrecognised rules type");
        }
    }

    @SuppressWarnings("unchecked")
    public String anonymize(final Object source, final List rules, final Anonymizable ruleType) throws JsonProcessingException {
        return ruleType.anonymize(source, rules);
    }

    @SuppressWarnings("unchecked")
    public String anonymize(final Object source, final Map rules, final Anonymizable ruleType) throws JsonProcessingException {
        return ruleType.anonymize(source, rules);
    }

    private static AbstractMap.SimpleEntry<String, String> parsePathAndKey(final String property) {
        String path = "$";
        String key = property;
        String[] parts = property.split("\\.");
        if (parts.length >= 2) {
            path = "$." + Arrays.stream(Arrays.copyOf(parts, parts.length - 1))
                    .collect(Collectors.joining("."));
            key = parts[parts.length - 1];
        }
        return new AbstractMap.SimpleEntry<>(key, path);
    }

    private static Object anonymiseValue(Object value) {
        Object result = null;
        if (value instanceof String) {
            result = anonymiseString(5);
        } else if (value instanceof Integer || value instanceof Double) {
            result = 0;
        }
        return result;
    }

    private static String anonymiseString(final int length) {
        return IntStream.range(0,length).mapToObj(val ->
                String.valueOf((char)(LOWER_BOUNDS + (int)(new Random().nextFloat() * (UPPER_BOUNDS - LOWER_BOUNDS + 1)))))
                .collect(Collectors.joining());
    }

}
