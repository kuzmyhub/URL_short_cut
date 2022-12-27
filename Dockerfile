FROM openjdk
WORKDIR shortcut
ADD target/job4j_URL_short_cut-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar
