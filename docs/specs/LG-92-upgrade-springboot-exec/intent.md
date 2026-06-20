---
kind: intent
name: LG-92-upgrade-springboot-exec
version: 0.1.0
---

# Intent — Upgrade core platform execution

> This intent captures the technical execution of the platform upgrade based on the findings in LG-90.

## 1. Problem statement
The core platform version is outdated, creating significant maintenance overhead and limiting compatibility with modern infrastructure and security requirements.

## 2. Who feels it
- **Developers** — increased maintenance effort and difficulty adopting modern patterns.
- **DevOps** — friction in CI/CD pipeline management due to version incompatibilities.
- **Operations/Security** — exposure to risks from running on deprecated infrastructure.

## 3. Why now
A recent technical audit (LG-90) identified this upgrade as a critical prerequisite for continued support and maintenance. Deferring this further increases technical debt and complexity of future migrations.

## 4. Desired outcome
The system is fully compliant with the latest platform standards, with all breaking changes addressed, a fully optimized deployment pipeline, and verified stability through comprehensive automated acceptance tests.

## 5. Success metrics

| Metric | Baseline | Target | Window |
|--------|---------:|-------:|--------|
| Acceptance Test Passing Rate | <100% | 100% | End of project |
| CI/CD Pipeline Duration | Current | ≤ Baseline | Post-upgrade |
| Known Vulnerabilities (Dependency) | >0 | 0 | Post-upgrade |

## 6. Non-goals
- **Functional feature development** — This effort is focused exclusively on the platform upgrade and necessary refactoring to ensure compatibility.
- **Architecture redesign** — We are not changing the core hexagonal architecture or business logic flows, only what is necessary for compatibility.

## 7. Constraints and hints
- **LG-90 Audit Report** — Must address all breaking changes and recommendations identified in the audit.
- **Verified Compatibility** — Acceptance tests (ATDD) must be the primary verification mechanism.

## 8. Open questions

| Question | Decider | Due |
|---------|---------|-----|
| Which specific breaking changes are prioritized? | Lead Architect | Resolved: Prioritize updating `gradle/libs.versions.toml` to baseline, dependency refactoring, and addressing deprecated APIs (per LG-90). |
| Are there any CI/CD specific blockers anticipated? | DevOps Lead | Resolved: No immediate blockers; align Gradle wrapper and GitHub Actions versions with `lg5-spring` practices. |

## Definition of Done (Intent)
- [x] Problem statement is one sentence, observation-flavored.
- [x] At least one user role identified with their specific pain.
- [x] "Why now" honestly answered.
- [x] Desired outcome described in observable terms, no solution naming.
- [x] At least one measurable success metric with baseline + target.
- [x] Non-goals list is explicit.
- [x] Open questions tabled.
- [x] Intent fits on one screen.
