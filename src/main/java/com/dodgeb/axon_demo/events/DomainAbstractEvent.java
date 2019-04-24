package com.dodgeb.axon_demo.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public abstract class DomainAbstractEvent implements MIBIAble, Reportable {

    private String identifier;

    private String driverNumber;

    @Override
    public String reportData() {
        return "my report";
    }
}
