FROM openjdk:11
VOLUME /tmp
COPY /application/target/lib /lib
ADD /application/target/app.1.0-SNAPSHOT.jar app.jar
#ENV JAVA_OPTS=""
ENTRYPOINT java -Dspring.profiles.active=test -jar /app.jar