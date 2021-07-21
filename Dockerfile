FROM openjdk:11
ARG JAR_FILE=target/upss-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} upss-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/upss-0.0.1-SNAPSHOT.jar"]