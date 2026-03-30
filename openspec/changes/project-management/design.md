# Design: project-management

## Context

This is a brownfield change in an existing enterprise backend system. The system uses Java 17 + Spring Boot 3.x + MyBatis-Plus for the backend and Vue 3 + Vite for the frontend. The project management module is a new capability that must integrate seamlessly with existing conventions.

## Decisions

### Decision 1: Reuse existing backend layering

The backend follows the existing `Controller → Service → Mapper` layering. No new abstraction layers are introduced.

### Decision 2: Reuse unified response and exception handling

All API responses use the existing `Response<T>` wrapper. All business errors use `ServiceException`. No alternative error handling is introduced.

### Decision 3: Reuse existing pagination

List query uses `PageHelper` with existing `PageInfo` wrapping, consistent with other list APIs.

### Decision 4: Reuse existing export capability

Export uses the existing backend export service (e.g., EasyExcel + existing export utility). The export endpoint accepts the same filter parameters as the list query endpoint.

### Decision 5: Reuse existing frontend patterns

The frontend page reuses the existing list page template, query form component, dialog-based form, and export button with loading feedback.

---

## API Contract

### List Query

```
GET /api/project-management/list
```

**Query Parameters:**

| Field | Type | Required | Description |
|---|---|---|---|
| projectName | String | No | 模糊匹配 |
| projectCode | String | No | 精确匹配 |
| status | Integer | No | 项目状态枚举 |
| ownerId | Long | No | 负责人 ID |
| createTimeStart | String | No | 创建时间起（yyyy-MM-dd） |
| createTimeEnd | String | No | 创建时间止（yyyy-MM-dd） |
| pageNum | Integer | Yes | 页码 |
| pageSize | Integer | Yes | 每页条数 |

**Response:**

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "projectName": "示例项目",
        "projectCode": "PRJ-2026-001",
        "status": 1,
        "statusName": "进行中",
        "ownerId": 100,
        "ownerName": "张三",
        "customerId": 200,
        "customerName": "示例客户",
        "startDate": "2026-01-15",
        "endDate": "2026-06-30",
        "remark": "备注",
        "createTime": "2026-01-10 09:00:00",
        "updateTime": "2026-03-20 14:30:00"
      }
    ],
    "total": 50,
    "pageNum": 1,
    "pageSize": 10
  }
}
```

### Create

```
POST /api/project-management
```

**Request Body:**

| Field | Type | Required | Description |
|---|---|---|---|
| projectName | String | Yes | 项目名称 |
| projectCode | String | Yes | 项目编号 |
| status | Integer | Yes | 状态 |
| ownerId | Long | Yes | 负责人 ID |
| customerId | Long | No | 客户 ID |
| startDate | String | No | 开始日期 |
| endDate | String | No | 结束日期 |
| remark | String | No | 备注 |

### Update

```
PUT /api/project-management/{id}
```

Request Body 同 Create，额外要求 `id` 路径参数。

### Export

```
GET /api/project-management/export
```

Query Parameters 同 List Query（除 pageNum/pageSize 外），返回文件流。

---

## Data Model

### Table: `t_project`

| Column | Type | Nullable | Description |
|---|---|---|---|
| id | BIGINT | PK | 主键 |
| tenant_id | BIGINT | NOT NULL | 租户 ID |
| project_name | VARCHAR(200) | NOT NULL | 项目名称 |
| project_code | VARCHAR(50) | NOT NULL | 项目编号（业务唯一） |
| status | TINYINT | NOT NULL | 状态（待确认枚举来源：字典服务 or 固定枚举） |
| owner_id | BIGINT | NOT NULL | 负责人 ID（来自主数据服务） |
| customer_id | BIGINT | NULL | 客户 ID |
| start_date | DATE | NULL | 开始日期 |
| end_date | DATE | NULL | 结束日期 |
| remark | VARCHAR(500) | NULL | 备注 |
| create_time | DATETIME | NOT NULL | 创建时间 |
| update_time | DATETIME | NOT NULL | 更新时间 |
| create_by | BIGINT | NULL | 创建人 |
| update_by | BIGINT | NULL | 更新人 |
| deleted | TINYINT | NOT NULL DEFAULT 0 | 逻辑删除 |

**索引建议：**
- `idx_tenant_id` ON (`tenant_id`)
- `idx_project_code` ON (`tenant_id`, `project_code`) UNIQUE
- `idx_owner_id` ON (`owner_id`)
- `idx_create_time` ON (`create_time`)

---

## Frontend Design

### Page Entry

- 路由：`/project-management`（挂载到现有菜单系统）
- 页面组件：复用现有列表页 layout

### Query Area

- 筛选字段：项目名称、项目编号、状态（下拉）、负责人（下拉/远程搜索）、创建时间（日期范围）
- 复用现有 query form 组件和重置逻辑

### List Table

- 列：项目名称、项目编号、状态、负责人、客户、开始日期、结束日期、创建时间、操作（编辑）
- 复用现有 table 组件和分页组件

### Form Dialog

- 新增和修改共用同一个 dialog 组件
- 表单校验复用现有 validation 模式
- 负责人字段使用远程搜索组件（复用现有模式）

### Export Button

- 复用现有导出按钮组件
- 点击后按当前筛选条件导出
- 显示 loading 状态直到下载完成

---

## Compatibility Strategy

1. 所有新增接口使用独立的 URL path `/api/project-management`，不影响现有接口
2. 前端新增独立路由和页面，不修改现有页面组件
3. 数据库新增独立表 `t_project`，不修改现有表结构
4. 权限新增独立的菜单和按钮权限配置，不修改现有权限模型

## Rollback Strategy

1. 后端：删除 ProjectController/Service/Mapper 相关代码，回滚 migration
2. 前端：删除项目管理路由和页面组件
3. 权限：删除对应菜单和按钮权限配置
4. 回滚操作不影响任何现有功能

## Pending Confirmations

1. `status` 字段枚举来源：字典服务 vs 固定枚举 — **待确认**
2. `owner_id` 主数据查询接口地址和响应格式 — **待确认**
3. 导出文件命名规范是否有全局约定 — **待确认**

