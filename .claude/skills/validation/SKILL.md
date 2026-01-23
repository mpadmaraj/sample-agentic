---
name: validation
description: >
  Defines and applies input validation rules to ensure all
  external inputs are checked before business logic execution,
  following constraints in agents.md.
---

# Skill: Input Validation

## Purpose
Ensure all external inputs are validated before processing.

---

## When to Use
- Handling user or external input
- Accepting request parameters or payloads

---

## Inputs
- Input fields
- Validation rules
- Constraints and limits

---

## Outputs
- Validation logic
- Clear validation failure responses

---

## Governing Rules
Validation must align with Security and Error Handling
guidelines in `agents.md`.

---

## Procedure
1. Identify all external inputs
2. Define validation rules for each input
3. Fail fast on invalid data
4. Provide clear error messages

---

## Failure Handling
- Stop execution on validation failure
- Do not proceed to business logic
