# 8. Axon - First Steps (Handle Commands and Events)

We now need to update our aggregate (**DriverAggregate**) to handle the command and Events to fully satisify our test requirements.
For this we need to go back to the **DriverAggregate** class and update the code.

Firstly we need to handle the **CreateDriverCommand** command, this is done by adding the following code:

``` java

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverAggregate {

    @AggregateIdentifier
    private String identifier;

    private String driverNumber;
    
    /**
     * Handle CreateDriverCommand.
     * @param command Command Object with required properties.
     */
    @CommandHandler
    public DriverAggregate(CreateDriverCommand command) {
        apply(new DriverCreatedEvent(command.getIdentifier(), command.getDriverNumber()));
    }

}
```

We have now added a new method (specifically a constructor in this instance), which takes our CreateDriverCommand object
as an argument. The annotation ```@CommandHandler``` registers the function as a listener whenever the command is issued
by the CommandBus. We have also added one line of code ```apply```, which takes our **DriverCreatedEvent** as an argument.

However, we still have yet to deal with the state change of our aggregate. This done by handlin the event create by the above
```apply``` method. Add the following code under our new constructor.

``` java
    @EventSourcingHandler
    public void handle(DriverCreatedEvent event) {
        this.identifier = event.getIdentifier();
        this.driverNumber = event.getDriverNumber();
    }
```

This new method is registered with the Axon Event Bus with the annotation ```@EventSourcingHandler``` and the method must
match the signature of ```public void handle(EventType event)```. The code the simply updates the state of our aggregate.

When we now run our unit test, we should successfully pass.