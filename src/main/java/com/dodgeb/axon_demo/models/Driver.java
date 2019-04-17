package com.dodgeb.axon_demo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "drivers")
@TypeAlias("Driver")
public class Driver {

    private String _id;

    @Id
    private String identifier;

    private String driverNumber;

}
