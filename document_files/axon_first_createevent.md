# 7. Axon - First Steps (Creating our Event)

To remove the last compiler error, we now need to create the **DriverCreatedEvent** class, this is done much in the same way as
the command by right clicking on the **com.dodgeb.axon_demo** package, selecting **New** and then **Java Class**. Enter 
*events.DriverCreatedEvent* and click **OK**. Update the code as follows:

``` java
package com.dodgeb.axon_demo.events;

@Data
@NoArgsConstructor
@Builder
public class DriverCreatedEvent {

    private String identifier;
    
    private String driverNumber;
}
```

This satisfies the remaining compiler error. However, now our code compiles, the test still fails. This is because we have not implemented
the logic in our aggregate to process the command or to update the state.

Next. [Axon - First Steps(Handle Commands and Events)](axon_first_handle.md)