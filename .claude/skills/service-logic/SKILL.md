---
name: service-logic
description: >
  Implements business logic in the service layer while enforcing
  separation of concerns and coding standards defined in agents.md.
---

# Skill: Service Logic

## Purpose
Implement business rules in the service layer only.

---

## When to Use
- Adding or modifying business logic
- Coordinating multiple operations

---

## Inputs
- Business requirements
- Validated input data

---

## Outputs
- Service-layer implementation
- Business rule enforcement

---

## Governing Rules
Service logic must comply with Architecture and Coding Standards
in `agents.md`.

---

## Procedure
1. Identify business responsibility
2. Implement logic in small, readable methods
3. Avoid framework-specific code in logic
4. Prepare logic for testing

---

## Failure Handling
- Fail on unclear business rules
- Surface domain errors explicitly
