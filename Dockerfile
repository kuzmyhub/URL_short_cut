# Этап 1 - сборка проекта в jar
FROM maven as maven
WORKDIR /app
COPY . /app
RUN mvn install

# Этап 2 - указание как запустить проект
FROM openjdk
WORKDIR /app
COPY --from=maven /app/target/URL_short_cut-1.0-SNAPSHOT.jar app.jar
CMD java -jar app.jar
