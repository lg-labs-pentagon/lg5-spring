---
kind: tasks
name: LG-92-upgrade-springboot-exec
version: 1.0.0
---

# Tasks — LG-92-upgrade-springboot-exec

> Generated from [`design.md`](design.md).

## Dep graph (ASCII)
```
TASK-001 ──► TASK-002 ──► TASK-003 ──► TASK-004 ──► TASK-005 ──► TASK-006 ──► TASK-007
```

## TASK-001 — Environment Preparation and Dependency Audit

- **Status:** `done`
- **Completion:** Implemented dependency audit and created audit report (commit SHA placeholder)
- **References:** REQ-001, RULE-001
- **Depends on:** —
- **Modules touched:** `container`
- **Skill:** `lg5-spring-overview`
- **Command / Subagent:** `/run gradle dependencies`
- **Acceptance:**
  - **Given** the current project environment.
  - **When** I run the dependency check task.
  - **Then** I have a full report of all dependencies and identify potential conflicts with Spring Boot 4.0.0.

## TASK-002 — Upgrade Parent POM and Spring Boot BOM

- **Status:** `todo`
- **References:** REQ-001, RULE-002
- **Depends on:** TASK-001
- **Modules touched:** `container` (Parent POM)
- **Skill:** `lg5-spring-overview`
- **Command / Subagent:** `/edit build.gradle`
- **Acceptance:**
  - **Given** a clear dependency list from TASK-001.
  - **When** I update the Spring Boot BOM version to 4.0.0.
  - **Then** the project successfully builds with the new BOM.

## TASK-003 — Dependency Alignment and Conflict Resolution

- **Status:** `todo`
- **References:** REQ-001, RULE-001
- **Depends on:** TASK-002
- **Modules touched:** `gradle/libs.versions.toml`, `container`
- **Skill:** `lg5-spring-overview`
- **Command / Subagent:** `/edit gradle/libs.versions.toml`
- **Acceptance:**
  - **Given** the upgraded Spring Boot BOM.
  - **When** I align all third-party dependencies to versions compatible with Spring Boot 4.0.0.
  - **Then** `gradle dependencies` shows no version conflicts or resolution errors.

## TASK-004 — Codebase Adaptation and Refactoring

- **Status:** `todo`
- **References:** REQ-002, RULE-015
- **Depends on:** TASK-003
- **Modules touched:** `api`, `domain_service`, `domain_core`, `data_access`, `message_core`, `message_model`
- **Skill:** `lg5-spring-overview`
- **Command / Subagent:** `/run gradle compileJava`
- **Acceptance:**
  - **Given** the dependencies are resolved.
  - **When** I compile the code and address all deprecations/breaking changes reported by the compiler.
  - **Then** the application compiles cleanly without deprecation warnings (treated as errors).

## TASK-005 — Update Acceptance and Testcontainers Dependencies

- **Status:** `todo`
- **References:** REQ-003, RULE-012, RULE-013, ADR-002
- **Depends on:** TASK-004
- **Modules touched:** `acceptance_test`, `container`
- **Skill:** `lg5-atdd`
- **Command / Subagent:** `/run gradle test`
- **Acceptance:**
  - **Given** the upgraded codebase.
  - **When** I update the Cucumber, JUnit, and Testcontainers dependencies in the acceptance-test module.
  - **Then** the integration and acceptance tests run successfully in the containerized environment.

## TASK-006 — CI/CD Pipeline and JDK Environment Update

- **Status:** `todo`
- **References:** REQ-004, RULE-009
- **Depends on:** TASK-005
- **Modules touched:** `.github/workflows/c-integration.yml`
- **Skill:** `lg5-github-actions`
- **Command / Subagent:** `/edit .github/workflows/c-integration.yml`
- **Acceptance:**
  - **Given** a working local build and test suite.
  - **When** I update the GitHub Action workflows to use the JDK 21 zulu image.
  - **Then** the CI pipeline builds, tests, and validates the service successfully on the new environment.

## TASK-007 — Final Verification and Cleanup

- **Status:** `todo`
- **References:** REQ-003, REQ-004, RULE-004
- **Depends on:** TASK-006
- **Modules touched:** All
- **Skill:** `lg5-atdd`
- **Command / Subagent:** `/run cucumber`
- **Acceptance:**
  - **Given** all tasks complete.
  - **When** I run the full test suite and code review tools.
  - **Then** all ATDD scenarios are green and there are zero `must`-severity violations from `lg5-code-reviewer`.

## Definition of Done (Tasks)

- [x] Every TASK references ≥1 REQ-NNN.
- [x] Every TASK has Given/When/Then acceptance criteria.
- [x] Every TASK is ≤1 day of work / 1-3 commits.
- [x] Dependencies form a DAG (no cycles).
- [x] First TASK is the project skeleton/precondition.
- [x] Last TASK is "all ATDD scenarios green + zero `must` violations".
- [x] Each TASK names the exact module(s), skill(s), and command(s).

