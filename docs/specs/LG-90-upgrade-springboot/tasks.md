# Tasks — LG-90-upgrade-springboot

> **Dep graph**
> TASK-001 ──► TASK-002 ──► TASK-003 ──► TASK-004 ──► TASK-005 ──► TASK-006

## TASK-001 — Initialize audit environment and report structure
- **Status:** `done`
- **Completion:** Implemented audit report structure (commit af7032f)
- **References:** REQ-001, ADR-001
- **Depends on:** —
- **Modules touched:** `docs/specs/LG-90-upgrade-springboot/`
- **Skill:** lg5-spring-overview
- **Command:** (none)
- **Acceptance:**
  - **Given** I am in the `LG-90-upgrade-springboot` specs directory
  - **When** I create `audit-report.md` with the structure from design §9.1
  - **Then** the file exists and is ready for population

## TASK-002 — Perform dependency audit
- **Status:** `done`
- **Completion:** Completed dependency audit and updated report (commit f638d6b)
- **References:** REQ-001, ADR-001, ADR-002
- **Depends on:** TASK-001
- **Modules touched:** `gradle/libs.versions.toml`, `docs/specs/LG-90-upgrade-springboot/audit-report.md`
- **Skill:** lg5-spring-overview
- **Command:** (none)
- **Acceptance:**
  - **Given** `audit-report.md` exists
  - **When** I run `gradle dependencies` (since it's a gradle project) and analyze against Spring Boot 3.5.14 and Java 25
  - **Then** `audit-report.md` §2 is populated with compatibility status

## TASK-003 — Infrastructure compatibility verification
- **Status:** `todo`
- **References:** REQ-002
- **Depends on:** TASK-002
- **Modules touched:** `<svc>-application-service`, `<svc>-acceptance-test`
- **Skill:** lg5-atdd
- **Command:** `mvn test`
- **Acceptance:**
  - **Given** target infrastructure baseline is established
  - **When** I run existing IT tests in `application-service` and `acceptance-test`
  - **Then** all tests pass and `audit-report.md` §3 is updated

## TASK-004 — Validate CI/CD pipeline compatibility
- **Status:** `todo`
- **References:** REQ-003
- **Depends on:** TASK-003
- **Modules touched:** `.github/workflows/`
- **Skill:** lg5-github-actions
- **Command:** (none)
- **Acceptance:**
  - **Given** IT tests are green
  - **When** I perform a local CI/CD workflow simulation with target configuration
  - **Then** the build passes and `audit-report.md` contains CI compatibility notes

## TASK-005 — Finalize migration plan
- **Status:** `todo`
- **References:** REQ-004
- **Depends on:** TASK-004
- **Modules touched:** `docs/specs/LG-90-upgrade-springboot/audit-report.md`
- **Skill:** lg5-spring-overview
- **Command:** (none)
- **Acceptance:**
  - **Given** all audit analysis is complete
  - **When** I document the migration roadmap in `audit-report.md`
  - **Then** the audit report is complete and ready for sign-off

## TASK-006 — Final Verification
- **Status:** `todo`
- **References:** REQ-001, REQ-002, REQ-003, REQ-004
- **Depends on:** TASK-005
- **Modules touched:** `docs/specs/LG-90-upgrade-springboot/audit-report.md`
- **Skill:** lg5-spring-overview
- **Command:** (none)
- **Acceptance:**
  - **Given** all tasks are done
  - **When** I perform a final audit report review
  - **Then** all ATDD scenarios are green (as applicable), zero MUST violations, and the audit report is signed off

## Definition of Done (Tasks)
- [ ] Every TASK references ≥1 REQ-NNN.
- [ ] Every TASK has Given/When/Then acceptance criteria.
- [ ] Every TASK is ≤1 day of work / 1-3 commits.
- [ ] Dependencies form a DAG (no cycles) — verified.
- [ ] First TASK is the project skeleton.
- [ ] Last TASK is "all ATDD scenarios green + zero `must` violations".
- [ ] Each TASK names the exact module(s), skill(s), and command(s) it uses.
