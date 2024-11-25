FROM gradle:jdk21 as builder

WORKDIR /discografia

COPY ./build.gradle .
COPY ./settings.gradle .

COPY src ./src 


FROM openjdk:21-jdk-slim

WORKDIR /discografia

COPY --from=builder /discografia/build/libs/*.jar discografia-1.jar

EXPOSE 443

CMD ["java", "-jar", "discografia-1.jar"]
