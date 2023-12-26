FROM eclipse-temurin:17-jre
ADD build/libs/app.jar /app.jar
ADD build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
ENTRYPOINT java -javaagent:opentelemetry-javaagent.jar \
                -jar /app.jar