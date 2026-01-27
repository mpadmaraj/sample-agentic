# API Reference Quick Guide

Quick reference for the Order Management API specification.

---

## Endpoint Summary

### MVP (Phase 1)

| Method | Endpoint | Purpose | Status Codes |
|--------|----------|---------|---|
| GET | `/orders/{id}` | Retrieve order details | 200, 400, 404, 500 |

### Future Endpoints (Not MVP)

| Method | Endpoint | Purpose | Status Codes |
|--------|----------|---------|---|
| POST | `/orders` | Create new order | 201, 400, 500 |
| PUT | `/orders/{id}` | Update order status | 200, 400, 404, 500 |
| DELETE | `/orders/{id}` | Cancel order | 204, 400, 404, 500 |
| GET | `/orders` | List orders (with filtering) | 200, 400, 500 |

---

## GET /orders/{id}

**Request**
```
GET /orders/{id}
Content-Type: application/json
```

**Path Parameters**
- `id` (Long, required): Positive order identifier

**Success Response (200)**
```json
{
  "id": 12345,
  "status": "PROCESSING",
  "customerId": 7891,
  "customerName": "John Smith",
  "items": [
    {
      "itemId": 1,
      "name": "Widget A",
      "quantity": 2,
      "unitPrice": 29.99,
      "subtotal": 59.98
    }
  ],
  "total": 109.97,
  "createdAt": "2026-01-20T14:30:00Z",
  "updatedAt": "2026-01-25T09:15:00Z",
  "shippedAt": null,
  "deliveredAt": null
}
```

**Error Responses**

404 Not Found:
```json
{
  "error": "NOT_FOUND",
  "message": "Order with id 999 not found",
  "timestamp": "2026-01-27T10:00:00Z",
  "path": "/orders/999"
}
```

400 Bad Request (invalid ID):
```json
{
  "error": "BAD_REQUEST",
  "message": "Order id must be a positive integer",
  "timestamp": "2026-01-27T10:00:00Z",
  "path": "/orders/abc"
}
```

500 Internal Server Error:
```json
{
  "error": "INTERNAL_SERVER_ERROR",
  "message": "An unexpected error occurred while retrieving the order",
  "timestamp": "2026-01-27T10:00:00Z",
  "path": "/orders/12345"
}
```

---

## Order Status Enum

Valid values: `CREATED`, `PROCESSING`, `SHIPPED`, `DELIVERED`, `CANCELLED`

**State Transitions**
```
CREATED ──→ PROCESSING ──→ SHIPPED ──→ DELIVERED
  ↓           ↓
  └─→ CANCELLED
```

| From | To | Allowed |
|------|----|----|
| CREATED | PROCESSING | ✓ |
| CREATED | CANCELLED | ✓ |
| PROCESSING | SHIPPED | ✓ |
| PROCESSING | CANCELLED | ✓ |
| SHIPPED | DELIVERED | ✓ |
| SHIPPED | CANCELLED | ✗ |
| DELIVERED | * | ✗ (terminal) |
| CANCELLED | * | ✗ (terminal) |

---

## Data Model Overview

### Order

| Field | Type | Required | Constraints |
|-------|------|----------|-------------|
| `id` | Long | Yes | > 0, auto-generated |
| `status` | Enum | Yes | CREATED, PROCESSING, SHIPPED, DELIVERED, CANCELLED |
| `customerId` | Long | Yes | > 0 |
| `customerName` | String | Yes | Max 255 chars, non-empty |
| `items` | List | Yes | At least 1 item |
| `total` | BigDecimal | Yes | > 0, DECIMAL(10,2) |
| `createdAt` | LocalDateTime | Yes | UTC timestamp |
| `updatedAt` | LocalDateTime | Yes | UTC timestamp |
| `shippedAt` | LocalDateTime | No | UTC or null |
| `deliveredAt` | LocalDateTime | No | UTC or null |

### OrderItem

| Field | Type | Required | Constraints |
|-------|------|----------|-------------|
| `itemId` | Long | Yes | > 0 |
| `name` | String | Yes | Max 255 chars |
| `quantity` | Integer | Yes | > 0 |
| `unitPrice` | BigDecimal | Yes | > 0, DECIMAL(10,2) |
| `subtotal` | BigDecimal | Yes | quantity × unitPrice |

---

## Exception Mapping

| Exception | HTTP Status | Use Case |
|-----------|---|---|
| OrderNotFoundException | 404 | Order doesn't exist |
| InvalidOrderException | 400 | Invalid input (ID format, validation) |
| InvalidOrderStateException | 400 | Invalid state transition |
| OrderOperationException | 400 | Business logic violation |
| Any unhandled | 500 | Unexpected server error |

---

## Error Response Structure

All errors follow this format:

```json
{
  "error": "<ERROR_CODE>",
  "message": "<human-readable message>",
  "timestamp": "<ISO8601 UTC>",
  "path": "<request path>",
  "validationErrors": [
    {
      "field": "<field name>",
      "message": "<validation error>"
    }
  ]
}
```

**Error Codes**:
- `NOT_FOUND` (404)
- `BAD_REQUEST` (400)
- `INTERNAL_SERVER_ERROR` (500)

---

## Input Validation Rules

### Order ID
- ✓ Must be positive integer (> 0)
- ✓ Must be numeric
- ✓ Cannot be null
- ✓ Must fit in Long type

### Order Data
- ✓ Status must be valid enum value
- ✓ Total must be > 0
- ✓ Items list cannot be empty
- ✓ Quantity must be > 0
- ✓ Unit price must be > 0

---

## HTTP Status Codes

| Code | Reason | When |
|------|--------|------|
| 200 | OK | Successful GET/PUT |
| 201 | Created | Successful POST (future) |
| 204 | No Content | Successful DELETE (future) |
| 400 | Bad Request | Invalid input or business rule violation |
| 404 | Not Found | Order doesn't exist |
| 500 | Server Error | Unexpected server-side failure |

---

## cURL Examples

### Retrieve Order (Success)
```bash
curl -X GET http://localhost:8080/orders/1 \
  -H "Content-Type: application/json"
```

### Retrieve Order (Not Found)
```bash
curl -X GET http://localhost:8080/orders/999 \
  -H "Content-Type: application/json"
```

### Retrieve Order (Invalid ID - Non-numeric)
```bash
curl -X GET http://localhost:8080/orders/abc \
  -H "Content-Type: application/json"
```

### Retrieve Order (Invalid ID - Negative)
```bash
curl -X GET http://localhost:8080/orders/-5 \
  -H "Content-Type: application/json"
```

---

## Testing Checklist

### Unit Tests (OrderServiceTest)
- [ ] Returns Order when exists
- [ ] Returns correct status
- [ ] Returns correct items and totals
- [ ] Throws OrderNotFoundException when not found
- [ ] Throws InvalidOrderException for negative ID
- [ ] Throws InvalidOrderException for zero ID
- [ ] Throws InvalidOrderException for null ID

### Integration Tests (OrderControllerIntegrationTest)
- [ ] GET /orders/1 returns 200 with OrderResponse
- [ ] GET /orders/999 returns 404
- [ ] GET /orders/abc returns 400
- [ ] GET /orders/-1 returns 400
- [ ] Error response includes all required fields

---

## Database Schema (Phase 2)

```sql
CREATE TABLE orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  status VARCHAR(50) NOT NULL,
  customer_id BIGINT NOT NULL,
  customer_name VARCHAR(255) NOT NULL,
  total DECIMAL(10, 2) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  shipped_at TIMESTAMP NULL,
  delivered_at TIMESTAMP NULL,
  cancelled_at TIMESTAMP NULL
);

CREATE TABLE order_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  quantity INT NOT NULL,
  unit_price DECIMAL(10, 2) NOT NULL,
  subtotal DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE INDEX idx_orders_customer ON orders(customer_id);
CREATE INDEX idx_order_items_order ON order_items(order_id);
```

---

## File Organization

```
src/main/java/com/example/agentic/
├── controller/
│   └── OrderController.java
├── service/
│   ├── OrderService.java
│   └── OrderMapper.java
├── dao/
│   └── OrderDao.java
├── model/
│   ├── Order.java
│   ├── OrderItem.java
│   └── OrderStatus.java
├── dto/
│   ├── OrderResponse.java
│   ├── OrderItemResponse.java
│   └── ErrorResponse.java
├── exception/
│   ├── OrderServiceException.java
│   ├── OrderNotFoundException.java
│   ├── InvalidOrderException.java
│   ├── InvalidOrderStateException.java
│   ├── OrderOperationException.java
│   └── GlobalExceptionHandler.java
└── Application.java

docs/
├── API_SPECIFICATION.md (this project's full API spec)
├── IMPLEMENTATION_GUIDE.md (step-by-step implementation)
├── TESTING_STRATEGY.md (comprehensive testing approach)
└── API_REFERENCE.md (this file - quick reference)
```

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2026-01-27 | Initial API specification and documentation |

---

## Related Documentation

- [API Specification](API_SPECIFICATION.md) - Complete specification with all details
- [Implementation Guide](IMPLEMENTATION_GUIDE.md) - Step-by-step implementation instructions
- [Testing Strategy](TESTING_STRATEGY.md) - Detailed testing approach and test cases
- [agents.md](../agents.md) - Agentic engineering guidelines for this project

---

## Support

For questions or clarifications:
1. Refer to full [API_SPECIFICATION.md](API_SPECIFICATION.md)
2. Check [TESTING_STRATEGY.md](TESTING_STRATEGY.md) for test examples
3. See [IMPLEMENTATION_GUIDE.md](IMPLEMENTATION_GUIDE.md) for implementation details
4. Review [agents.md](../agents.md) for architectural standards

