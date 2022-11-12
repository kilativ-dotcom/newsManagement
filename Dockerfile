FROM maven:3.8-amazoncorretto-8 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package -DskipTests

FROM amazoncorretto:8-alpine-jre

WORKDIR /my-project
COPY ./src ./src

COPY --from=maven target/news-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "./news-0.0.1-SNAPSHOT.jar"]