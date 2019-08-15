FROM openjdk:11.0.4-jre-slim

LABEL maintainer="p2p.olxbr.com"

VOLUME /tmp

EXPOSE 8081

RUN apt-get update && apt-get install -y curl

ADD target/*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${ENVIRONMENT} -jar /app.jar" ]
