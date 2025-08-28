FROM amazoncorretto:21-alpine AS build
RUN apk update && apk upgrade
WORKDIR /app
COPY gradlew gradlew.bat ./
COPY gradle/ gradle/
COPY build.gradle.kts settings.gradle.kts ./
RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon
COPY src ./src
FROM build AS development
CMD ["./gradlew", "bootRun", "--no-daemon"]
EXPOSE 8080