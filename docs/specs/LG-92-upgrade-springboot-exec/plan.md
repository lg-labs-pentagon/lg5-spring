---
kind: template
name: plan-template
version: 0.1.0
---

# Plan — LG-92-upgrade-springboot-exec

> Derived from `prd.md` and ADRs `adr/ADR-001-dependency-management.md`, `adr/ADR-002-incremental-validation.md`.

## Architecture overview

The upgrade does not change the core architecture (RULE-004), it only updates the underlying framework dependencies.

```
<svc>-domain/
  ├── <svc>-domain-core         # Business logic
  └── <svc>-application-service # Saga orchestrators, services
<svc>-api                       # REST controllers
<svc>-data-access               # JPA repositories
<svc>-message/
  ├── <svc>-message-core        # Kafka producers/consumers
  └── <svc>-message-model       # Avro schemas
<svc>-container                 # Spring Boot application context
<svc>-acceptance-test           # Cucumber IT/ATDD
<svc>-support                   # Infrastructure/common helpers
```

## Module ↔ requirement matrix

| Module | Covers REQ |
|--------|------------|
| All Modules | REQ-001, REQ-002 |
| `<svc>-acceptance-test` | REQ-003 |
| `<svc>-container` | REQ-004 |

## ADR index

- [ADR-001](adr/ADR-001-dependency-management.md) — Centralized dependency management.
- [ADR-002](adr/ADR-002-incremental-validation.md) — Incremental validation via acceptance tests.

## Sequenced steps

1.  **Upgrade Parent POM & Base Dependencies** (ADR-001).
2.  **Refactor Deprecated Code** (REQ-002).
3.  **Run Acceptance Test Suite** (REQ-003, ADR-002).
4.  **Verify Pipeline Stability** (REQ-004).

## Cross-cutting concerns

- **Observability:** Verify existing metrics/alerts post-upgrade.
- **Data lifecycle:** Ensure Liquibase migration paths are not impacted.

## Risks

| ID | Risk | Mitigation | Owner |
|----|------|------------|-------|
| R1 | Regression in existing functionality | Comprehensive ATDD suite (ADR-002) | Dev Team |
| R2 | Pipeline instability due to plugin conflicts | Incremental migration | DevOps |

## Estimated artifact count

- New files: `~2` (ADRs)
- Modified files: `~10` (build files, deprecated code)
- New tests: `0` (reusing existing)

## Definition of Done (Plan)

- [x] Every PRD requirement is covered by ≥1 module in the matrix above.
- [x] Every architectural decision is captured as an ADR under `adr/`.
- [x] Constitutional rule violations explicitly listed and justified (None).
- [x] Module map matches RULE-004 (service module shape).
- [x] Open questions explicitly listed (in PRD §8).
- [x] All cross-cutting concerns assigned.
