# 2. Updating dependencies

In this section we will be updating the *pom.xml* file to update which depencencies to import, 
which will allow; 1. Junit 5 testing, 2. Axon Framework (and testing libraries). Later we will be importing libraries, but for 
now we want to get a simple application up and running.

Open the pom.xml, which will be in the root folder of your project, the file should look similar to the following:

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.4.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>axon-demo</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

In the *<dependencies>* section, you should have three imports; **web** used for the REST endpoints, **Lombok**
for the reduction in boiler plate code, and **spring-boot-starter-test** used in conjunction of unit testing spring components.

First we need to add libraries, ready for JUnit 5 testing, by adding code to the pom file between the *<dependencies></dependencies>* tags:

``` xml
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.3.2</version>
            <scope>test</scope>
        </dependency>
```

Next, we will import Axon Framework and test components:

``` xml
        <!-- https://mvnrepository.com/artifact/org.axonframework/axon-spring-boot-starter -->
        <dependency>
            <groupId>org.axonframework</groupId>
            <artifactId>axon-spring-boot-starter</artifactId>
            <version>4.1.1</version>
        </dependency>
        
        <!-- https://mvnrepository.com/artifact/org.axonframework/axon-test -->
        <dependency>
            <groupId>org.axonframework</groupId>
            <artifactId>axon-test</artifactId>
            <version>4.1.1</version>
            <scope>test</scope>
        </dependency>
```
> **Note** Version 4+ of Axon framework, uses Axon Server - so you will need a Axon Running on your host machine.
> The easiest way is by using docker and running Axon Server as a container. This will be described in the next section.

Next. [Docker Compose - Part 1](docker-compose_part1.md)