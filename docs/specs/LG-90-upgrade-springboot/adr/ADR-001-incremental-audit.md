# ADR-001: Incremental Audit Strategy

- **Status:** Proposed
- **Date:** 2026-06-20
- **Deciders:** Platform Engineering Lead
- **Consulted:** Development Team
- **Informed:** Security/Compliance Leads

## Context

To prepare for a major Spring Boot upgrade, we need to ensure the system is ready. A big-bang upgrade is high-risk. We need an incremental, audit-first approach to identify breaking changes, dependency mismatches, and infrastructure requirements before attempting the upgrade.

## Decision

We will adopt an incremental audit-first strategy. We will break down the audit into focused phases: dependency analysis, compatibility check of critical infrastructure components (messaging, test support), and CI/CD pipeline validation. We will document findings in a migration plan before any framework changes are made.

## Alternatives considered

- **Big-Bang Upgrade** — Attempting the upgrade directly in a single iteration.
  - Pros: Faster if successful.
  - Cons: Extremely high risk, difficult to troubleshoot, potential for extended downtime.
  - Why not chosen: Too high risk for critical infrastructure.

- **Parallel Branch Development** — Maintaining a separate branch for the upgrade.
  - Pros: Isolation.
  - Cons: High merge overhead, potential for configuration drift.
  - Why not chosen: Audit and preparation should ideally happen on the main branch to ensure immediate impact and visibility.

## Consequences

- **Positive:** Reduces risk by identifying breaking changes early, ensures CI/CD stability.
- **Negative:** Takes longer to reach the upgrade phase.
- **Neutral:** Increased documentation effort (migration plan).

## Constitutional impact

- RULE-001 — Confirms stack baseline (Spring Boot 3.5.14 + Java 25) for the current audit phase.
- RULE-007, RULE-008, RULE-009, RULE-010, RULE-011, RULE-012, RULE-013, RULE-014, RULE-016 — This ADR ensures that all current architectural rules remain respected during the audit.

## Implementation notes

- PRD: `docs/specs/LG-90-upgrade-springboot/prd.md`
- Plan: `docs/specs/LG-90-upgrade-springboot/plan.md`

## Related ADRs

- ADR-002 — Dependency Management Policy.

## Definition of Done (ADR)

- [x] Status is Proposed.
- [x] Decision is stated in active voice.
- [x] At least one alternative is documented.
- [x] Consequences cover positive AND negative.
- [x] Constitutional impact section names every relevant `must` rule.
- [x] Any `must` override is time-boxed with a tech-debt link.

---

_Originally drafted: 2026-06-20 · Last reviewed: 2026-06-20._
