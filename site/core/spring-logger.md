# lg5-spring-logger

Configura el stack de logging completo con soporte para **ELK Stack** (Elasticsearch, Logstash, Kibana).

---

## Propósito

- Logging estructurado en JSON para Logstash/Elasticsearch
- MDC (Mapped Diagnostic Context) con version info
- Distributed tracing con Spring Cloud Sleuth
- Conditional logging con Janino
- Compresión con Snappy

---

## Configuración

```yaml
info:
    app:
        name: ${spring.application.name}
        version: "@project.version@"

logging:
    level:
        com.blanksystem: INFO
        org.apache.kafka: ERROR
        io.confluent.kafka: ERROR
        feign: DEBUG
        feign.client: DEBUG
```

---

## Appenders Configurados

### JSON Logstash (Complex)

- Log en formato JSON
- TraceId, SpanId, MDC auto-populado
- Nivel INFO+

### JSON Logstash Simple

- Campos esenciales (timestamp, level, message)
- Nivel DEBUG+

### Console

- Output colorizado en la consola

---

## Propiedades Configurables

| Propiedad | Propósito |
|-----------|-----------|
| logging.level.com.* | Nivel de log por package |
| log.path | Directorio donde guardar logs |
| log.name | Nombre del archivo de log |

---

## Uso en tu Servicio

```xml
<dependency>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-logger</artifactId>
</dependency>
```
