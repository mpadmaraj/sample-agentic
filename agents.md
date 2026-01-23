# Project: Simple Order Service (Agentic Reference)

This project is a **minimal Spring Boot service** used to demonstrate
Agentic Engineering concepts with clear architectural and coding constraints.

## Architecture

- **Framework**: Spring Boot 3.x
- **Language**: Java 21
- **Build Tool**: Maven
- **API Style**: REST
- **Persistence**: In-memory / stub DAO (for simplicity)
- **Testing**: JUnit 5

Architecture Style:

- Layered architecture
- Controller → Service → DAO
- No direct coupling between controllers and DAOs

## Coding Standards

- Constructor injection only (no field injection)
- Controllers must be thin (no business logic)
- Business logic must live in services
- Prefer composition over inheritance
- Avoid static state and utility classes
- Methods should be small and intention-revealing
- Prefer explicit types and clear naming over brevity
- No unnecessary abstractions

## File & Package Organization

- `controller` – REST controllers (HTTP concerns only)
- `service` – Business logic and orchestration
- `dao` – Data access or persistence stubs
- `resources` – Configuration files
- `test` – Unit tests
  Tests should mirror the main package structure.

## API Design

- Resource-oriented URLs (nouns, not verbs)
- Use appropriate HTTP methods (GET, POST, PUT, DELETE)
- Return meaningful HTTP status codes
- Keep request/response models simple
- Do not leak internal implementation details via APIs

## Testing

- Write tests for all service-layer logic
- Tests should be behavior-focused, not implementation-focused
- Avoid excessive mocking
- Prefer readable tests over clever tests
- Test names should describe behavior clearly

## Security

- Never hardcode secrets or credentials
- Validate all external inputs
- Assume all inputs are untrusted
- Avoid reflection and dynamic execution
- Call out potential security risks explicitly

## Error Handling

- Fail fast on invalid input
- Throw meaningful exceptions with clear messages
- Do not swallow exceptions
- Separate error handling from business logic
- Errors should be understandable to a human reader

## Working Agreement for Agents

- If requirements are unclear, ask clarifying questions
- Do not guess business rules
- Propose changes before implementing them
- Explain trade-offs and assumptions
- Optimize for readability and maintainability over speed
