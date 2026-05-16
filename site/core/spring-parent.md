# lg5-spring-parent

`lg5-spring-parent` es el **BOM (Bill of Materials)** central del framework. Es el módulo más importante porque define las versiones de todas las demás librerías y configura los plugins de Maven para todos los servicios.

## Propósito

Genera un **POM Parent** que los microservicios heredan para obtener:

- Versiones de dependencias centralizadas
- Plugin management (compiler, surefire, failsafe, jacoco, checkstyle)
- Perfiles de Docker (amd64/arm64)
- Repositorios adicionales (Confluent Maven)

---

## Configuración Técnica

### Build Configuration

**lg5-spring-parent/build.gradle.kts:**

```kotlin
subprojects {
    publishing {
        publications {
            create<MavenPublication>("parentJava") {
                from(components["java"])
                pom.packaging = "pom"
            }
        }
    }
}
```

---

### Plugins Configurados

| Plugin | Versión | Propósito |
|--------|---------|-----------|
| maven-compiler-plugin | 3.13.0 | Java 21, MapStruct + Lombok |
| maven-surefire-plugin | 3.5.2 | Unit tests |
| maven-failsafe-plugin | 3.5.2 | IT tests + acceptance tests |
| jacoco-maven-plugin | 0.8.12 | Code coverage |
| maven-checkstyle-plugin | 3.6.0 | Google Java Style |
| avro-maven-plugin | 1.12.0 | Avro codegen |
| jib-maven-plugin | 3.5.1 | Multi-arch Docker |
| spring-boot-maven-plugin | 3.5.14 | Build image |
| depgraph-maven-plugin | 4.0.3 | Dependency graph |

---

### Perfiles de Docker

```xml
<!-- amd (default) -->
<profile>
    <id>amd</id>
    <properties>
        <os.arch>amd64</os.arch>
        <os.family>linux</os.family>
        <digest-amd>sha256:...</digest-amd>
    </properties>
</profile>

<!-- arm64 -->
<profile>
    <id>arch-aarch64</id>
    <properties>
        <os.arch>aarch64</os.arch>
        <os.family>linux</os.family>
        <digest-arm>sha256:...</digest-arm>
    </properties>
</profile>
```

---

### Repositorios Configurados

```xml
<repositories>
    <repository>
        <id>confluent</id>
        <url>https://packages.confluent.io/maven</url>
    </repository>
</repositories>
```

---

## Uso en un Microservicio

```xml
<parent>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-parent</artifactId>
    <version>1.0.0-alpha.6ff1b95</version>
</parent>
```

### Qué Heredas del Parent POM

1. **Spring Boot Parent** como parent POM
2. **Plugins preconfigurados**
3. **Perfiles Docker** (amd64, arm64)
4. **Repositorio Confluent**
5. **Properties configurados**
