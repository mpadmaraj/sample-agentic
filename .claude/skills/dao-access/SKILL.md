---
name: dao-access
version: 1.0
category: backend
intent: data-access
description: >
  Implements data access logic while maintaining clear separation
  from business logic and respecting architectural boundaries
  defined in agents.md.
---

# Skill: DAO Access

## Purpose
Access data sources without leaking persistence concerns
into higher layers.

---

## When to Use
- Reading or writing persistent data
- Interacting with external data sources

---

## Inputs
- Query criteria
- Persistence constraints

---

## Outputs
- DAO or repository methods
- Clear data access boundaries

---

## Governing Rules
DAO logic must not contain business rules and must follow
architecture constraints in `agents.md`.

---

## Procedure
1. Define required data operation
2. Implement minimal persistence logic
3. Keep APIs simple and explicit
4. Avoid business decisions in DAO

---

## Failure Handling
- Fail on data inconsistency
- Surface data access errors clearly
