# Plan — Upgrade Spring Boot Preparation (LG-90)

> **Use this template via `/sdd-plan`.** Generated from
> [`prd.md`](prd.md) and the ADRs under [`adr/`](adr/).
> The plan describes the **how** at the architectural level; concrete
> code lives in [`tasks.md`](tasks.md) and the actual repo.

## Architecture overview

Module map (RULE-004), with one-line purpose per module.

```
<svc>-domain/
  ├── <svc>-domain-core         # Core business logic (Spring-free)
  └── <svc>-application-service # Orchestration of domain components
<svc>-api                       # REST controllers and contracts
<svc>-data-access               # JPA repositories and entities
<svc>-message/
  ├── <svc>-message-core        # Kafka producers/consumers logic
  └── <svc>-message-model       # Avro schema definitions
<svc>-container                 # Spring Boot application entry point
<svc>-acceptance-test           # Cucumber/Testcontainers integration tests
<svc>-support                   # Common utilities and configuration
```

## Module ↔ requirement matrix

| Module | Covers REQ |
|--------|------------|
| `<svc>-domain-core` | REQ-001 |
| `<svc>-application-service` | REQ-002 |
| `<svc>-message-core` | REQ-002, REQ-003 |
| `<svc>-acceptance-test` | REQ-003 |
| `<svc>-container` | REQ-003, REQ-004 |
| `<svc>-support` | REQ-001 |

## ADR index

- [ADR-001](adr/ADR-001-incremental-audit.md) — Incremental Audit Strategy.
- [ADR-002](adr/ADR-002-dependency-management.md) — Dependency Management Policy.

## Sequenced steps

See [`tasks.md`](tasks.md) for the full TASK-NNN list. Summary of the
dependency graph:

```
TASK-001 (Audit) ──► TASK-002 (Compatibility Analysis) ──► TASK-003 (CI/CD Pipeline Validation) ──► TASK-004 (Migration Document)
```

## Cross-cutting concerns

- **Build System:** Maven POM alignment with future Spring Boot versions.
- **CI/CD:** Pipeline stability and artifact caching.
- **Test Infrastructure:** Verification of testcontainers and mock frameworks compatibility.

## Risks

| ID | Risk | Mitigation | Owner |
|----|------|------------|-------|
| R1 | Hidden breaking changes in libraries. | Comprehensive audit and CI pipeline validation. | Platform Engineering Lead |
| R2 | Incompatibility with messaging infrastructure. | Dedicated compatibility analysis for Kafka components. | Development Team |

## Estimated artifact count

- New files: `~3` (ADRs + Plan)
- Modified files: `~0` (No production code changes yet)
- New tests: `~0` (Only CI validation)

## Definition of Done (Plan)

- [x] Every PRD requirement is covered by ≥1 module in the matrix above.
- [x] Every architectural decision is captured as an ADR under `adr/`.
- [x] Constitutional rule violations explicitly listed and justified (or zero).
- [x] Module map matches RULE-004 (service module shape).
- [x] Open questions explicitly listed (under "Risks" or in PRD §8).
- [x] All cross-cutting concerns assigned to a team/owner.
