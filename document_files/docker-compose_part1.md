# Docker Compose - Part 1

As our basic application requires Axon Server out of the box, we need to ensure it is running for our application to work.

The simplest way, is to run a docker container, with Axon Server running. We are going to do this by using *docker-compose*.

First, create a new file in the root of your project called **docker-compose.yaml and copy the following text into it:

``` yaml
version: '2'
services:

  axon-server:
    image: axoniq/axonserver
    ports:
      - "8024:8024"
      - "8124:8124"
```

To start Axon Server, open **Terminal** in the root of your project and type:

``` bash
docker-compose up -d
```

This will retrieve the image and start it up ready for our application.

> **Note** For docker-compose to work, you will need to ensure that docker and docker-compose are installed
> on your system. There are plenty of resources on the web to help you do this.