import { defineConfig, defaultTheme } from 'vitepress'

export default defineConfig({
  title: 'lg5-spring',
  description: 'Spring Boot 3.5 + JDK 21 — Microservices Framework',
  themeConfig: {
    nav: nav(),
    sidebar: sidebar(),
    social: {
      github: 'https://github.com/lg-labs-pentagon/lg5-spring',
    },
    footer: {
      message: 'Released under the MIT License.',
      copyright: 'Copyright © 2025-2026 Lg Pentagon (LgLabs)',
    },
    search: {
      provider: 'local',
    },
  },
  lastUpdated: true,
  SSG: true,
})

function nav() {
  return [
      { text: 'Guía', link: '/get-started/introduction.md' },
      { text: 'Core', link: '/core/libraries-overview.md' },
      { text: 'Arquitectura', link: '/architecture/hexagonal-architecture.md' },
      { text: 'Ejemplo Práctico', link: '/example/blank-service-overview.md' },
      { text: 'Testing', link: '/testing/overview.md' },
      { text: 'API Reference', link: '/api/intro.md' },
    ]
}

function sidebar() {
  return {
      '/get-started/': getSidebar('get-started'),
      '/core/': getSidebar('core'),
      '/architecture/': getSidebar('architecture'),
      '/example/': getSidebar('example'),
      '/testing/': getSidebar('testing'),
      '/api/': getSidebar('api'),
    }
}

function getSidebar(base) {
  const map = {
      'get-started': [
        {
          text: 'Guía de Inicio',
          items: [
              { text: 'Introducción', link: '/get-started/introduction.md' },
              { text: 'Instalación', link: '/get-started/installation.md' },
              { text: 'Estructura de Proyecto', link: '/get-started/project-structure.md' },
            ]
        },
      ],
      'core': [
        {
          text: 'Librerías Core',
          items: [
              { text: 'Visión General', link: '/core/libraries-overview.md' },
              { text: 'lg5-spring-parent', link: '/core/spring-parent.md' },
              { text: 'lg5-spring-starter', link: '/core/spring-starter.md' },
              { text: 'lg5-spring-api-rest', link: '/core/spring-api-rest.md' },
              { text: 'lg5-spring-data-jpa', link: '/core/spring-data-jpa.md' },
              { text: 'lg5-spring-client', link: '/core/spring-client.md' },
              { text: 'lg5-spring-kafka', link: '/core/spring-kafka.md' },
              { text: 'lg5-spring-outbox', link: '/core/spring-outbox.md' },
              { text: 'lg5-jvm-saga', link: '/core/spring-saga.md' },
              { text: 'lg5-spring-logger', link: '/core/spring-logger.md' },
              { text: 'lg5-common', link: '/core/spring-common.md' },
            ]
        },
      ],
      'architecture': [
        {
          text: 'Arquitectura',
          items: [
              { text: 'Arquitectura Hexagonal', link: '/architecture/hexagonal-architecture.md' },
              { text: 'Módulos y Distribución', link: '/architecture/module-distribution.md' },
              { text: 'Patrones DDD', link: '/architecture/ddd-patterns.md' },
              { text: 'Event-Driven Architecture', link: '/architecture/eventdriven-architecture.md' },
            ]
        },
      ],
      'example': [
        {
          text: 'blank-service (Ejemplo Práctico)',
          items: [
              { text: 'Visión General', link: '/example/blank-service-overview.md' },
              { text: 'Paso 1 — Configuración', link: '/example/step-1-configuration.md' },
              { text: 'Paso 2 — Domain Models', link: '/example/step-2-domain-models.md' },
              { text: 'Paso 3 — Application Service', link: '/example/step-3-application-service.md' },
              { text: 'Paso 4 — REST API', link: '/example/step-4-rest-api.md' },
              { text: 'Paso 5 — Data Access', link: '/example/step-5-data-access.md' },
              { text: 'Paso 6 — Kafka Messaging', link: '/example/step-6-kafka-messaging.md' },
              { text: 'Paso 7 — Testing', link: '/example/step-7-testing.md' },
              { text: 'Paso 8 — Despliegue', link: '/example/step-8-deployment.md' },
            ]
        },
      ],
      'testing': [
        {
          text: 'Testing',
          items: [
              { text: 'Visión General', link: '/testing/overview.md' },
              { text: 'Unit Tests', link: '/testing/unit-tests.md' },
              { text: 'Integration Tests', link: '/testing/integration-tests.md' },
              { text: 'Acceptance Tests', link: '/testing/acceptance-tests.md' },
              { text: 'Testcontainers', link: '/testing/testcontainers.md' },
            ]
        },
      ],
      'api': [
        {
          text: 'Referencia de API',
          items: [
              { text: 'Introducción', link: '/api/intro.md' },
              { text: 'GlobalExceptionHandler', link: '/api/global-exception-handler.md' },
              { text: 'KafkaProducer', link: '/api/kafka-producer.md' },
              { text: 'KafkaConsumer', link: '/api/kafka-consumer.md' },
              { text: 'SagaStep', link: '/api/saga-step.md' },
              { text: 'OutboxScheduler', link: '/api/outbox-scheduler.md' },
              { text: 'ErrorDTO', link: '/api/error-dto.md' },
            ]
        },
      ],
    }
  return map[base] || []
}
