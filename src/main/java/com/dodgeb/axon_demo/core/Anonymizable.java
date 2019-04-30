package com.dodgeb.axon_demo.core;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Anonymizable<U> {

    String anonymize(Object source, U rules) throws JsonProcessingException;

}
