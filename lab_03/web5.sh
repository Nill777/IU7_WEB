#!/bin/bash

./gradlew clean bootJar

echo "Starting backend services..."

java -jar build/libs/*.jar --server.port=8080 > master.log 2>&1 &
echo "Master instance started on port 8080 PID: $!"

java -jar build/libs/*.jar --server.port=9091 > replica1.log 2>&1 &
echo "Read-replica 1 started on port 9091 PID: $!"

java -jar build/libs/*.jar --server.port=9092 > replica2.log 2>&1 &
echo "Read-replica 2 started on port 9092 PID: $!"

echo "All instances are running"