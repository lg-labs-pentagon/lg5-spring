---
kind: template
name: adr-template
version: 0.2.0
---

# ADR-002: Incremental Validation via Acceptance Tests

- **Status:** Accepted
- **Date:** 2026-06-20
- **Deciders:** Lead Architect
- **Consulted:** DevOps Lead

## Context

A major platform upgrade poses a significant risk of functional regressions. We need a strategy to validate the upgrade incrementally without risking system stability.

## Decision

We will use the existing automated acceptance test suite as the primary gate for validation at each incremental step of the upgrade. We will only proceed to the next upgrade step if 100% of acceptance tests pass.

## Alternatives considered

- **Manual QA validation**
  - Cons: Slow, error-prone, and not repeatable.
  - Why not chosen: Fails to meet the success metric of automated stability.

## Consequences

- **Positive:** High confidence in functional integrity, fast feedback loop, repeatable process.
- **Negative:** Requires initial investment to ensure test suite is comprehensive and stable.

## Constitutional impact

- RULE-012 — Confirms: Uses the required `@ActiveProfiles({"test","local"})` and extends `Lg5TestBoot[PortNone]`.
- RULE-013 — Confirms: Uses Testcontainers opt-in via `testcontainers.<name>.enabled`.

## Implementation notes

- The PRD this ADR supports: `docs/specs/LG-92-upgrade-springboot-exec/prd.md`.
- Implementation plan: `docs/specs/LG-92-upgrade-springboot-exec/plan.md`.

## Related ADRs

- ADR-001 — Dependency Management Strategy.
