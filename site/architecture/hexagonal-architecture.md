# Arquitectura Hexagonal

lg5-spring se basa en **Arquitectura Hexagonal** (también conocida como **Ports-and-Adapters**).

## ¿Qué es la Arquitectura Hexagonal?

Es un patrón de arquitectura que separa la lógica de negocio de los detalles externos (bases de datos, APIs, frameworks). La aplicación está en el centro, y todo lo externo se conecta a través de **puertos** (interfaces) y **adaptadores**.

```
┌─────────────────────────────────────────────────────┐
│                    External World                     │
│                                                         │
│  ┌──────────┐     ┌──────────┐     ┌──────────────┐  │
│  │  Postgres │     │  Kafka   │     │  3rd API     │  │
│  │  / JDBC   │     │  / HTTP  │     │  / HTTP      │  │
│  └────┬─────┘     └────┬─────┘     └──────┬───────┘  │
└───────┼─────────────────┼────────────────────┼────────┘
        │                 │                    │
        ▼                 ▼                    ▼
┌─────────────────────────────────────────────────────────┐
│         │            │            │                      │
│         │  ┌─────────┴────────────┴───────────┐         │
│         │  │            Hexagon                │         │
│         │  │                                    │         │
│         │  │   ┌────────────────────┐           │         │
│         │  │   │   Domain Layer     │           │         │
│         │  │   │  (Business Logic)  │           │         │
│         │  │   └────────────────────┘           │         │
│         │  │                                    │         │
│         │  └────────────────────────────────────┘         │
│         │                 │                              │
│         └─────────────────┼──────────────────────────────┘
                            │
                            ▼
┌────────────────────────────────────────────────────────────┐
│                       Adaptadores                            │
│                                                              │
│  ┌─────────────┐ ┌──────────────┐ ┌──────────────────────┐ │
│  │   Ports      │ │   Ports      │ │     Ports            │ │
│  │   (Input)    │ │   (Output)   │ │    (Input/Output)    │ │
│  │              │ │              │ │                      │ │
│  │  REST        │ │  DB          │ │  Kafka               │ │
│  │  endpoints   │ │  repository  │ │  events                │ │
│  │              │ │              │ │                      │ │
│  └─────────────┘ └──────────────┘ └──────────────────────┘ │
└────────────────────────────────────────────────────────────┘
```

## Principios

1. **The Domain stays clean** — La capa de dominio no conoce frameworks, ni APIs, ni bases de datos.
2. **Dependencies point inward** — Las capas externas solo dependen de las internas, nunca al revés.
3. **Testability** — Los puertos permiten mockear fácilmente las dependencias externas.

## Capas en lg5-spring

```
Domain Core (Pure)
      │
      ▼
Application Service (Use Cases)
      │
      ▼
Data Access (Infrastructure)
      │
      ▼
API (REST endpoints)
      │
      ▼
Messaging (Kafka events)
```

## Capas del Proyecto

| Capa | Paquete | Responsabilidad |
|---|---|---|
| **Domain Core** | `your-domain-core` | Entitys, ValueObjects, DomainExceptions |
| **Application** | `your-application-service` | Use cases, ApplicationService, Sagas |
| **Data Access** | `your-data-access` | JPA Entities, Repository impls |
| **API** | `your-api` | REST controllers, DTOs |
| **Messaging** | `your-message` | Kafka listeners & publishers |
| **External** | `your-external` | 3rd party client adapters |

## Ejemplo de Flujo

```
┌──────────┐
│  Client  │  (HTTP POST /blank)
└────┬─────┘
     │
     ▼
┌──────────┐
│  API     │  BlankController (REST endpoint)
│  Layer   │
└────┬─────┘
     │
     ▼
┌──────────┐
│  App     │  BlankApplicationService → CommandHandler
│  Layer   │      ↓ validates & transforms
└────┬─────┘      ↓
     │        Domain Layer
     ▼        BlankEntity → BlankCreatedEvent
┌──────────┐
│  Data    │  BlankRepository → JPA save
│  Layer   │
└────┬─────┘
     │
     ▼
┌──────────┐
│  Kafka   │  Event to Kafka topic via Outbox
│  Layer   │
└──────────┘
```
