#!/bin/bash

echo "Starting deployment script..."

export JAVA_HOME="/usr/lib/jvm/java-17-openjdk"
export PATH="$JAVA_HOME/bin:$PATH"

echo "Building Spring Boot fat-jar..."
./gradlew clean bootJar || exit 1

JAR_FILE=$(find build/libs -name "*.jar" | head -n 1)
DEPLOY_DIR="web6_with_nginx_config"
SERVICES=("gateway-service" "core-service" "data-service")

for service in "${SERVICES[@]}"; do
  cp "$JAR_FILE" "$DEPLOY_DIR/$service/app.jar"
done

cd "$DEPLOY_DIR" || exit 1

echo "Running docker compose with scaling for 3 instances per service..."
docker compose up --build -d \
  --scale gateway=3 \
  --scale core=3 \
  --scale data=3

echo "--- COMPLETE ---"
echo "API Gateway is available at: http://localhost/swagger-ui/index.html"
echo "Grafana is available at: http://localhost/monitoring/"
echo "To stop all services, run docker compose down from /web6_with_nginx_config"
