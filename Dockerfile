FROM adoptopenjdk/openjdk11:latest
COPY build/libs/spring-kotlin-*.jar spring-kotlin.jar
ENTRYPOINT ["java","-jar","/spring-kotlin.jar"]