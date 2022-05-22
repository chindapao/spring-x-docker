FROM openjdk:11
EXPOSE 9090
ARG JAR_FILE=./target/demo-0.0.1.jar
ADD ${JAR_FILE} demoapp.jar
ENTRYPOINT ["java","-jar","/demoapp.jar"]