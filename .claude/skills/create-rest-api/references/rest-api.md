Build reference.
# REST API Reference

## Core Principles
- APIs should be resource-oriented (use nouns, not verbs)
- Use standard HTTP methods correctly
- APIs must be stateless

## URL Design
- Use plural nouns for collections  
  Example: `/orders`, `/users`
- Use path parameters for resource identity  
  Example: `/orders/{id}`
- Avoid action verbs in URLs

## HTTP Methods
- `GET` – Retrieve data (no side effects)
- `POST` – Create new resources
- `PUT` – Replace existing resources
- `PATCH` – Partial updates
- `DELETE` – Remove resources

## Responses
- Use appropriate HTTP status codes
- Keep response bodies simple and consistent
- Do not expose internal domain or persistence details

## Errors
- Fail fast on invalid input
- Return meaningful error messages
- Do not leak internal stack traces or implementation details
