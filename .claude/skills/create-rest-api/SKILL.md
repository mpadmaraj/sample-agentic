---
name: create-rest-api
description: >
  Creates a REST API endpoint that adheres to the architectural,
  coding, testing, and security rules defined in agents.md.
  Orchestrates API design, validation, service logic,
  persistence access, and test generation.
---

# Skill: Create REST API Endpoint

## Purpose
Create a REST API endpoint while strictly following the constraints
and conventions defined in `agents.md`.

---

## When to Use
- Adding a new REST endpoint
- Extending an existing API with new functionality

Do **not** use this skill for:
- UI-only changes
- Internal refactoring without API impact

---

## Inputs
- Endpoint intent
- HTTP method
- Resource name
- Request parameters or body
- Expected success and failure responses

---

## Outputs
- Controller endpoint
- Service-layer logic
- DAO integration (if required)
- Unit tests
- Updated documentation (if applicable)

---

## Governing Rules
This skill must comply with all relevant sections of `agents.md`.
If a conflict exists, **`agents.md` takes precedence**.

---

## Composed Skills
Executed in order:

1. `api-design/SKILL.md`
2. `validation/SKILL.md`
3. `service-logic/SKILL.md`
4. `dao-access/SKILL.md`
5. `test-generation/SKILL.md`

---

## Procedure
1. Clarify requirements and assumptions
2. Design API contract
3. Validate inputs
4. Implement service logic
5. Integrate persistence if required
6. Add unit tests
7. Review against `agents.md`
8. Surface risks and trade-offs

---

## References
- `references/rest-api.md`
- `references/testing.md`

---

## Script References
- `scripts/build.sh`

---

## Failure Handling
- Stop on unclear requirements
- Stop on architectural violations
- Escalate security or design concerns
- Do not auto-fix ambiguous behavior

---

## Review Notes
Document:
- Assumptions
- Trade-offs
- Follow-ups
