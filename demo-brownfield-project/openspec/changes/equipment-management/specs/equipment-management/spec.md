# Spec: equipment-management

> 产出环节：环节 2（M 级起手）+ 环节 3（审批微调补充了 3 个 scenario）

## ADDED Requirements

### Requirement: Query Equipment List

The system SHALL allow authorized users to query the equipment list with filtering conditions.

#### Scenario: Query with filters

- **GIVEN** the user is on the equipment management page
- **WHEN** the user enters filter conditions (name, code, status) and triggers a search
- **THEN** the system returns a paginated list matching the conditions, using existing Response/PageResult format

#### Scenario: Query respects tenant isolation

- **WHEN** User A belongs to Tenant 1
- **THEN** User A can only see equipment belonging to Tenant 1

#### Scenario: Query with empty result

- **WHEN** no equipment matches the filter conditions
- **THEN** the system returns an empty list with total=0, not an error

---

### Requirement: Create Equipment

The system SHALL allow authorized users to create new equipment records.

#### Scenario: Create with valid data

- **WHEN** the user submits a valid creation form
- **THEN** the system persists the record with current tenant_id and returns success

#### Scenario: Create with missing required fields

- **WHEN** required fields are missing (e.g., equipment name is empty)
- **THEN** the system rejects with ServiceException and returns error via Response

#### Scenario: Create with duplicate equipment code

- **WHEN** the user submits a code that already exists within the same tenant
- **THEN** the system rejects with ServiceException("设备编号已存在")

---

### Requirement: Update Equipment

The system SHALL allow authorized users to update existing equipment records.

#### Scenario: Update with valid data

- **WHEN** the user modifies fields and submits
- **THEN** the system persists the update and returns success

#### Scenario: Update echoes current data

- **WHEN** the user opens the edit form
- **THEN** the form pre-fills with current equipment data

#### Scenario: Update respects tenant isolation

- **WHEN** User A from Tenant 1 attempts to update equipment belonging to Tenant 2
- **THEN** the system rejects the request

---

### Requirement: Brownfield Compatibility

#### Scenario: Preserve existing conventions

- **WHEN** equipment-management is implemented
- **THEN** it MUST NOT modify any existing interface, page, component, or database table

## Pending Confirmations

1. `equipment_status` 字典 key 是否在 DictService 中已存在 — **待确认**
2. `equipment_code` 唯一性约束范围：全局唯一 or 租户内唯一 — **待确认**

