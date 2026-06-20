# ADR-002: Dependency Management Policy for Upgrade Preparation

- **Status:** Proposed
- **Date:** 2026-06-20
- **Deciders:** Platform Engineering Lead
- **Consulted:** Development Team
- **Informed:** Security/Compliance Leads

## Context

Our dependency management relies on a BOM approach. As we prepare for a Spring Boot major upgrade, we need to ensure our dependency management strategy allows for controlled updates of plugins and libraries while adhering to constitutional rules.

## Decision

We will adopt a 'strict pinning' policy for all critical dependencies (as identified in the PRD) during the audit phase. We will use the Maven BOM to manage library versions, ensuring consistency across all modules. Any proposed change to critical dependency versions must be accompanied by a validation report in the CI pipeline.

## Alternatives considered

- **Loose Versioning** — Allowing flexible version ranges in POMs.
  - Pros: Easier to adopt updates.
  - Cons: Unpredictable builds, potential for breakages in CI/CD.
  - Why not chosen: Risk is too high during an upgrade preparation phase.

## Consequences

- **Positive:** Predictable builds, clear upgrade path.
- **Negative:** Increased management overhead for dependency versions.

## Constitutional impact

- RULE-002 — Confirms `lg5-spring-parent` usage.
- RULE-001 — Confirms that all dependency changes must align with future requirements without violating the current baseline until the upgrade happens.

## Implementation notes

- PRD: `docs/specs/LG-90-upgrade-springboot/prd.md`
- Plan: `docs/specs/LG-90-upgrade-springboot/plan.md`

## Related ADRs

- ADR-001 — Incremental Audit Strategy.

## Definition of Done (ADR)

- [x] Status is Proposed.
- [x] Decision is stated in active voice.
- [x] At least one alternative is documented.
- [x] Consequences cover positive AND negative.
- [x] Constitutional impact section names every relevant `must` rule.
- [x] Any `must` override is time-boxed with a tech-debt link.

---

_Originally drafted: 2026-06-20 · Last reviewed: 2026-06-20._
