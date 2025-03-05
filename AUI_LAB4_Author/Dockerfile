# Wybieramy obraz bazowy z OpenJDK
FROM openjdk:17-jdk-slim

# Kopiujemy plik jar aplikacji Spring Boot (po zbudowaniu)
COPY out/artifacts/AUI_jar/AUI.jar /app/your-springboot-app.jar

# Ustawiamy katalog roboczy
WORKDIR /app

# Wystawiamy port 8080
EXPOSE 8082

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "your-springboot-app.jar"]
