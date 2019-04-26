FROM openjdk:8u201-jdk-alpine3.9

ADD target/application.jar /app/application.jar

EXPOSE 8080:8080

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:MaxRAMFraction=2", "-Djava.io.tmpdir=/app/java_tmp"]

CMD ["-jar", "application.jar"]