#!/bin/bash

echo "Starting deployment script..."

echo "Building the main Spring Boot application..."
./gradlew clean bootJar

JAR_FILE=$(find build/libs -name "*.jar" | head -n 1)
DEPLOY_DIR="web6_with_nginx_config"
SERVICES=("gateway-service" "core-service" "data-service")

for service in "${SERVICES[@]}"; do
    SERVICE_PATH="$DEPLOY_DIR/$service"

    # Копируем JAR-файл в каждую директорию сервиса
    cp $JAR_FILE "$SERVICE_PATH/app.jar"

    # Создаем Dockerfile внутри каждой директории сервиса
    cat <<EOF > "$SERVICE_PATH/Dockerfile"
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
EOF
done
echo "Service and Dockerfiles are ready"

echo "Starting all services via Docker Compose..."
cd $DEPLOY_DIR

docker-compose up --build -d

echo ""
echo "--- COMPLETE ---"
echo "API Gateway is available at: http://localhost/swagger-ui/index.html"
echo "Grafana is available at: http://localhost/monitoring/"
echo "To stop all services, run docker-compose down from /web6_with_nginx_config"