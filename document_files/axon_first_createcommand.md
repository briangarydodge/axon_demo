# 6. Axon - First Steps (Creating our Command)

As our IDE/Compiler demonstrates, we need to create a new class for **CreateDriverCommand**, which implements a builder method. Start by right 
clicking on the **com.dodgeb.axon_demo** package and selecting **New** then **Java Class** and enter *commands.CreateDriverCommand*, which
will create the following class code:

```java
package com.dodgeb.axon_demo.commands;

public class CreateDriverCommand {

}
```
Update the code to read as follows:

``` java

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateDriverCommand {

    @TargetAggregateIdentifier
    private String identifier;

    private String driverNumber;

}
```

we have created a plain java object, which stores the require properties to fulfil our desired command - in this case to create a 
new record with identifier and driverNumber.

By using Lombok and adding the ```@NoArgsConstructor```, ```@AllArgsConstructor```, ```@Builder``` and ```@Data``` annotations, the 
required constructors, getters and setters are automatically generated and do not need to be maintained as properties are added or removed.

There is a new annotation ```@TargetAggregateIdentifier```, this tells axon which aggregate the command relates to.

We have now fulfilled the first compiler requirement, and now need to create the Event to mee the next compiler error requirement.

Next. [Axon - First Steps (Creating our Event)](axon_first_createevent.md)