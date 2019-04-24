# 4. Axon - First Steps (Creating our Aggregate)

In this section, we are going to create an **Aggregate**, which is a special class for storing the state of an object
as well as methods for updating the state of the object.

In our IDE, right click on the package com.dodgeb.axon_demo and select **New** then **Java Class**. In the **Name** field, 
enter *aggregates/DriverAggregate*. This will create a new package called **aggregates** and a new class called **DriverAggregate**
 with the following code:
 
 ``` java
 package com.dodgeb.axon_demo.aggregates;
 
 public class DriverAggregate {
 
 }
 ```
 
 We now are going to update the code to the following:
 
 ``` java 
@Aggregate
@AllArgsConstructor
@Data
package com.dodgeb.axon_demo.aggregates;

public class DriverAggregate {

        @AggregateIdentifier
        private String identifier;
        
        private String driverNumber;
}
 ```
 
 We have now created a basic class containing two properties; *identifier* and *driverNumber* for storing our state information.
 There are some other pieces of code, which may be new.
 
 ```@Aggregate``` registers the class with Axon as an aggregate, which will be the target of state change events.
 ```@NoArgsConstructor``` (Lombok Annotation) behind the scenes creates a empty (default) constructor, which is used by Axon and Jackson (For 
 JSON Serialisation and De-serialisation).
 ```@Data``` (Lombok Annotation), which provides Getters and Setters (as well as other object methods).
 
 Now that we have our Aggregate, we can get down producing Commands and Events, which will be used for updating our Aggregate. We
 going to do this in a Test Driven way, by creating the Tests first then updating the code to make our tests pass.
 
 