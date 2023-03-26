FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
RUN ls
COPY ./target/music-0.0.1-SNAPSHOT.jar /home/app.jar 
ENTRYPOINT ["java","-jar","/home/app.jar"]