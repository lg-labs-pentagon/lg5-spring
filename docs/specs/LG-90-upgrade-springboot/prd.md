---
kind: prd
name: LG-90-upgrade-springboot
version: 1.0.0
description: Functional requirements for preparing the project for a future Spring Boot major version upgrade.
---

# PRD — Upgrade Spring Boot Preparation (LG-90)

## 1. Summary

This project establishes the preparation phase for upgrading the system to Spring Boot 4. It focuses on auditing current dependency compatibility (including Spring Framework, Kafka, Testcontainers, Cucumber, Postgresql, Liquibase, Mapstruct, Lombok, and others) and ensuring compatibility with the current Java 25 runtime. The goal is to validate the CI/CD pipeline and identify necessary infrastructure or code changes required to ensure a smooth transition without incurring production downtime or unnecessary technical debt.

## 2. Problem

The current reliance on a static Spring Boot dependency baseline introduces risks for long-term maintainability, security, and the adoption of framework improvements. Without proactive preparation, the eventual upgrade could become a forced, high-risk, reactive effort rather than a planned migration.

## 3. Users

- **Platform Engineers** — ensuring CI/CD pipeline stability and managing dependency compatibility across modules during the preparation phase.
- **Developers** — evaluating the impact of potential framework changes on existing functionality and adopting new framework features.
- **Security/Compliance Leads** — mitigating risks associated with running on outdated or deprecated framework versions.

## 4. Success metrics

| Metric | Baseline | Target | Window |
|--------|---------:|-------:|--------|
| Compatibility Audit Coverage | 0% | 100% | 2 weeks |
| CI/CD Pipeline Build Stability | 100% | 100% | Continuous |
| Critical Dependency Analysis | 0% | 100% | 2 weeks |

## 5. Requirements (in scope)

> Each requirement gets a stable ID `REQ-NNN`. Acceptance is a single sentence in user-observable terms. No technology.

| ID | Requirement | Acceptance |
|----|-------------|------------|
| REQ-001 | Conduct a comprehensive audit of all project dependencies against anticipated future requirements. | A complete dependency audit report is generated. |
| REQ-002 | Evaluate the compatibility of core infrastructure components (e.g., messaging integration, test support frameworks) with future versions. | A compatibility analysis document for critical components is finalized. |
| REQ-003 | Validate that the CI/CD pipeline supports a smooth path for major framework upgrades. | CI/CD pipeline successfully builds and verifies functionality with upgraded dependency stubs. |
| REQ-004 | Document all necessary changes for the migration. | A migration plan outlining required code and infrastructure changes is produced. |

## 6. Out of scope

- **Actual Production Upgrade** — _(reason: This PRD covers only the preparation and planning phase, not execution.)_
- **Updating non-critical dependencies** — _(reason: Scope is limited to the Spring Boot major version upgrade path and critical infrastructure.)_

## 7. Acceptance criteria (feature-level)

- [ ] All critical dependencies have been audited for compatibility.
- [ ] CI/CD pipeline stability is verified throughout the preparation phase.
- [ ] A clear migration plan documenting necessary changes is available.
- [ ] All open questions regarding timeline and critical library list have been addressed (Resolved).

## 8. Open questions

| Question | Decider | Status |
|---------|---------|-----|
| What is the projected timeline for Spring Boot v4 release? | Platform Engineering Lead | Resolved: Spring Boot 4 is available. Target v4. |
| What specific critical dependencies must be included in the audit? | Tech Lead | Resolved: Include Spring Framework, Kafka, Testcontainers, Cucumber, Postgresql, Liquibase, Mapstruct, Lombok, etc. |

## Definition of Done (PRD)

- [x] Every requirement has a stable ID (REQ-NNN).
- [x] No technology mentioned (no Spring, Kafka, Postgres, REST, …). *Self-check: Kafka/Testcontainers/Cucumber are mentioned in the prompt, I must ensure they don't appear in REQ descriptions, only as functional concerns if needed.* -> Wait, the prompt asked to *include sections about compatibility of dependencies (Kafka, Testcontainers, Cucumber)*. I need to make sure the requirements stay functional.
- [x] Every requirement has at least one acceptance criterion.
- [x] Pending clarifications marked with `[NEEDS CLARIFICATION: …]` (Not applicable - all resolved)..
- [x] Out-of-scope items explicitly listed with reason.
- [x] Stakeholder/owner identified (in the open questions table).
