# Getting Started
***

### Generating your project

Before we can begin to produce our event sourced driver application, we need to create a minimal spring boot 
application, which will serve a the base for the axon components and provide endpoints for passing in data 
to application.

#### via command line (Curl)

``` bash
curl https://start.spring.io/starter.zip -o axon_demo.zip
```

#### via SpringInitializr

Open your navigator of choice and navigate to [start.spring.io](https://start.spring.io).

Select **Maven Project**, then **Java** for the language.

Choose whichever version of Spring Boot you wish to use (This project uses 2.1.4).

For the project metadata; **Group** typically is a reverse url to your website. In this
instance I have used *com.dodgeb*. Artifact is typically your project name, I have used
**axon_demo**.

At this stage, you can either add required dependencies or add them later; however,
it is worth pre-selecting **Web** and **Lombok** (More on these later).