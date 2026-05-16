# lg5-spring — Documentación

Documentación del framework lg5-spring para crear microservicios Spring Boot 3.5 + JDK 21 usando arquitectura hexagonal, DDD, Kafka y más.

---

## Contenido

| Sección | Descripción |
|---|---|
| [Guía](get-started/introduction.md) | Introducción, instalación, estructura de proyecto |
| [Core](core/libraries-overview.md) | Librerías del framework (parent, starter, kafka, ...) |
| [Arquitectura](architecture/hexagonal-architecture.md) | Hexagonal, DDD, módulos, event-driven |
| [Ejemplo Práctico](example/blank-service-overview.md) | blank-service completo en 8 pasos |
| [Testing](testing/overview.md) | Unit, Integration, Acceptance, Testcontainers |
| [API Reference](api/intro.md) | Referencia de las APIs del framework |

---

## Empezar

```bash
# Dev
npm run docs:dev

# Build
npm run docs:build

# Preview
npm run docs:preview
```

---

## Estructura

```
site/
├── index.md                                          # Inicio
├── .vitepress/                                    # Config VitePress
│     └── config.ts                              # Site config
├── get-started/                                    # Guía de inicio
├── core/                                            # Librerías core
├── architecture/                                # Arquitectura
├── example/                                      # Ejemplo: blank-service
├── testing/                                    # Testing
└── api/                                        # API Reference
```
