---
kind: template
name: adr-template
version: 0.2.0
---

# ADR-001: Adopt Centralized Dependency Management for Upgrade

- **Status:** Accepted
- **Date:** 2026-06-20
- **Deciders:** Lead Architect
- **Consulted:** DevOps Lead

## Context

The current platform version is outdated, requiring a major Spring Boot upgrade. We need a consistent approach to manage dependency versions across the service to ensure compatibility and simplify the upgrade process.

## Decision

We will use `gradle/libs.versions.toml` to centralize all dependency version definitions and upgrade the project's parent POM to the latest framework SHA as defined in `RULE-002`.

## Alternatives considered

- **Manual version updates per build file**
  - Cons: High risk of inconsistency, difficult to audit, and complex upgrade path.
  - Why not chosen: Does not satisfy maintainability requirements.

## Consequences

- **Positive:** Single source of truth for dependencies, easier to upgrade, consistent across modules.
- **Negative:** Requires initial effort to migrate all dependencies to `libs.versions.toml`.

## Constitutional impact

- RULE-001 — Confirms: Aligns with the stack baseline of Spring Boot 3.4.2 / Spring 6.2.2.
- RULE-002 — Confirms: Uses the required `lg5-spring-parent` parent POM.

## Implementation notes

- The PRD this ADR supports: `docs/specs/LG-92-upgrade-springboot-exec/prd.md`.
- Implementation plan: `docs/specs/LG-92-upgrade-springboot-exec/plan.md`.

## Related ADRs

- ADR-002 — Incremental Validation Strategy.
