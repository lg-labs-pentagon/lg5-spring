---
kind: intent
name: 001-upgrade-springboot
version: 0.1.0
---

# Intent — 001-upgrade-springboot

## 1. Problem statement
The project relies on a static Spring Boot dependency baseline, and upcoming major version releases pose risks to maintainability, security, and the ability to adopt framework improvements if not proactively prepared for.

## 2. Who feels it
- **Platform Engineers** — maintaining CI/CD stability and managing dependency compatibility across modules.
- **Developers** — hindered by potential incompatibility when attempting to adopt new Spring/framework features.
- **Security/Compliance Leads** — risks from running on deprecated or soon-to-be-deprecated framework versions.

## 3. Why now
Preparing for future Spring Boot major versions (e.g., v4) is a strategic action to de-risk long-term maintenance and avoid large-scale, reactive refactoring in the future. Starting this process now ensures that our infrastructure (CI/CD, test framework) and critical dependencies (Kafka, Testcontainers) are evaluated and compatible before the upgrade becomes a forced necessity.

## 4. Desired outcome
The project's dependency graph is audited for compatibility with future Spring Boot major versions, the CI/CD pipeline is validated to support the upgrade path, and a clear understanding of required infrastructure/code changes is established without incurring production downtime or unnecessary technical debt.

## 5. Success metrics

| Metric | Baseline | Target | Window |
|--------|---------:|-------:|--------|
| Compatibility Audit Coverage | 0% | 100% | 2 weeks |
| CI/CD Pipeline Build Stability | 100% | 100% | Continuous |
| Critical Dependency Analysis | 0% | 100% | 2 weeks |

## 6. Non-goals
- **Actual Production Upgrade** — _(reason: This is an intent for planning/preparation, not execution.)_
- **Updating non-critical dependencies** — _(reason: Scope is limited to the Spring Boot major version upgrade path and critical infrastructure.)_

## 7. Constraints and hints
- **Testcontainers & Cucumber usage** — _(rationale: Critical infrastructure that must be verified for compatibility with new Spring Boot versions.)_
- **Kafka/Avro integration** — _(rationale: Core communication path that is sensitive to Spring Boot/Kafka client version changes.)_

## 8. Open questions
| Question | Decider | Due |
|---------|---------|-----|
| When is the projected timeline for Spring Boot v4 release or alpha usage? | Platform Engineering Lead | [NEEDS CLARIFICATION: Check official Spring Blog] |
| What specific critical dependencies (beyond Kafka/Testcontainers/Cucumber) must be included in the audit? | Tech Lead | [NEEDS CLARIFICATION: List critical libs] |

## Definition of Done (Intent)
- [x] Problem statement is one sentence, observation-flavored.
- [x] At least one user role identified.
- [x] "Why now" answered.
- [x] Desired outcome described in observable terms.
- [x] Success metrics with baseline + target.
- [x] Non-goals list is explicit.
- [x] Open questions tabled.
- [x] Intent fits on one screen.
