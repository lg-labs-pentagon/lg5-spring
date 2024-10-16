# Lg5 Spring Library

**_Hex Arch, DDD,SAGA, Outbox&Kafka_**

[Repository][4]

## Use Spring Boot `3.3.X`, JDK 21

> Tools for developer software just faster.

## 🚀 Using Lg5 Spring

> Install 1/2: Add this to pom.xml:

```xml title="pom.xml" linenums="1" hl_lines="4"

<parent>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-parent</artifactId>
    <version>1.0.0-alpha.[check lts version]</version>
</parent>   
```

_Note: Please check the [latest version][5]_

Install 2/2: Install the dependencies in your project.

```bash title="terminal" linenums="1" hl_lines="1"
mvn install
```

## 📚Contents

* [lg5-spring-parent](lg5-spring-parent)
    * Integration Test (_Suffix IT.java_)
        * `mvn verify`
    * Unit Test(_Suffix Test.java_)
        * `mvn test`
* [lg5-spring-api-rest](lg5-spring-api-rest)
* [lg5-spring-client](lg5-spring-client)
* [lg5-spring-data-jpa](lg5-spring-data-jpa)
* [lg5-spring-kafka](lg5-spring-kafka)
    * [lg5-spring-kafka-config](lg5-spring-kafka%2Flg5-spring-kafka-config)
    * [lg5-spring-kafka-consumer](lg5-spring-kafka%2Flg5-spring-kafka-consumer)
    * [lg5-spring-kafka-model](lg5-spring-kafka%2Flg5-spring-kafka-model)
    * [lg5-spring-kafka-producer](lg5-spring-kafka%2Flg5-spring-kafka-producer)
* [lg5-spring-logger](lg5-spring-logger)
* [lg5-spring-outbox](lg5-spring-outbox)
* [lg5-spring-starter](lg5-spring-starter)
* [lg5-spring-test](lg5-spring-test)
* [lg5-spring-acceptance-test](lg5-spring-acceptance-test)
  * Cucumber
  * Reporting Plugin
  * Depend On (lg5-spring-testcontainers) dependency.
* [lg5-spring-testcontainers](lg5-spring-testcontainers)
    * Postgres Container
    * Kafka Container
        * `${kafka-config.bootstrap-servers}`
    * SchemaRegistry Container
        * `${kafka-config.schema-registry-url}`
    * Wiremock Container
        * Specify third system url `${wiremock.config.url}`.
        * Indicate a port binding to connect: `${wiremock.config.port}`.
        * Directory where stored the mock req/res http `${wiremock.config.folder}`.
* [lg5-spring-utils](lg5-spring-utils)
* [lg5-spring-integration-test](lg5-spring-integration-test)

[4]: https://github.com/lg-labs-pentagon/lg5-spring

[5]: https://github.com/lg-labs-pentagon/lg5-spring/packages/2125499