---
kind: design
name: design
version: 1.0.0
description: Technical design for LG-90 Spring Boot Upgrade Preparation.
---

# Design — LG-90-upgrade-springboot

> **Anchor:** [`plan.md`](plan.md), [`adr/`](adr/).
> Constitutional rules cited by stable RULE-ID.

## 1. Scope and boundaries

This design covers the preparation phase for the Spring Boot major version upgrade.

- **In design**:
  - REQ-001: Audit of project dependencies (Spring Boot, Kafka, Testcontainers, etc.) against Java 25 / future Spring Boot baseline.
  - REQ-002: Compatibility analysis for core infrastructure (Kafka, messaging).
  - REQ-003: CI/CD pipeline validation for future-compatible dependency stubs.
  - REQ-004: Migration plan documentation.
- **Out of design**:
  - Actual production framework upgrade (out of scope for preparation).
  - Updating non-critical dependencies.

## 2. Domain model (sketch)

- *Skipped* — _(reason: Preparatory audit project, no new aggregates/entities.)_

## 3. REST contracts (RULE-006)

- *Skipped* — _(reason: Preparatory audit project, no new REST API endpoints.)_

## 4. Kafka contracts (RULE-007, RULE-010)

- *Skipped* — _(reason: Preparatory audit project, no new Kafka topics or producers/consumers.)_

## 5. Persistence model (RULE-008)

- *Skipped* — _(reason: Preparatory audit project, no new JPA entities or tables.)_

## 6. Saga design (RULE-009)

- *Skipped* — _(reason: No saga orchestrations implemented in this preparation phase.)_

## 7. Configuration (RULE-014)

- *Skipped* — _(reason: No new functional configuration properties.)_

## 8. Module dependency graph

```
<svc>-api ──► <svc>-application-service ──► <svc>-domain-core
                       │                            ▲
                       ▼                            │
                <svc>-data-access ─────────────────-┘
                       ▲
<svc>-message-core ────┘
        ▲
<svc>-message-model
        ▲
<svc>-container
        ▲
<svc>-acceptance-test
        ▲
<svc>-support
```

## 9. Audit Analysis, Report Structure, and Test design

The audit focuses on validating dependency compatibility with Spring Boot 3.5.14 and Java 25.

### 9.1 Audit Report Structure (Incremental Analysis)

The audit report will be generated as a Markdown file in `docs/specs/LG-90-upgrade-springboot/audit-report.md`.

```markdown
# Dependency Audit Report

## 1. Summary
- Target Baseline: Spring Boot 3.5.14 + Java 25
- Date: <YYYY-MM-DD>
- Status: <PASS/FAIL/ACTION_REQUIRED>

## 2. Dependency Analysis
| Library | Current Version | Target Baseline Compatible? | Notes |
|---------|-----------------|---------------------------|-------|
| spring-boot | 3.5.14 | Yes | Baseline |
| testcontainers | 1.20.0 | Yes | |
| kafka-clients | 3.9.0 | Yes | |
| ... | ... | ... | ... |

## 3. Infrastructure Compatibility
- Kafka: <Status>
- Persistence: <Status>
- Test Support: <Status>
```

### 9.2 Test design for Validation

| REQ | Test type | Lives in | Asserts |
|-----|-----------|----------|---------|
| REQ-001 | Dependency check | `pom.xml` (Maven Enforcer) | Dependency compatibility |
| REQ-002 | IT (`Lg5TestBoot`) | `<svc>-application-service/...` | Infrastructure component health |
| REQ-003 | ATDD (Cucumber) | `<svc>-acceptance-test/...` | CI/CD pipeline end-to-end stability |

## 10. Skipped sections (with justification)

- §2 Domain model — _(reason: Preparatory audit project, no new aggregates/entities.)_
- §3 REST contracts — _(reason: Preparatory audit project, no new REST API endpoints.)_
- §4 Kafka contracts — _(reason: Preparatory audit project, no new Kafka topics.)_
- §5 Persistence model — _(reason: Preparatory audit project, no new JPA entities.)_
- §6 Saga design — _(reason: No saga orchestrations implemented in this preparation phase.)_
- §7 Configuration — _(reason: No new functional configuration properties.)_
- Data Model (`data-model.md`) — _(reason: This feature is a preparatory audit project and does not introduce new persistent state, domain events, or REST DTOs requiring persistence schema or Avro definition.)_

## 11. Open questions

| Question | Impact (PRD/Plan/Design) | Decider | Due |
|---------|--------------------------|---------|-----|
| Are all 3rd-party libs identified? | Plan | Tech Lead | TBD |

## Definition of Done (Design)

- [x] Every REQ-NNN from the PRD maps to ≥1 section (model/REST/Kafka/...).
- [x] Every section either has content or appears in §10 with justification.
- [x] All constitutional rules touched are cited by stable RULE-ID.
- [x] All DTOs are records (RULE-015); all production classes are final-ready.
- [x] All Kafka payloads have an Avro schema referenced (RULE-007).
- [x] Every event-emitting aggregate has an outbox entry referenced (RULE-008).
- [x] Every `SagaStep<T>` (if any) has process + rollback semantics defined (RULE-009).
- [x] Module dependency graph has no cycles and matches RULE-004.
- [x] Configuration uses canonical prefixes (RULE-014).
- [x] Test design maps every REQ-NNN to a concrete test home.
- [x] Open questions explicitly listed (or "none").
- [x] `data-model.md` cross-references resolved (every § that delegates field detail points at the right `data-model.md` §).
