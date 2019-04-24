# 5. Axon - First Steps (Creating our Test)

 To start, right-click on the class name (*DriverAggregate*) and select **Generate** and then select **Test...**.  
 Make sure the **Testing Library** is JUnit 5 and leave everything else as default, click **OK**. This create an empty 
 **DriverAggregateTest** class. Add the following code:
 
 ``` java
 class DriverAggregateTest {
 
     private static final String IDENTIFIER = "123";
 
     private static final String DRIVER_NUMBER = "dln123";
 
     private AggregateTestFixture<DriverAggregate> fixture;
 
     @BeforeEach
     void setup() {
         fixture = new AggregateTestFixture<>(DriverAggregate.class);
     }
 
     @Test
     @DisplayName("Test Driver Creation") 
     void driverCreation() {
             CreateDriverCommand command = CreateDriverCommand.builder()
                     .identifier(IDENTIFIER)
                     .driverNumber(DRIVER_NUMBER)
                     .build();
     
             DriverCreatedEvent event = DriverCreatedEvent.builder()
                     .identifier(IDENTIFIER)
                     .driverNumber(DRIVER_NUMBER)
                     .build();
     
             fixture.givenNoPriorActivity()
                     .when(command)
                     .expectEvents(event);
     }
 ```
 
 This code will obviously fail (actually it will fail to compile) at this stage, we are now required to define a few things here:
 1. The ```CreateDriverCommand``` class (with builder), and 
 2. The ```DriverCreatedEvent``` class (also with builder).
 
 Next. [Axon - First Steps (Creating our Command)](axon_first_createcommand.md)