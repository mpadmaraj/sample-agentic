# Testing Reference

## Purpose of Tests
- Verify behavior, not implementation
- Provide confidence during change
- Serve as living documentation

## What to Test
- Business logic in services
- Edge cases and failure paths
- Input validation rules

## What to Avoid
- Testing private methods
- Over-mocking dependencies
- Brittle tests tied to implementation details

## Test Design
- One behavior per test
- Clear, descriptive test names
- Arrange → Act → Assert structure

## Quality Guidelines
- Prefer readable tests over high coverage
- Tests should fail clearly and informatively
- If a test is hard to write, the code may need refactoring
