# Paso 1: Configuración Inicial

## 1. Crear el Proyecto

Sigue blank-service como plantilla:

```bash
git clone https://github.com/lg-labs/blank-service.git
cd blank-service
```

## 2. Parent POM

Cada módulo hereda de `lg5-spring-parent`:

```xml
<!-- blank-service/pom.xml (root) -->
<project>
     <modelVersion>4.0.0</modelVersion>
     <groupId>com.blanksystem</groupId>
     <artifactId>blank-service</artifactId>
     <version>1.0.0-alpha</version>
     <packaging>pom</packaging>

     <parent>
         <groupId>com.lg5.spring</groupId>
         <artifactId>lg5-spring-parent</artifactId>
         <version>1.0.0-alpha.f47a391</version>
     </parent>

     <modules>
         <module>blank-api</module>
         <module>blank-container</module>
         <module>blank-domain</module>
         <module>blank-data-access</module>
         <module>blank-external</module>
         <module>blank-message</module>
         <module>blank-acceptance-test</module>
         <module>blank-support</module>
     </modules>
</project>
```

## 3. Configuración del Aplicativo

`blank-container/src/main/resources/application.yaml`:

```yaml
info:
     app:
         name: ${spring.application.name}
         version: "@project.version@"

spring:
     main:
         banner-mode: "off"
     application:
         name: blank-service
     jpa:
         open-in-view: false
         show-sql: true
     datasource:
         url: jdbc:postgresql://localhost:54322/postgres?currentSchema=blank
         username: sa
         password: sa
     liquibase:
         change-log: classpath:db/changelog/db.changelog-master.yaml

kafka-config:
     bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
     schema-registry-url-key: schema.registry.url
     schema-registry-url: http://localhost:8081
     num-of-partitions: 3
     replication-factor: 3

kafka-producer-config:
     key-serializer-class: org.apache.kafka.common.serialization.StringSerializer
     value-serializer-class: io.confluent.kafka.serializers.KafkaAvroSerializer
     compression-type: snappy
     acks: all

kafka-consumer-config:
     key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
     value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
     auto-offset-reset: earliest
     specific-avro-reader: true
     batch-listener: true
     concurrency-level: 3

logging:
     level:
         com.blanksystem: INFO
         org.apache.kafka: ERROR
         io.confluent.kafka: ERROR
         feign: DEBUG
         feign.client: DEBUG

blanksystem:
     blank:
         service:
             blank-topic-name: blank.1.0.event.created
         events:
             journal:
                 blank:
                     topic: blank.1.0.event.created
                     consumer:
                         group: blank-topic-consumer
```

## 4. Punto de Entrada: Application.java

```java
// blank-container/src/main/java/.../container/Application.java
package com.blanksystem.blank.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
     "com.blanksystem.blank.service.data"
})
@EntityScan(basePackages = {
     "com.blanksystem.blank.service.external",
     "com.blanksystem.blank.service.data"
})
@EnableFeignClients(basePackages = "com.blanksystem.blank.service.external")
@SpringBootApplication(scanBasePackages = {
     "com.blanksystem",
     "com.lg5.spring.kafka",
     "com.lg5.spring.mdc"
})
@EnableAspectJAutoProxy
public class Application {

     public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
     }
}
```
