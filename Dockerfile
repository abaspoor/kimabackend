# مرحله اول: بیلد اپ با Maven
FROM maven:3.9.4-openjdk-17-slim AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# مرحله دوم: اجرای فایل JAR با JDK سبک‌تر
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
