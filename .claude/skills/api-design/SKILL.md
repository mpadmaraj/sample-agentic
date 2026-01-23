---
name: api-design
description: >
  Designs a REST API contract that aligns with architectural,
  naming, and interface rules defined in agents.md.
---

# Skill: API Design

## Purpose
Define a clear and consistent REST API contract before implementation.

---

## When to Use
- Creating a new API endpoint
- Modifying an existing API contract

---

## Inputs
- Resource name
- Operation intent
- HTTP method
- Request parameters or body
- Expected responses

---

## Outputs
- Endpoint path
- HTTP method
- Request model
- Response model
- Error scenarios

---

## Governing Rules
All API design decisions must comply with `agents.md`,
especially sections on Architecture and API Design.

---

## Procedure
1. Identify the primary resource
2. Select the appropriate HTTP method
3. Define request and response structure
4. Identify error cases
5. Validate design against `agents.md`

---

## Failure Handling
- Stop if API semantics are unclear
- Stop if design leaks internal details
- Escalate ambiguous contracts
