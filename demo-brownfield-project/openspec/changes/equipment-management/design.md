# Design: equipment-management

> 产出环节：环节 2 + 环节 7（变更回写：删除 statusName，修正 pageSize）

## Context

M-Medium brownfield change。独立配置页，不改权限模型、租户逻辑和导出语义。

## Decisions

### Decision 1: Reuse existing backend layering
Controller → Service → ServiceImpl，复用 Response<T>、ServiceException、TenantContext、PageResult。

### Decision 2: Backend does NOT translate status field
**（变更回写产物）** 历史约定：后端返回原始 int 值，前端用 DictService 本地翻译。严禁在响应中返回 statusName。

### Decision 3: Default pageSize = 20
**（变更回写产物）** 与 BaseTable 默认值对齐，不用 10。

### Decision 4: Reuse existing frontend patterns
SearchForm + BaseTable + FormDialog，注意各组件 QUIRK（见 QUIRK 登记簿）。

## API Contract

### List Query

```
GET /api/equipment/list
```

| Param | Type | Required | Description |
|---|---|---|---|
| equipmentName | String | No | 模糊匹配 |
| equipmentCode | String | No | 精确匹配 |
| status | Integer | No | 状态值 |
| pageNum | Integer | Yes | 页码 |
| pageSize | Integer | Yes | 每页条数（默认 20） |

Response: `Response<PageResult<Equipment>>`

**注意：响应中只包含 status（int），不包含 statusName。前端翻译。**

### Create

```
POST /api/equipment
```

| Field | Type | Required |
|---|---|---|
| equipmentName | String | Yes |
| equipmentCode | String | Yes |
| status | Integer | Yes |
| location | String | No |
| remark | String | No |

### Update

```
PUT /api/equipment/{id}
```

Body 同 Create。

## Data Model

### Table: `t_equipment`

| Column | Type | Nullable |
|---|---|---|
| id | BIGINT | PK |
| tenant_id | BIGINT | NOT NULL |
| equipment_name | VARCHAR(200) | NOT NULL |
| equipment_code | VARCHAR(50) | NOT NULL |
| status | TINYINT | NOT NULL |
| location | VARCHAR(200) | NULL |
| remark | VARCHAR(500) | NULL |
| create_time | DATETIME | NOT NULL |
| update_time | DATETIME | NOT NULL |
| create_by | BIGINT | NULL |
| deleted | TINYINT | NOT NULL DEFAULT 0 |

Unique: (`tenant_id`, `equipment_code`)（**待确认：是否租户内唯一**）

## Pending Confirmations

1. `equipment_status` 字典 — **待确认**
2. `equipment_code` 唯一性范围 — **待确认**（当前设计按租户内唯一）

