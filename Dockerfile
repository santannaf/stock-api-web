FROM maven:3.6.3-jdk-8 AS builder
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests -U
#RUN mvn -f /home/app/pom.xml -DskipTests clean dependency:list install

FROM openjdk:8-jre-alpine
WORKDIR /home/app
ENV PORT 8080
EXPOSE 8080
COPY --from=builder /home/app/challenge-app/target/*exec.jar /home/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]