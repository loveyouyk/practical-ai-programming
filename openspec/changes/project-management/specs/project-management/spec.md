# Spec: project-management

## ADDED Requirements

### Requirement: Query Project List

The system SHALL allow authorized users to query the project list with filtering conditions, using the project's existing pagination and response conventions.

#### Scenario: Query with filters

- **GIVEN** the user is on the project management page
- **WHEN** the user enters filter conditions (project name, project code, status, owner, creation date range) and triggers a search
- **THEN** the system returns a paginated project list matching the conditions, using the existing unified response format

#### Scenario: Query with empty filters

- **WHEN** the user triggers a search without any filter conditions
- **THEN** the system returns all projects within the user's tenant scope, paginated

#### Scenario: Query respects tenant isolation

- **WHEN** User A belongs to Tenant 1 and User B belongs to Tenant 2
- **THEN** User A can only see projects belonging to Tenant 1, and User B can only see projects belonging to Tenant 2

---

### Requirement: Create Project

The system SHALL allow authorized users to create new projects, validating required fields and persisting data with tenant context.

#### Scenario: Create with valid data

- **WHEN** the user submits a valid project creation form with all required fields
- **THEN** the system persists the new project record with the current tenant ID and returns a success response

#### Scenario: Create with missing required fields

- **WHEN** the user submits a creation form with missing required fields (e.g., project name is empty)
- **THEN** the system rejects the request and returns a validation error using the existing error response format

#### Scenario: Create preserves existing validation conventions

- **WHEN** the project creation capability is implemented
- **THEN** it MUST use the existing validation framework (e.g., `@Validated`, `ServiceException`) and NOT introduce a separate validation mechanism

---

### Requirement: Update Project

The system SHALL allow authorized users to update existing projects, with proper field echo-back and tenant-scoped data access.

#### Scenario: Update with valid data

- **WHEN** the user modifies project fields and submits
- **THEN** the system persists the updated record and returns a success response

#### Scenario: Update echoes current data

- **WHEN** the user opens the edit form for an existing project
- **THEN** the form pre-fills with the current project data

#### Scenario: Update respects tenant isolation

- **WHEN** User A from Tenant 1 attempts to update a project belonging to Tenant 2
- **THEN** the system rejects the request

---

### Requirement: Export Project List

The system SHALL allow authorized users to export the currently filtered project list, reusing the existing export capability.

#### Scenario: Export with current filters

- **WHEN** the user clicks the export button while filter conditions are active
- **THEN** the system exports only the projects matching the current filters, NOT the full project list

#### Scenario: Export reuses existing export tool

- **WHEN** the export capability is implemented
- **THEN** it MUST reuse the existing backend export service/tool and NOT create a separate export mechanism

#### Scenario: Export file naming

- **WHEN** the export completes
- **THEN** the exported file follows the existing file naming convention of the project

---

### Requirement: Brownfield Compatibility

The capability SHALL preserve all existing system behaviors, conventions, and shared components.

#### Scenario: Preserve existing API conventions

- **WHEN** the project management APIs are implemented
- **THEN** they MUST use the existing unified response wrapper, error format, and pagination parameters

#### Scenario: Preserve existing frontend patterns

- **WHEN** the project management page is implemented
- **THEN** it MUST reuse existing list page structure, query form pattern, dialog/drawer form pattern, and export button pattern

#### Scenario: No unrelated changes

- **WHEN** the project management capability is delivered
- **THEN** no unrelated existing interfaces, pages, components, or database tables SHALL be modified

