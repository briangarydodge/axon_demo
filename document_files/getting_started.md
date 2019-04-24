# 1. Getting Started
***

## Generating your project

Before we can begin to produce our event sourced driver application, we need to create a minimal spring boot 
application, which will serve a the base for the axon components and provide endpoints for passing in data 
to application. Below are three varying ways to get started.

### a. via command line (Curl)

``` bash
curl https://start.spring.io/starter.zip -o axon_demo.zip
```
Unzip your application and import into your IDE of choice as a maven project. (For the purposes of this project I am using IntelliJ Idea).

---

### b. via SpringInitializr

Open your navigator of choice and navigate to [start.spring.io](https://start.spring.io).

Select **Maven Project**, then **Java** for the language.

Choose whichever version of Spring Boot you wish to use (This project uses 2.1.4).

For the project metadata; **Group** typically is a reverse url to your website. In this
instance I have used *com.dodgeb*. Artifact is typically your project name, I have used
**axon_demo**.

At this stage, you can either add required dependencies or add them later; however,
it is worth pre-selecting **Web** and **Lombok** (More on these later). Click **Generate Project**.

Once the file is downloaded, unzip and import into your IDE of choice. (For the purposes of this project I am usinh IntelliJ Idea.)

---

### c. via IntelliJ Idea (Ultimate)

* Select **Create new Project** from IntelliJ's front screen or select **File->New Project** (If the Editor is already open).
* Select **Spring Initializr** from the left hand pane and leave the url as the default. 
* Enter the metadata for your project as required. **Group** typically is a reverse url to your website. In this
instance I have used *com.dodgeb*. Artifact is typically your project name, I have used **axon_demo**.
* On the **Dependencies** page, select (a) **Lombok** from the **Core** section and **Web** from the **Web** section.
(More on these later).
* Finish and wait for IntelliJ to fully load your project.

---

## Finishing off

Once IntelliJ is open and your project is fully loaded, you will have an empty SpringBoot project, with
the basic building blocks to start coding your Event Sourced application.

In the next section, we will import the various dependencies which will allow us to create the Axon application
and build tests (using Test Driven Development principles).

Next. [Updating Dependencies](updating_dependencies.md)
