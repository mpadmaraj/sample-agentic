# Simple Order Service

A minimal Spring Boot 3.x reference implementation demonstrating **Agentic Engineering** principles with clear architectural constraints and AI-friendly documentation.

## Project Intent

This project exists as a **teaching tool and blueprint for agentic systems**—showing how to structure a Spring Boot application so that AI agents can understand and extend it reliably. It prioritizes clarity and constraint-driven development over feature completeness.

The service provides a simple REST API for querying order status, deliberately minimal to keep focus on architecture and coding discipline.

## Architecture Overview

**Framework & Stack**
- Java 21 with Spring Boot 3.x
- Maven build system
- In-memory DAO (no external persistence)
- JUnit 5 for testing
- REST API style

**Architectural Pattern**
```
HTTP Request
    ↓
OrderController (HTTP concerns only)
    ↓
OrderService (business logic)
    ↓
OrderDao (data access stub)
```

**Core Principles**
- Layered architecture with strict separation of concerns
- Constructor injection only (no field injection)
- Controllers are thin (no business logic)
- Service layer owns all business rules
- No direct coupling between layers

## Design Principles & Constraints

### Must Never Change Casually
- **Constructor Injection Only** – Field injection defeats dependency management and testability
- **Thin Controllers** – All business logic must live in services. Controllers handle HTTP concerns only
- **Single Responsibility Per Layer** – Respect the controller → service → DAO boundary
- **Fail Fast** – Validate inputs immediately; throw meaningful exceptions; never swallow errors
- **No Utility Classes** – Avoid static methods and god objects; use composition instead
- **Clear Naming** – Prefer explicit, long names over clever abbreviations

### What This Project Is NOT

- ❌ **Not production-ready** – Uses in-memory storage; no real database
- ❌ **Not a feature factory** – Intentionally minimal API to maintain clarity
- ❌ **Not a microservices template** – Single service only; focuses on internals, not orchestration
- ❌ **Not a full-stack example** – No frontend, no DevOps, no cloud deployment
- ❌ **Not an authorization reference** – No authentication/RBAC examples
- ❌ **Not a persistence guide** – DAO is a stub; not intended as JPA/Hibernate template

## Directory Structure

```
src/main/java/com/example/agentic/
├── Application.java              # Spring Boot entry point
├── controller/
│   └── OrderController.java      # HTTP layer (GET /orders/{id})
├── service/
│   └── OrderService.java         # Business logic layer
├── dao/
│   └── OrderDao.java             # Data access stub
├── dto/                          # Request/response models (placeholder)
├── exception/                    # Custom exceptions (placeholder)
└── model/                        # Domain models (placeholder)

src/test/java/com/example/agentic/
└── service/
    ├── OrderServiceTest.java     # Service layer tests
    └── InvoiceServiceTest.java   # Example test file

.claude/skills/                   # AI agent skill definitions
├── api-design/
├── create-rest-api/
├── dao-access/
├── documentation/
├── service-logic/
├── test-generation/
└── validation/

.github/agents/                   # Agent configurations
├── code-reviewer.agent.md
├── readme-specialist.agent.md
└── subagents/
    └── documentation-writer.agent.md

agents.md                         # Master governance file for all development
```

## API Design

### Current Endpoint

**GET /orders/{id}**
- **Purpose:** Retrieve the status of an order by ID
- **Status Code:** 200 OK
- **Response:** Plain string (e.g., "CREATED")
- **Current Behavior:** Returns hardcoded "CREATED" status (stub implementation)

> ⚠️ **Design Debt:** This endpoint should return a structured DTO with timestamp and full order details. See [Error Handling Strategy](#error-handling-strategy) for missing validation and error responses.

### API Design Principles

All API changes must comply with these rules (from `agents.md`):
- Use resource-oriented URLs (nouns, not verbs)
- Use appropriate HTTP methods (GET, POST, PUT, DELETE)
- Return meaningful HTTP status codes
- Keep request/response models simple
- Never leak internal implementation details via APIs

## Coding Standards

### JavaScript-Agnostic Guidelines
These constraints apply universally (enforced by agents):

**Code Organization**
- Constructor injection only
- Controllers must be thin (no business logic)
- Business logic must live in services
- Prefer composition over inheritance
- Avoid static state and utility classes

**Code Quality**
- Methods should be small and intention-revealing
- Prefer explicit types and clear naming over brevity
- No unnecessary abstractions
- Fail fast on invalid input
- Throw meaningful exceptions with clear messages
- Do not swallow exceptions

**Testing**
- Write tests for all service-layer logic
- Tests should be behavior-focused, not implementation-focused
- Avoid excessive mocking
- Prefer readable tests over clever tests
- Test names should describe behavior clearly

**Security**
- Never hardcode secrets or credentials
- Validate all external inputs
- Assume all inputs are untrusted
- Avoid reflection and dynamic execution
- Call out potential security risks explicitly

## Error Handling Strategy

Current state: **Incomplete** – No validation, exception handling, or error responses defined.

**Gaps Identified:**
- ❌ No HTTP status codes (should use 400, 404, 500, etc.)
- ❌ No validation for order IDs (negative, zero, non-existent)
- ❌ No structured error responses
- ❌ No exception hierarchy defined
- ❌ No error logging strategy

**Expected Pattern (from `agents.md`):**
- Fail fast on invalid input
- Throw meaningful exceptions with clear messages
- Do not swallow exceptions
- Separate error handling from business logic
- Errors should be understandable to a human reader

## Skills Available in This Project

AI agents use these skill definitions to perform consistent, high-quality work:

| Skill | Purpose | Location |
|-------|---------|----------|
| **api-design** | Design REST API contracts aligned with architectural rules | [.claude/skills/api-design/](/.claude/skills/api-design/) |
| **create-rest-api** | Implement REST endpoints with proper HTTP semantics | [.claude/skills/create-rest-api/](/.claude/skills/create-rest-api/) |
| **dao-access** | Define and implement data access patterns | [.claude/skills/dao-access/](/.claude/skills/dao-access/) |
| **documentation** | Create and improve README and project documentation | [.claude/skills/documentation/](/.claude/skills/documentation/) |
| **service-logic** | Implement business logic in service layer | [.claude/skills/service-logic/](/.claude/skills/service-logic/) |
| **test-generation** | Generate behavior-focused unit tests | [.claude/skills/test-generation/](/.claude/skills/test-generation/) |
| **validation** | Implement input validation and error handling | [.claude/skills/validation/](/.claude/skills/validation/) |

Each skill file contains detailed procedures, inputs, outputs, and governing rules.

## Agents & Automation

This project includes AI agent definitions to automate and constrain development tasks:

### Available Agents

**Code Reviewer** ([.github/agents/code-reviewer.agent.md](.github/agents/code-reviewer.agent.md))
- Reviews Java code for quality, security, and best practices
- Identifies code smells and potential issues
- Checks for proper error handling and logging
- **Mandatory handoff to documentation-writer when gaps are found**

**README Specialist** ([.github/agents/readme-specialist.agent.md](.github/agents/readme-specialist.agent.md))
- Creates and improves README.md files
- Ensures documentation reflects current project state
- Documents architectural intent and non-goals
- Maintains consistency and scannability

### Available Subagents

**Documentation Writer** ([.github/agents/subagents/documentation-writer.agent.md](.github/agents/subagents/documentation-writer.agent.md))
- Triggered by code-reviewer when documentation gaps are found
- Creates API specifications, error handling strategies, and data models
- Ensures all architectural decisions are documented

### Agent Configuration

Agents are configured via YAML frontmatter in their `.agent.md` files and respect the governing rules in `agents.md`. They operate with restricted tool access (read, edit, search, agent invocation) and cannot modify code files—only documentation.

## Working Agreement for AI Agents & Humans

**For All Contributors:**
- If requirements are unclear, ask clarifying questions
- Do not guess business rules
- Propose changes before implementing them (discuss first)
- Explain trade-offs and assumptions
- Optimize for readability and maintainability over speed

**Specific Constraints for AI Agents:**
- **Respect the architecture** – Never bypass layering rules or use field injection
- **Use skills consistently** – All API work uses `api-design` skill; all tests use `test-generation` skill
- **Document your work** – Architectural decisions must be recorded
- **Hand off when needed** – Code reviewers must trigger documentation handoffs when gaps exist
- **Follow agents.md as law** – This is your source of truth for development practices
- **No casual refactoring** – Changes to core constraints (like constructor injection) require explicit approval
- **Fail safely** – Always create documentation for error scenarios; never assume error handling is trivial

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+

### Build & Run

```bash
# Build the project
mvn clean package

# Run tests
mvn test

# Start the service
mvn spring-boot:run
```

The service listens on `http://localhost:8080`

### Test the API

```bash
# Get order status
curl http://localhost:8080/orders/1

# Expected response (current stub):
CREATED
```

## Testing

### Current Test Coverage

- `OrderServiceTest` – Tests happy path (returns "CREATED" status)
- `InvoiceServiceTest` – Placeholder test file

### Test Structure

Tests mirror the main package structure:
```
src/test/java/com/example/agentic/
└── service/
    ├── OrderServiceTest.java
    └── InvoiceServiceTest.java
```

### Testing Principles

From `agents.md`:
- Write tests for all service-layer logic
- Tests should be behavior-focused, not implementation-focused
- Avoid excessive mocking
- Prefer readable tests over clever tests
- Test names should describe behavior clearly

**Current Gaps:**
- ❌ No error scenario testing
- ❌ No validation testing
- ❌ No controller layer tests
- ❌ No DAO layer tests

## Rules for Humans and AI Agents

### Rules For Code Changes

1. **Constructor Injection is Mandatory**
   - All dependencies must be injected via constructor
   - Field injection is forbidden
   - Reason: Enables testability, immutability, and clear dependency graphs

2. **Controllers Are Thin**
   - Controllers handle HTTP concerns only (routing, status codes, serialization)
   - All business logic lives in services
   - Reason: Separation of concerns, easier testing, cleaner code

3. **Service Layer Owns Business Logic**
   - No business decisions in controllers or DAOs
   - Services orchestrate DAOs and apply rules
   - Reason: Centralized, testable business logic; easier to understand and modify

4. **Fail Fast**
   - Validate inputs immediately upon entry
   - Throw exceptions with clear messages
   - Never swallow exceptions silently
   - Reason: Easier debugging, clearer error messages, faster problem resolution

5. **No Static Utility Classes**
   - Avoid `static` methods and `static` fields
   - Use composition and dependency injection instead
   - Reason: Testability, state management, dependency visibility

6. **Naming Matters**
   - Prefer clarity over brevity (e.g., `findOrderStatus` vs. `findOS`)
   - Method names should describe intent (e.g., `validateOrderId` not `check`)
   - Reason: Code is read 10x more than it's written; reduce cognitive load

### Rules for AI Agents

1. **Respect Architectural Boundaries**
   - Do not put business logic in controllers
   - Do not bypass layers with direct DAO access from controllers
   - Do not use field injection or static state

2. **Use Skills Consistently**
   - When creating APIs, use the `api-design` skill
   - When writing tests, use the `test-generation` skill
   - When defining DAOs, use the `dao-access` skill
   - Reference the skill files before implementing

3. **Document Your Work**
   - Add JavaDoc to public classes and methods
   - Record architectural decisions and trade-offs
   - Update this README if you change project intent or constraints

4. **Hand Off Appropriately**
   - Code reviewers: Trigger documentation-writer handoff when gaps exist
   - Never skip mandatory handoffs
   - Wait for documentation draft before completing code review

5. **Ask Before Changing Core Constraints**
   - Do not modify constructor injection requirement
   - Do not add field injection as an exception
   - Do not create utility classes
   - Changes to these require explicit approval

6. **Assume All Inputs Are Untrusted**
   - Always validate external input (HTTP params, request bodies)
   - Use the `validation` skill for complex validation logic
   - Return 400 Bad Request for invalid input, not 500 Internal Server Error

## Configuration

### Spring Boot Configuration

```yaml
# src/main/resources/application.yml
spring:
  application:
    name: simple-agentic
```

## Contributing

See [agents.md](agents.md) for the complete governance model and working agreements.

For documentation contributions, see [.github/agents/readme-specialist.agent.md](.github/agents/readme-specialist.agent.md).

## Project Governance

This project is governed by **agents.md**, which defines:
- Coding standards and architectural rules
- API design principles
- Testing and error handling expectations
- Security guidelines
- Working agreements for humans and AI agents

All development must align with `agents.md`. If you find a contradiction between this README and `agents.md`, **agents.md takes precedence**.

## License

This project is provided as-is for educational and reference purposes.

---

**Last Updated:** January 28, 2026  
**Project Type:** Reference Implementation (Agentic Engineering)
