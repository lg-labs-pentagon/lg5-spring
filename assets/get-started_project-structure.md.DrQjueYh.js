import{c as a,Q as n,j as p,m as i}from"./chunks/framework.BPKcPtvA.js";const u=JSON.parse('{"title":"Estructura de Proyecto","description":"","frontmatter":{},"headers":[],"relativePath":"get-started/project-structure.md","filePath":"get-started/project-structure.md","lastUpdated":1778943603000}'),e={name:"get-started/project-structure.md"};function t(l,s,r,o,c,d){return n(),p("div",null,[...s[0]||(s[0]=[i(`<h1 id="estructura-de-proyecto" tabindex="-1">Estructura de Proyecto <a class="header-anchor" href="#estructura-de-proyecto" aria-label="Permalink to &quot;Estructura de Proyecto&quot;">​</a></h1><h2 id="vision-general" tabindex="-1">Visión General <a class="header-anchor" href="#vision-general" aria-label="Permalink to &quot;Visión General&quot;">​</a></h2><p>lg5-spring sigue una <strong>arquitectura hexagonal (Ports-and-Adapters)</strong> con <strong>DDD</strong> y <strong>CQRS</strong>. La estructura de directorios es cuidadosamente diseñada para separar responsabilidades y mantener la independencia de cada capa.</p><hr><h2 id="estructura-de-modulos" tabindex="-1">Estructura de Módulos <a class="header-anchor" href="#estructura-de-modulos" aria-label="Permalink to &quot;Estructura de Módulos&quot;">​</a></h2><p>Cada microservicio basado en lg5-spring tiene la siguiente estructura:</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>your-service/                             # Root del microservicio</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-api/                             # API REST (controllers)</span></span>
<span class="line"><span>│     └── com/yourcompany/your/api/rest/</span></span>
<span class="line"><span>│         └── YourController.java</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-container/                       # Punto de entrada Spring Boot</span></span>
<span class="line"><span>│     └── com/yourcompany/your/container/</span></span>
<span class="line"><span>│         └── Application.java</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-domain/</span></span>
<span class="line"><span>│     ├── your-domain-core/               # Pure domain</span></span>
<span class="line"><span>│     │    └── com/yourcompany/your/service/domain/</span></span>
<span class="line"><span>│     │         ├── entity/YourEntity.java</span></span>
<span class="line"><span>│     │         ├── valueobject/YourId.java</span></span>
<span class="line"><span>│     │         ├── event/YourEvent.java</span></span>
<span class="line"><span>│     │         ├── exception/YourException.java</span></span>
<span class="line"><span>│     │         └── yourservice/</span></span>
<span class="line"><span>│     │</span></span>
<span class="line"><span>│     └── your-application-service/       # Application layer</span></span>
<span class="line"><span>│         └── com/yourcompany/your/service/domain/</span></span>
<span class="line"><span>│             ├── dto/</span></span>
<span class="line"><span>│             ├── ports/input/</span></span>
<span class="line"><span>│             ├── ports/output/</span></span>
<span class="line"><span>│             └── mapper/</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-data-access/                    # Infrastructure (JPA)</span></span>
<span class="line"><span>│     └── com/yourcompany/your/service/data/</span></span>
<span class="line"><span>│         ├── adapter/YourRepositoryImpl.java</span></span>
<span class="line"><span>│         ├── entity/YourEntity.java</span></span>
<span class="line"><span>│         ├── mapper/YourDataAccessMapper.java</span></span>
<span class="line"><span>│         └── repository/YourJPARepository.java</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-message/</span></span>
<span class="line"><span>│     ├── your-message-core/               # Kafka events</span></span>
<span class="line"><span>│     │    └── com/yourcompany/your/service/message/</span></span>
<span class="line"><span>│     │         ├── listener/YourKafkaListener.java</span></span>
<span class="line"><span>│     │         └── publisher/YourKafkaPublisher.java</span></span>
<span class="line"><span>│     └── your-message-model/              # Avro models</span></span>
<span class="line"><span>│         └── src/main/resources/avro/</span></span>
<span class="line"><span>│             └── your.avsc</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── your-acceptance-test/                # Cucumber BDD tests</span></span>
<span class="line"><span>└── your-support/                        # Aggregation, Jacoco reports</span></span></code></pre></div><hr><h2 id="estructura-del-monorepo-lg5-spring" tabindex="-1">Estructura del Monorepo lg5-spring <a class="header-anchor" href="#estructura-del-monorepo-lg5-spring" aria-label="Permalink to &quot;Estructura del Monorepo lg5-spring&quot;">​</a></h2><p>El propio framework tiene su propia estructura monorepo:</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>lg5-spring/</span></span>
<span class="line"><span>│</span></span>
<span class="line"><span>├── lg5-spring-parent/           # BOM - Gestiona todas las versiones</span></span>
<span class="line"><span>├── lg5-spring-starter/          # spring-boot-starter mínimo</span></span>
<span class="line"><span>├── lg5-spring-api-rest/         # Spring Web + Validation</span></span>
<span class="line"><span>├── lg5-spring-data-jpa/         # JPA + PostgreSQL + Liquibase</span></span>
<span class="line"><span>├── lg5-spring-client/           # OpenFeign</span></span>
<span class="line"><span>├── lg5-spring-kafka/            # Kafka sub-system</span></span>
<span class="line"><span>│    ├── lg5-spring-kafka-config/</span></span>
<span class="line"><span>│    ├── lg5-spring-kafka-model/</span></span>
<span class="line"><span>│    ├── lg5-spring-kafka-producer/</span></span>
<span class="line"><span>│    └── lg5-spring-kafka-consumer/</span></span>
<span class="line"><span>├── lg5-spring-outbox/           # Outbox Pattern</span></span>
<span class="line"><span>├── lg5-jvm-saga/                # Saga Pattern</span></span>
<span class="line"><span>├── lg5-common/                  # DDD shared</span></span>
<span class="line"><span>│    └── lg5-common-domain/</span></span>
<span class="line"><span>│    └── lg5-common-application-service/</span></span>
<span class="line"><span>├── lg5-spring-logger/           # ELK Logging</span></span>
<span class="line"><span>├── lg5-jvm-utils/               # Lombok, Guava, MapStruct</span></span>
<span class="line"><span>├── lg5-spring-testcontainers/   # Test Containers</span></span>
<span class="line"><span>├── lg5-spring-integration-test/ # Integration Test base</span></span>
<span class="line"><span>├── lg5-spring-acceptance-test/  # Cucumber BDD</span></span>
<span class="line"><span>└── lg5-jvm-unit-test/           # JUnit 5 + Mockito</span></span></code></pre></div><hr><h2 id="archivos-clave-por-modulo" tabindex="-1">Archivos Clave por Módulo <a class="header-anchor" href="#archivos-clave-por-modulo" aria-label="Permalink to &quot;Archivos Clave por Módulo&quot;">​</a></h2><p>Cada módulo en el monorepo contiene:</p><table tabindex="0"><thead><tr><th>Archivo</th><th>Propósito</th></tr></thead><tbody><tr><td>build.gradle.kts</td><td>Declaración de dependencias usando version catalog</td></tr><tr><td>src/main/java/</td><td>Código fuente Java/Kotlin</td></tr><tr><td>src/test/java/</td><td>Tests unitarios</td></tr></tbody></table><h3 id="el-archivo-de-versiones" tabindex="-1">El Archivo de Versiones <a class="header-anchor" href="#el-archivo-de-versiones" aria-label="Permalink to &quot;El Archivo de Versiones&quot;">​</a></h3><p>El archivo central de versionado es <code>gradle/libs.versions.toml</code>:</p><div class="language-toml vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang">toml</span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">[</span><span style="--shiki-light:#6F42C1;--shiki-dark:#B392F0;">versions</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">]</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">springboot-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;3.5.14&quot;</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">springframework-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;6.2.2&quot;</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">postgres-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;42.7.5&quot;</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">liquibase-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;4.31.0&quot;</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">avro-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;1.12.0&quot;</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">spring-kafka-version = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;3.3.2&quot;</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">[</span><span style="--shiki-light:#6F42C1;--shiki-dark:#B392F0;">libraries</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">]</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">springboot-starter = { group = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;org.springframework.boot&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">, name = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;spring-boot-starter&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;"> }</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">springboot-start-web = { group = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;org.springframework.boot&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">, name = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;spring-boot-starter-web&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;"> }</span></span>
<span class="line"><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">postgresql = { group = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;org.postgresql&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;">, name = </span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;">&quot;postgresql&quot;</span><span style="--shiki-light:#24292E;--shiki-dark:#E1E4E8;"> }</span></span></code></pre></div><hr><h2 id="flujo-de-construccion" tabindex="-1">Flujo de Construcción <a class="header-anchor" href="#flujo-de-construccion" aria-label="Permalink to &quot;Flujo de Construcción&quot;">​</a></h2><div class="language-bash vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang">bash</span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span style="--shiki-light:#6A737D;--shiki-dark:#6A737D;"># Construir todo el monorepo</span></span>
<span class="line"><span style="--shiki-light:#6F42C1;--shiki-dark:#B392F0;">make</span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;"> all-build</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#6A737D;--shiki-dark:#6A737D;"># Publicar en Maven Local</span></span>
<span class="line"><span style="--shiki-light:#6F42C1;--shiki-dark:#B392F0;">make</span><span style="--shiki-light:#032F62;--shiki-dark:#9ECBFF;"> publish-local</span></span></code></pre></div>`,21)])])}const g=a(e,[["render",t]]);export{u as __pageData,g as default};
