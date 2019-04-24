package com.dodgeb.axon_demo.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface MIBIAble {

    default String payload() throws JsonProcessingException {

        return this.getClass().getName() + "{" + new ObjectMapper().writeValueAsString(this) +"]";

    }

}
