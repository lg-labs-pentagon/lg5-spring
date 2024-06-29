# Lg5 JVM Library
**_Hex Arch, DDD,SAGA, Outbox&Kafka_**
[Repository][4]

## Use JDK 21

> **No use frameworks**, but it is important to create software using the best practices.      

## 🚀 Using Lg5 JVM

> Install 1/2: Add this to pom.xml:

```xml title="pom.xml" linenums="1" hl_lines="4"
<dependency>
  <groupId>com.lg5.jvm</groupId>
  <artifactId>lg5-jvm-utils</artifactId>
  <version>1.0.0-alpha.[check lts version]</version>
</dependency> 
```
_Note: Please check the [latest version][5]_

Install 2/2: Install the dependencies in your project.

```bash title="terminal" linenums="1" hl_lines="1"
mvn install
```

## 📚Contents

* [lg5-common](lg5-common)
    * [lg5-common-application-service](lg5-common%2Flg5-common-application-service)
    * [lg5-common-domain](lg5-common%2Flg5-common-domain)
* [lg5-jvm-saga](lg5-jvm-saga)
* [lg5-jvm-test](lg5-jvm-test)
* [lg5-jvm-utils](lg5-jvm-utils)
  * MapStruct
  * Guava
  * Lombok
  * Slf4j
  * Commons Lang3 _(To_remove)_

[4]: https://github.com/lg-labs-pentagon/lg5-spring
[5]: https://github.com/lg-labs-pentagon/lg5-spring/packages/2128425
