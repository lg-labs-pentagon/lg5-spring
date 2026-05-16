# Paso 8: Despliegue

## 8.1: Docker con JIB

El parent POM `lg5-spring-parent` configura automáticamente **Google JIB** para construir imágenes Docker multi-arquitectura:

```xml
<!-- lg5-spring-config/pom.xml pluginManagement -->
<plugin>
     <groupId>com.google.cloud.tools</groupId>
     <artifactId>jib-maven-plugin</artifactId>
     <configuration>
         <from>
             <image>eclipse-temurin:21-jre</image>
             <digest>${digest-amd}</digest> 
             <!-- amd64 -->
             <!-- <digest>${digest-arm}</digest> --> 
             <!-- arm64 -->
          </from>
         <to>
             <image>${project.artifactId}:${project.version}</image>
          </to>
         <container>
             <ports>
                 <port>8080</port>
              </ports>
             <>jvmFlags>
                 <jvmFlag>-Xms512m</jvmFlag>
                 <jvmFlag>-XX:MaxRAMPercentage=50</jvmFlag>
              </jvmFlags>
             <useCurrentTimestamp>true</useCurrentTimestamp>
          </container>
      </configuration>
</plugin>
```

## 8.2: Construir Imagen Docker

```bash
# Construir y publicar Docker
mvn clean install

# Esto ejecuta:
#   spring-boot:build-image (Spring Boot native)
#   jib:build (Google JIB multi-arch)
```

## 8.3: Docker Compose Local

```yaml
# docker-compose.yml (infraestructura)
version: '3.8'
services:
     postgres:
         image: postgres:17.0
         environment:
             POSTGRES_DB: postgres
             POSTGRES_USER: sa
             POSTGRES_PASSWORD: sa
         ports:
           - "5432:5432"
         networks:
           - blank

     postgres-gui:
         image: dpage/pgadmin4
         ports:
           - "5013:80"
         environment:
             PGADMIN_DEFAULT_EMAIL: blanksystem@db.com
             PGADMIN_DEFAULT_PASSWORD: blanksystem-db
         depends_on: [postgres]
         networks: [blank]

     kafka:
         image: confluentinc/cp-kafka:7.8.1
         ports:
           - "9092:9092"
           - "9093:9093"
         environment:
             KAFKA_ADVERTISED_LISTENERS: "..."
         networks: [blank]

     kafka-gui:
         image: provectuslabs/kafka-ui:latest
         ports:
           - "9080:8080"
         depends_on: [kafka]
         networks: [blank]

networks:
     blank:
         driver: bridge
```

## 8.4: Makefile Commands

```bash
# Start Postgres + Kafka
make docker-up

# Stop Postgres + Kafka
make docker-down

# Run blank-service app
make run-app

# Build everything
make all-build

# Build Docker images
make build-docker

# Run tests
make run-unit-test
make run-integration-test
make run-acceptance-test
make run-checkstyle

# Run a specific test
make run-test-spec TEST_NAME=MyTest

# Publish to Maven Local
make publish-local
```

## 8.5: K8s Deploy

```bash
# Deploy with K8s
git clone https://github.com/lg-labs/blank-infra.git
cd blank-infra
# Follow K8s deployment instructions
```

## 8.6: Endpoints de Desarrollo

| Servicio | URL | Credentials |
|---|---|---|
| API | `http://localhost:8181` | None |
| PgAdmin | `http://localhost:5013` | `blanksystem@db.com` / `blanksystem-db` |
| Kafka UI | `http://localhost:9080` | None |

## 8.7: Logging & Observabilidad

### ELK Stack

```yaml
logging:
     level:
         com.blanksystem: INFO
         org.apache.kafka: ERROR
         io.confluent.kafka: ERROR
         feign: DEBUG
         feign.client: DEBUG
```

- Simple log: `[log.path]/blank-service-simple.log`
- Complex log: `[log.path]/blank-service-complex.log`

### Distributed Tracing

Spring Cloud Sleuth está configurado para agregar trace IDs automáticamente:

- Todos los logs incluyen `traceId` y `spanId`
- MDC está configurado con `springVersion`, `javaVersion`, `SpringBootVersion`, `lg5Spring`
