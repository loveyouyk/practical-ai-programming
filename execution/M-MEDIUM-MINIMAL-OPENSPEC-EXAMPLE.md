# M 级最小 OpenSpec 示例

> 文档性质：示例资产，不是当前仓库中的真实活跃 change。  
> 目标：给团队一个 `M-Medium` 场景下的最小 OpenSpec 参考，帮助区分“高风险完整案例”和“中型快车道案例”。

---

## 1. 为什么需要这个示例

当前主示例：

- `openspec/changes/project-management/`

更适合作为 `L / High Risk` 完整流程案例。  
为了避免团队把所有需求都按这个强度展开，这里补一个更接近 `M-Medium` 的最小 OpenSpec 示例。

建议先补看：

- `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`

---

## 2. 这个示例适用于什么场景

示例需求：

> 在现有后台系统中新增“项目分组管理”页面，支持列表查询、新增、修改。  
> 已知这是一个独立配置页，复用现有权限模式、列表页模式、表单模式和统一返回体，不改租户逻辑，不改导出语义，不改旧接口结构。

这类需求更符合 `M-Medium`，因为：

1. 有页面、有接口、有数据表，不能算 `S-Low`；
2. 边界相对清晰；
3. 不涉及权限模型改造；
4. 不涉及导出语义变化；
5. 不涉及高不确定性的跨模块历史链路。

---

## 3. 建议的最小流程

1. 最小探索
2. 最小 OpenSpec
3. 快速人工审批
4. 审批通过后开发
5. 验收矩阵
6. 结构化证据

如果在审批中发现：

1. 主数据来源不清；
2. 旧链路影响超预期；
3. 字段契约不清；
4. 风险高于原判断；

则升级到完整流程。

---

## 4. 示例 capability

建议 capability：

- `project-group-management`

---

## 5. 最小 `proposal.md` 示例

```md
## Why

当前系统缺少独立的项目分组配置能力，导致项目分组维护依赖人工配置或散落在其他模块中。业务希望在不改变现有权限、租户和页面框架的前提下，新增一个“项目分组管理”页面，支持列表查询、新增和修改。

## What Changes

- 新增 `project-group-management` capability
- 在前端新增项目分组管理页面，复用现有列表页和表单模式
- 在后端新增项目分组查询、新增、修改接口，沿用现有统一返回体和校验模式
- 增加对应 migration、测试和验收记录

## Impact

- 前端影响：新增一个独立配置页
- 后端影响：新增一个独立配置表及其 CRUD 接口
- 兼容影响：不改旧接口结构，不改导出语义，不改权限模型
```

---

## 6. 最小 `spec.md` 示例

```md
## ADDED Requirements

### Requirement: Query Project Group List

The system SHALL allow users to query project groups through the existing backend and frontend conventions.

#### Scenario: Query group list

- **WHEN** the user opens the project group management page
- **THEN** the system returns the group list using the project's existing response format

### Requirement: Create Or Update Project Group

The system SHALL allow users to create and update project groups while preserving existing validation and compatibility conventions.

#### Scenario: Create project group

- **WHEN** the user submits a valid create form
- **THEN** the system persists the new project group

#### Scenario: Update project group

- **WHEN** the user submits a valid update form
- **THEN** the system persists the updated project group

### Requirement: Brownfield Compatibility

The capability SHALL reuse the existing page layout, request wrapping, response wrapping, validation style, and permission pattern.

#### Scenario: Preserve existing conventions

- **WHEN** the capability is implemented
- **THEN** it MUST NOT replace shared components or change unrelated existing interfaces
```

---

## 7. 最小 `design.md` 示例

```md
## Context

This is a medium-sized brownfield change. The page is independent, the field semantics are clear, and there is no change to export semantics, tenant logic, or historical API contracts.

## Decisions

### Decision 1: Reuse existing list page pattern

The frontend reuses the project's existing list/filter/form page structure.

### Decision 2: Reuse existing backend layering

The backend reuses the existing Controller / Service / Repository layering, validation, and response conventions.

## API Contract

- `GET /api/project-groups`
- `POST /api/project-groups`
- `PUT /api/project-groups/{id}`

## Request Fields

- `groupName`
- `groupCode`
- `status`
- `remark`

## Compatibility

- No change to export semantics
- No change to tenant model
- No change to existing unrelated interfaces
```

---

## 7.5 Pending Confirmations（最小示例也必须有）

即使是 M 级最小 OpenSpec，也建议显式列出待确认项。不列待确认项容易导致开发者拍脑袋决策。

```md
## Pending Confirmations

1. `groupCode` 唯一性约束是全局唯一还是租户内唯一 — **待确认**
2. `status` 字段枚举是否已在 DictService 中存在 — **待确认**
```

---

## 8. 最小 `tasks.md` 示例

```md
## 1. Minimal Discovery

- [ ] 1.1 Confirm page entry, API style, validation pattern, and table naming convention
- [ ] 1.2 Record reuse points and compatibility assumptions

## 2. Backend

- [ ] 2.1 Add project group query/create/update capability
- [ ] 2.2 Add migration and backend tests

## 3. Frontend

- [ ] 3.1 Add project group page using existing list and form pattern
- [ ] 3.2 Add frontend tests and browser verification

## 4. Verification

- [ ] 4.1 Build acceptance matrix
- [ ] 4.2 Record structured verification evidence
```

---

## 9. 如何使用这个示例

推荐方式：

1. 把它当作 `M-Medium` 的最小骨架；
2. 按真实项目替换 capability、字段、接口路径和验证项；
3. 不要把这个示例误当成“永远不需要完整流程”的证明。

一句话说：

> 这个示例的价值，不是让团队跳过规格，而是让团队知道：中型需求可以有更轻但仍然受控的 OpenSpec 写法。  

