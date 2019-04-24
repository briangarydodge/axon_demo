# Getting Started
***

## 1. Generating your project

Before we can begin to produce our event sourced driver application, we need to create a minimal spring boot 
application, which will serve a the base for the axon components and provide endpoints for passing in data 
to application.

### 1.a via command line (Curl)

``` bash
curl https://start.spring.io/starter.zip -o axon_demo.zip
```
Unzip your application and import into your IDE of choice as a maven project. (For the purposes of this project I am using IntelliJ Idea).

### 1.b via SpringInitializr

Open your navigator of choice and navigate to [start.spring.io](https://start.spring.io).

Select **Maven Project**, then **Java** for the language.

Choose whichever version of Spring Boot you wish to use (This project uses 2.1.4).

For the project metadata; **Group** typically is a reverse url to your website. In this
instance I have used *com.dodgeb*. Artifact is typically your project name, I have used
**axon_demo**.

At this stage, you can either add required dependencies or add them later; however,
it is worth pre-selecting **Web** and **Lombok** (More on these later). Click **Generate Project**.

Once the file is downloaded, unzip and import into your IDE of choice. (For the purposes of this project I am usinh IntelliJ Idea.)


### 1.c via IntelliJ Idea (Ultimate)

* Select **Create new Project** from IntelliJ's front screen or select **File->New Project** (If the Editor is already open).
* Select **Spring Initializr** from the left hand pane and leave the url as the default. 
* Enter the metadata for your project as required. **Group** typically is a reverse url to your website. In this
instance I have used *com.dodgeb*. Artifact is typically your project name, I have used **axon_demo**.
* On the **Dependencies** page, select (a) **Lombok** from the **Core** section and **Web** from the **Web** section.
(More on these later).
* Finish and wait for IntelliJ to fully load your project.