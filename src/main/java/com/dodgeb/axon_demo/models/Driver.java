package com.dodgeb.axon_demo.models;

import javax.persistence.Id;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "drivers")  // target mongo collection
@TypeAlias("Driver")  // set _class in mongo document to Alias
public class Driver {

    private String _id; // Mongo Object Id

    @Id
    private String identifier;

    private String driverNumber;

}
