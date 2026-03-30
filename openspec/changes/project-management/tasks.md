# Tasks: project-management

## 1. Brownfield Discovery

- [ ] 1.1 Confirm existing list page pattern, query form pattern, dialog form pattern, export button pattern
- [ ] 1.2 Confirm existing Controller / Service / Mapper layering and unified response wrapper
- [ ] 1.3 Confirm existing pagination component (PageHelper / PageInfo)
- [ ] 1.4 Confirm existing export utility and file naming convention
- [ ] 1.5 Confirm `status` field source: dictionary service or fixed enum
- [ ] 1.6 Confirm `owner_id` master data query interface and response format
- [ ] 1.7 Confirm tenant context injection method (`TenantContext`)
- [ ] 1.8 Record all reuse points, compatibility assumptions, and pending confirmations

## 2. Backend List Query

- [ ] 2.1 Create `t_project` table migration script following existing naming convention
- [ ] 2.2 Create `Project` entity / model
- [ ] 2.3 Create `ProjectMapper` with list query (support filters: name, code, status, ownerId, createTime range)
- [ ] 2.4 Create `ProjectService` with paginated query logic, tenant filtering
- [ ] 2.5 Create `ProjectController` GET `/api/project-management/list` endpoint
- [ ] 2.6 Add backend unit tests for list query (happy path + empty result + tenant isolation)

## 3. Backend Create / Update

- [ ] 3.1 Add create method in `ProjectService` with validation and tenant context
- [ ] 3.2 Add update method in `ProjectService` with validation, tenant check, and existence check
- [ ] 3.3 Add POST `/api/project-management` and PUT `/api/project-management/{id}` endpoints
- [ ] 3.4 Add backend unit tests for create (valid + invalid + duplicate code) and update (valid + not found + wrong tenant)

## 4. Backend Export

- [ ] 4.1 Add export method in `ProjectService` reusing existing export utility
- [ ] 4.2 Add GET `/api/project-management/export` endpoint accepting same filters as list query
- [ ] 4.3 Verify exported file contains only filtered results (not full dataset)
- [ ] 4.4 Add backend test for export with filters

## 5. Frontend List Page

- [ ] 5.1 Add route `/project-management` to router configuration
- [ ] 5.2 Create project management list page component reusing existing list page layout
- [ ] 5.3 Implement query area with filters (name, code, status dropdown, owner remote search, date range)
- [ ] 5.4 Implement list table with columns matching design spec
- [ ] 5.5 Implement pagination reusing existing pagination component
- [ ] 5.6 Add API service layer for project management endpoints

## 6. Frontend Form

- [ ] 6.1 Create shared form dialog component for create and update
- [ ] 6.2 Implement form validation reusing existing validation patterns
- [ ] 6.3 Implement owner field with remote search component (reuse existing pattern)
- [ ] 6.4 Verify form echo-back on edit mode
- [ ] 6.5 Verify list refresh after create/update

## 7. Frontend Export

- [ ] 7.1 Add export button reusing existing export button component
- [ ] 7.2 Ensure export request carries current query form filter parameters
- [ ] 7.3 Implement loading feedback during export
- [ ] 7.4 Verify exported file matches current filter conditions (not full data)

## 8. Integration

- [ ] 8.1 Front-to-back integration: list query with filters
- [ ] 8.2 Front-to-back integration: create project → list refresh
- [ ] 8.3 Front-to-back integration: update project → data echo-back → list refresh
- [ ] 8.4 Front-to-back integration: export with active filters
- [ ] 8.5 Verify tenant isolation: different tenant users see different data
- [ ] 8.6 Verify permission: unauthorized users cannot access project management page/API
- [ ] 8.7 Verify old pages and old APIs are not broken

## 9. Verification

- [ ] 9.1 Build acceptance matrix mapping each requirement to evidence
- [ ] 9.2 Record structured verification evidence (command, environment, result, conclusion, residual risk)
- [ ] 9.3 Run `/opsx-verify` or equivalent manual verification gate
- [ ] 9.4 Confirm all requirements have "联调通过" status with evidence

## 10. Release Preparation

- [ ] 10.1 Confirm migration script is ready and reversible
- [ ] 10.2 Confirm menu and button permission configuration
- [ ] 10.3 Prepare release checklist (backend deploy, frontend deploy, migration, health check)
- [ ] 10.4 Define rollback trigger conditions
- [ ] 10.5 Define post-release monitoring items (error rate, old API stability, tenant data isolation)

