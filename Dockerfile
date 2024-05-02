FROM openjdk:11

ENV TZ=Asia/Taipei
ENV JAVA_OPTS="-server -XX:+UseG1GC -verbose:gc -Xlog:gc:stdout -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=90 -XX:MinRAMPercentage=50"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar