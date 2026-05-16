# Instalación

## Prerrequisitos

- **JDK 21** — Recomendado: [Amazon Corretto 21](https://docs.aws.amazon.com/corretto/latest/corretto-21-ge/whatsnew.html)
- **Maven 3.8+** — Para construir proyectos
- **Gradle 8.x** — Para construir el framework (lg5-spring monorepo)
- **Docker + Docker Compose** — Para infraestructura local (Postgres, Kafka)

## Instalar JDK 21

```bash
# Usando SDKMAN
sdk install java 21.0.2-amzn
sdk use java 21.0.2-amzn

# O usando Homebrew
brew install openjdk@21
```

## Clonar el Framework

```bash
git clone https://github.com/lg-labs-pentagon/lg5-spring.git
cd lg5-spring
```

## Construir el Framework

```bash
# Build todas las librerías
make all-build

# Publicar en Maven local (para desarrollo)
make publish-local
```

Esto ejecuta `./gradlew build` y `publishLibraryPublicationToMavenLocal`, publicando todos los módulos en `~/.m2/repository`.

## Crear un Nuevo Microservicio

### 1. Dependencia del Parent POM

En tu `pom.xml` root:

```xml
<parent>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-parent</artifactId>
    <version>1.0.0-alpha.6ff1b95</version>
</parent>
```

### 2. Declarar Módulos

Agrega solo los módulos que necesitas:

```xml
<dependencies>
    <!-- Opcional: Starter mínimo -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-starter</artifactId>
    </dependency>

    <!-- REST APIs -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-api-rest</artifactId>
    </dependency>

    <!-- Base de datos -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-data-jpa</artifactId>
    </dependency>

    <!-- Comunicación entre servicios -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-client</artifactId>
    </dependency>

    <!-- Mensajería Kafka -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-kafka-producer</artifactId>
    </dependency>
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-kafka-consumer</artifactId>
    </dependency>

    <!-- Outbox Pattern -->
    <dependency>
        <groupId>com.lg5.spring.outbox</groupId>
        <artifactId>lg5-spring-outbox</artifactId>
    </dependency>

    <!-- SAGA Pattern -->
    <dependency>
        <groupId>com.lg5.jvm</groupId>
        <artifactId>lg5-jvm-saga</artifactId>
    </dependency>

    <!-- Logging ELK -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-logger</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.lg5.spring</groupId>
        <artifactId>lg5-spring-acceptance-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 3. Estructura de Directorios Sugerida

Sigue el patrón del ejemplo [blank-service](https://github.com/lg-labs/blank-service):

```
your-service/
├── your-api/               # Controllers, REST endpoints
├── your-container/         # Spring Boot application, configs
├── your-domain/
│   ├── your-domain-core/   # Entities, ValueObjects, DomainExceptions
│   └── your-application-service/  # Use cases, ApplicationService, Sagas
├── your-data-access/       # JPA Entities, Repository impls, Migrations
├── your-external/          # Feign clients, Third-party adapters
├── your-message/
│   ├── your-message-core/      # Kafka listeners, publishers
│   └── your-message-model/     # Avro models
├── your-acceptance-test/     # Cucumber BDD tests
└── your-support/             # Aggregation module, reports
```

## Verificar la Instalación

```bash
# Construir el proyecto
mvn clean install

# Ejecutar tests
mvn test

# Publicar en Maven local
mvn clean install -DskipTests
```

## Publicar el Framework en Maven Local

Si estás desarrollando el framework mismo:

```bash
# Construir todo
make all-build

# Publicar todo local
make publish-local
```

Esto ejecuta las tareas de Gradle configuradas en el `Makefile` del monorepo.

```makefile
all-build:
    ./gradlew build --warning-mode all

publish-local:
    ./gradlew publishLibraryPublicationToMavenLocal -Pversion=1.0.0-alpha.95 --warning-mode all
```
