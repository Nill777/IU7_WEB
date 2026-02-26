## WebLab #6 (SOA)

В `web6_with_nginx_config` разворачивается исходное Spring Boot приложение, декомпозированное на 3 сервиса при помощи профилей:

- `gateway-service` (профиль `gateway`) — внешнее API, проксирует вызовы в core.
- `core-service` (профиль `core`) — бизнес-логика, дергает `data` по REST.
- `data-service` (профиль `data`) — слой данных на Postgres + JPA.
- Мониторинг: Loki+Promtail+Grafana (Promtail снимает docker-логи).

Запуск (каждый сервис в 3 экземплярах):
```bash
./web6.sh
# или вручную
./gradlew clean bootJar
cp build/libs/*.jar web6_with_nginx_config/{gateway-service,core-service,data-service}/app.jar
cd web6_with_nginx_config
docker compose up --build -d --scale gateway=3 --scale core=3 --scale data=3
```

Основное:
- Swagger UI: `http://localhost/swagger-ui/index.html` (gateway)
- API базовый путь: `/api/v2`
- Grafana: `http://localhost/monitoring`
