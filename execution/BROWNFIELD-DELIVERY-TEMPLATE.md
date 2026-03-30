# 棕地项目统一交付模板

> 用途：把一个棕地项目功能从“项目巡查”推进到“上线归档”时，沉淀成一份统一交付文档。  
> 设计原则：只保留真实会用到的产物，不堆砌空泛章节。  
> 建议用法：每个真实功能变更复制一份，按实际情况填写。

如果你不确定当前环境能否直接执行 `/opsx-*` 或 Superpowers，请先补看：

- `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

---

## 1. 基本信息

```md
# <功能名称> 交付文档

- 项目名称：
- 变更类型：新增功能 / 升级改造 / 兼容性修复 / 安全加固
- 业务域：
- 负责人：
- 相关团队：
- 计划上线时间：
- 关联 OpenSpec change：
- 关联实施计划：
- 关联功能分支：
- 关联 PR：
```

---

## 2. 变更目标

只写这次交付真正要达成的业务结果，不写实现细节。

```md
## 变更目标

### 为什么做
- 

### 目标行为
- 

### 非目标
- 
```

---

## 2.1 工具环境与执行方式

这部分用于避免“文档里写了 `/opsx-*`，但团队实际上没有统一执行方式”。

```md
## 工具环境与执行方式

### 当前环境
- OpenSpec / `/opsx-*`：待确认 / 可直接执行 / 部分可用 / 不可用
- Superpowers：待确认 / 可直接执行 / 部分可用 / 不可用

### 本次采用方式
- Explore：`/opsx-explore` / 人工 fallback
- Verify：`/opsx-verify` / 人工 fallback
- Sync：`/opsx-sync` / 人工判断
- Archive：`/opsx-archive` / 人工判断

### fallback 依据文档
- `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
```

---

## 3. 当前系统现状摘要

这部分来自棕地项目巡查，不要凭印象写。

```md
## 当前系统现状摘要

### 前端现状
- 页面入口：
- 相关路由：
- 相关组件：
- API 调用入口：
- 现有交互约定：
- 关键证据路径：

### 后端现状
- Controller 入口：
- Service 主链：
- Repository / Mapper：
- 相关表：
- 统一返回体 / 异常 / 权限 / 租户约定：
- 关键证据路径：

### 测试现状
- 已有单测：
- 已有集成测试：
- 已有前端测试：
- 已有 smoke test：
- 已掌握的请求/响应样例：

### 发布现状
- 配置方式：
- migration 方式：
- 监控方式：
- 回滚方式：
```

---

## 4. 规则基线摘要

这部分不是重新发明规则，而是引用你已经提炼好的项目规则基线，只摘录与本次功能直接相关的部分。

```md
## 规则基线摘要

### 项目级规则
- 

### 后端规则
- 

### 前端规则
- 

### 本次变更必须额外强调的约束
- 
```

建议在这里同时附上引用：

```md
### 关联规则文档
- `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
- `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
- `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
- `.cursor/rules/*.mdc`
```

---

## 5. OpenSpec 产物清单

这部分只做链接和摘要，不把 OpenSpec 内容整段复制过来。

建议优先参考当前主示例 change：

- `openspec/changes/project-management/`

```md
## OpenSpec 产物清单

- `proposal.md`：
  - 作用：
  - 路径：

- `spec.md`：
  - capability：
  - 核心 requirements：
  - 路径：

- `design.md`：
  - 关键设计决策：
  - 路径：

- `tasks.md`：
  - 核心任务分组：
  - 路径：
```

---

## 6. 实施计划摘要

这里写的是 Superpowers 的实施计划摘要，不是再写一遍 tasks。

```md
## 实施计划摘要

### 任务顺序
1. 
2. 
3. 

### 后端改动范围
- 

### 前端改动范围
- 

### 联调重点
- 

### 关键风险
- 

### 计划与 OpenSpec 是否一致
- 一致 / 不一致
- 若不一致，已回写的 artifact：
```

如果已经有计划文件，建议补链接：

```md
### 关联实施计划
- `docs/superpowers/plans/<date>-<feature>.md`
```

---

## 7. 实现结果摘要

这部分写“做了什么”，但只写结果，不写完整改动日志。

```md
## 实现结果摘要

### 后端
- 

### 前端
- 

### 兼容性处理
- 

### 字段级契约与证据
- 关键请求样例：
- 关键响应样例：
- 错误响应样例：

### 未纳入本次范围
- 
```

---

## 8. Git / Commit / Review / PR 记录

这部分用于把开发结果和评审过程沉淀下来，避免“代码上线了，但过程不可追踪”。

```md
## Git / Commit / Review / PR 记录

### 分支信息
- 功能分支：
- 基线分支：

### Commit 摘要
- commit 1：
- commit 2：
- commit 3：

### 代码评审
- 是否完成 code review：
- 主要评审意见：
- 是否已修复：

### PR 信息
- PR 标题：
- PR 链接：
- PR Summary：
- Test Plan：

### 合并结果
- 是否已合并：
- 合并时间：
- 合并后是否再次验证：
```

---

## 9. 测试与验证结果

这一部分要实事求是，不能写“已验证”但没有证据。

```md
## 测试与验证结果

### 后端测试
- 执行命令：
- 结果：
- 备注：

### 前端测试
- 执行命令：
- 结果：
- 备注：

### 联调验证
- 核心验证链路：
- 结果：

### 权限 / 租户 / 主数据验证
- 覆盖场景：
- 结果：

### 验收矩阵
- 路径：
- 结论：具备上线评审条件 / 不具备上线评审条件 / 部分达标

### 旧链路回归
- 回归范围：
- 结果：

### OpenSpec 验证
- 是否执行 `/opsx-verify`：
- 结果摘要：
- 若未执行，采用的 fallback 门禁：
```

建议保留这种真实表达：

```md
### 尚未完成或未执行项
- [ ] 
- 原因：
- 风险：
```

---

## 10. 发布准备清单

这部分是最容易“看起来有，实际上不能执行”的地方，所以模板尽量具体。

```md
## 发布准备清单

### 配置项
- 

### 数据库变更
- migration 文件：
- 是否可回滚：
- 备份要求：

### 后端发布项
- 构建产物：
- 发布步骤：
- 健康检查：

### 前端发布项
- 构建产物：
- 发布步骤：
- 页面检查：

### 监控项
- 

### 回滚点
- 

### 旧链路观察项
- 
```

---

## 11. 上线执行记录

这一段不是“计划”，而是实际上线时的记录。

```md
## 上线执行记录

### 发布时间
- 

### 实际执行步骤
1. 
2. 
3. 

### 发布后 smoke test
- 

### 发布后监控观察
- 

### 是否触发回滚
- 否 / 是
- 若是，原因：
```

---

## 12. 结论与归档建议

这部分用于交付收尾。

```md
## 结论与归档建议

### 是否达到交付标准
- 是 / 否

### 若达到标准
- 是否可归档：
- 是否需要 `/opsx-sync`：
- 是否需要补充规则基线：
- 当前是否具备证据化验收闭环：

### 若未达到标准
- 阻塞项：
- 下一步动作：
```

---

## 13. 最小可用填写示例

下面给一个最小可用例子，避免模板看起来完整但不会填。  
注意：这只是填写示例，不代表任何项目在未经验证前就天然具备同样结论。

这个示例对应的参考 change 路径为：

- `openspec/changes/project-management/`

```md
# 项目管理模块交付文档

- 项目名称：deep-charge-operatorback
- 变更类型：新增功能
- 业务域：项目管理
- 负责人：<姓名>
- 关联 OpenSpec change：`openspec/changes/project-management/`
- 关联功能分支：`feature/project-management`

## 工具环境与执行方式

### 当前环境
- OpenSpec / `/opsx-*`：待确认
- Superpowers：待确认

### 本次采用方式
- Explore：`/opsx-explore`
- Verify：`/opsx-verify`

### fallback 依据文档
- `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

## 变更目标

### 为什么做
- 在现有历史系统中补充项目管理模块能力，并保持旧链路兼容。

### 目标行为
- 支持列表查询、新增、修改和按当前筛选条件导出项目数据。

### 非目标
- 不做项目审批流、日志中心或无关大规模重构。

## 当前系统现状摘要

### 前端现状
- 系统已有相近业务页面和导出能力，但缺少独立的项目管理模块闭环。
- 关键证据路径：`src/views/...`、`src/api/...`

### 后端现状
- 已有相近查询链路与主数据依赖，但没有完整的项目管理查询/新增/修改/导出闭环。
- 关键证据路径：`src/main/java/...`

### 测试现状
- 后端已有 JUnit 5 测试模式。

## 规则基线摘要

### 项目级规则
- 先理解现状，再设计和实现。

### 后端规则
- 遵循既有 Controller / Service / Mapper 分层。

### 前端规则
- 复用既有路由、页面、组件、API 封装和消息提示模式。

### 本次变更必须额外强调的约束
- 权限、租户、主数据和导出语义必须有证据化验证。

## OpenSpec 产物清单

- `proposal.md`：说明为什么在历史系统中增量补齐项目管理模块能力
- `spec.md`：定义 `project-management` requirements 和兼容性场景
- `design.md`：说明前后端复用边界和回滚策略
- `tasks.md`：拆分调研、后端、前端、联调、验证、发布准备

## Git / Commit / Review / PR 记录

### 分支信息
- 功能分支：`feature/project-management`
- 基线分支：`main`

### Commit 摘要
- commit 1：新增项目列表查询与新增/修改后端接口
- commit 2：新增项目前端列表页、查询区和表单
- commit 3：补充导出、联调修复与测试

## 测试与验证结果

### 后端测试
- 执行命令：`mvn test`
- 结果：通过

### 联调验证
- 核心验证链路：列表查询、新增、修改、导出
- 结果：主流程通过

### 权限 / 租户 / 主数据验证
- 覆盖场景：查询权限、编辑权限、租户范围、负责人主数据校验
- 结果：通过

### 验收矩阵
- 路径：`execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
- 结论：部分达标
```

---

## 14. 已确认版填写示例（完整闭环）

下面给一个"已经走完全流程"后的填写示例，帮助团队理解模板填写到最终状态应该是什么样子。  
注意：这是虚构的理想状态示例，用于展示各字段的预期填写深度。

```md
# 项目管理模块交付文档

- 项目名称：deep-charge-operatorback
- 变更类型：新增功能
- 业务域：项目管理
- 负责人：张三
- 相关团队：后端组（张三、李四）、前端组（王五）
- 计划上线时间：2026-04-10
- 关联 OpenSpec change：`openspec/changes/project-management/`
- 关联实施计划：`docs/superpowers/plans/2026-03-25-project-management.md`
- 关联功能分支：`feature/project-management`
- 关联 PR：`#142`

## 工具环境与执行方式

### 当前环境
- OpenSpec / `/opsx-*`：可直接执行
- Superpowers：可直接执行

### 本次采用方式
- Explore：`/opsx-explore`
- Verify：`/opsx-verify`
- Sync：人工判断（本次 change 尚未稳定到同步主 specs）
- Archive：人工判断

### fallback 依据文档
- `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

## 变更目标

### 为什么做
- 在现有历史系统中补充项目管理模块能力，运营团队需要独立入口管理项目基本信息。

### 目标行为
- 支持按名称/编号/状态/负责人/创建时间筛选的列表查询
- 支持新增项目（含表单校验）
- 支持修改项目（含数据回显）
- 支持按当前筛选条件导出项目列表

### 非目标
- 不做项目审批流、日志中心或无关大规模重构
- 不修改现有权限模型结构
- 不修改现有导出语义

## 当前系统现状摘要

### 前端现状
- 页面入口：`src/views/business/` 下的相近业务页面
- 相关路由：`src/router/modules/business.js`
- 相关组件：`SearchForm`、`BaseTable`、`FormDialog`、`ExportButton`
- API 调用入口：`src/api/business/`
- 现有交互约定：查询区 + 列表 + Dialog 表单 + 导出按钮
- 关键证据路径：`src/views/business/contract/index.vue`（最相近的参考页面）

### 后端现状
- Controller 入口：`com.lscharge.controller.business`
- Service 主链：`com.lscharge.service.business`
- Repository / Mapper：`com.lscharge.mapper.business`
- 相关表：`t_contract`、`t_customer`（结构参考）
- 统一返回体：`Response<T>`、异常 `ServiceException`、租户 `TenantContext`
- 关键证据路径：`ContractController.java`、`ContractServiceImpl.java`

### 测试现状
- 已有单测：JUnit 5 + Mockito，覆盖 Service 层
- 已有集成测试：无（本项目历史不含集成测试）
- 已有前端测试：无（本项目历史不含前端自动化测试，依赖手动验证）
- 已掌握的请求/响应样例：见 design.md API Contract 章节

### 发布现状
- 配置方式：application-prod.yml
- migration 方式：Flyway，文件命名 `V{version}__{description}.sql`
- 监控方式：Prometheus + Grafana
- 回滚方式：Git revert + Flyway undo migration

## 规则基线摘要

### 项目级规则
- 先理解现状，再设计和实现
- 发现遗漏先回写 OpenSpec 再继续

### 后端规则
- 遵循 Controller / Service / Mapper 分层
- 统一返回体 `Response<T>`、异常 `ServiceException`
- 租户字段 `tenant_id` 必须存在且查询时过滤

### 前端规则
- 复用 SearchForm + BaseTable + FormDialog + ExportButton
- 不替换组件库和状态管理

### 本次变更必须额外强调的约束
- 负责人字段来自主数据服务 `/api/master-data/users`，已确认可用
- 项目状态使用字典服务 key `project_status`，已确认存在
- 导出复用 EasyExcel + `ExportUtil`

### 关联规则文档
- `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
- `.cursor/rules/brownfield-project-baseline.mdc`
- `.cursor/rules/java-brownfield-backend.mdc`
- `.cursor/rules/frontend-brownfield-conventions.mdc`

## OpenSpec 产物清单

- `proposal.md`：
  - 作用：说明增量扩展项目管理模块的理由、范围和兼容边界
  - 路径：`openspec/changes/project-management/proposal.md`

- `spec.md`：
  - capability：`project-management`
  - 核心 requirements：Query Project List、Create Project、Update Project、Export Project List、Brownfield Compatibility
  - 路径：`openspec/changes/project-management/specs/project-management/spec.md`

- `design.md`：
  - 关键设计决策：复用现有分层、返回体、分页、导出工具、前端组件
  - 路径：`openspec/changes/project-management/design.md`

- `tasks.md`：
  - 核心任务分组：Discovery → Backend → Frontend → Integration → Verification → Release
  - 路径：`openspec/changes/project-management/tasks.md`

## 实施计划摘要

### 任务顺序
1. 后端列表查询 + migration
2. 后端新增/修改
3. 后端导出
4. 前端列表页 + 查询区
5. 前端表单
6. 前端导出按钮
7. 联调
8. 验证 + 验收矩阵

### 后端改动范围
- 新增 ProjectController、ProjectService、ProjectServiceImpl、ProjectMapper、Project entity
- 新增 Flyway migration V20260325__add_project_table.sql

### 前端改动范围
- 新增 src/views/project-management/index.vue
- 新增 src/api/project-management.js
- 修改 src/router/modules/business.js（新增路由）

### 联调重点
- 导出是否按筛选条件生效
- 租户隔离是否在查询和导出中同时生效
- 负责人远程搜索是否正确调用主数据服务

### 关键风险
- 主数据服务如果响应慢，负责人下拉可能影响体验

### 计划与 OpenSpec 是否一致
- 一致

## 实现结果摘要

### 后端
- ProjectController 4 个端点已实现
- 所有端点使用 Response<T> 返回、ServiceException 异常处理
- 租户过滤通过 TenantContext 自动注入

### 前端
- 项目管理列表页已实现，复用 SearchForm + BaseTable + FormDialog + ExportButton
- 导出按钮传递当前查询表单参数

### 兼容性处理
- 未修改任何现有接口
- 未修改任何现有页面组件
- 新增独立路由和页面

### 字段级契约与证据
- 关键请求样例：POST /api/project-management `{"projectName":"测试项目","projectCode":"PRJ-2026-TEST","status":1,"ownerId":100}`
- 关键响应样例：`{"code":200,"message":"success","data":{"id":1,...}}`
- 错误响应样例：`{"code":400,"message":"项目名称不能为空","data":null}`

### 未纳入本次范围
- 项目删除（产品明确暂不做）
- 项目详情页（下一迭代）

## Git / Commit / Review / PR 记录

### 分支信息
- 功能分支：`feature/project-management`
- 基线分支：`main`

### Commit 摘要
- commit 1：feat: 新增项目管理后端列表查询与 migration
- commit 2：feat: 新增项目管理后端新增/修改/导出接口与测试
- commit 3：feat: 新增项目管理前端列表页、表单和导出
- commit 4：fix: 修复导出筛选条件未传递问题
- commit 5：test: 补充租户隔离和权限测试用例

### 代码评审
- 是否完成 code review：是
- 主要评审意见：导出接口需增加文件大小上限检查
- 是否已修复：是（commit 4 中修复）

### PR 信息
- PR 标题：feat: 新增项目管理模块（列表/新增/修改/导出）
- PR 链接：#142
- PR Summary：在现有系统中新增项目管理模块 CRUD 和导出能力
- Test Plan：后端单测 + 前后端联调 + 租户隔离验证 + 导出筛选验证

### 合并结果
- 是否已合并：是
- 合并时间：2026-04-08 15:30
- 合并后是否再次验证：是，合并后在 staging 环境重新执行了 smoke test

## 测试与验证结果

### 后端测试
- 执行命令：`mvn test -pl project-management`
- 结果：18/18 通过
- 备注：覆盖查询、新增、修改、导出、租户隔离、权限校验

### 前端测试
- 执行命令：手动浏览器验证（项目无前端自动化测试）
- 结果：Chrome 120 + Firefox 121 验证通过
- 备注：查询、新增、修改、导出、表单校验、loading 反馈均正常

### 联调验证
- 核心验证链路：列表查询 → 新增 → 修改 → 导出
- 结果：全部通过

### 权限 / 租户 / 主数据验证
- 覆盖场景：
  - 管理员可查看和编辑
  - 普通用户只可查看
  - 租户 A 不可看到租户 B 数据
  - 负责人下拉正确调用主数据服务
- 结果：全部通过

### 验收矩阵
- 路径：项目内 `docs/acceptance/project-management-matrix.md`
- 结论：具备上线评审条件

### 旧链路回归
- 回归范围：合同列表页、客户列表页、导出功能
- 结果：未发现回归问题

### OpenSpec 验证
- 是否执行 `/opsx-verify`：是
- 结果摘要：Completeness PASS, Correctness PASS, Coherence PASS, Compatibility PASS, Release readiness PASS

### 尚未完成或未执行项
- [ ] 负责人下拉在大数据量下的性能测试
- 原因：主数据服务测试环境数据量不足
- 风险：低（生产环境负责人数量预计 < 500）

## 发布准备清单

### 配置项
- 无新增配置项

### 数据库变更
- migration 文件：`V20260325__add_project_table.sql`
- 是否可回滚：是（Flyway undo migration 已准备）
- 备份要求：发布前备份 production 数据库

### 后端发布项
- 构建产物：`project-management.jar`
- 发布步骤：标准 CI/CD 流水线
- 健康检查：`/actuator/health`

### 前端发布项
- 构建产物：`dist/`
- 发布步骤：标准 CI/CD 流水线
- 页面检查：访问 `/project-management` 确认页面加载正常

### 监控项
- 接口 5xx 错误率
- 接口 P99 延迟
- 导出接口超时率

### 回滚点
- Git revert commit + Flyway undo migration + 重新部署

### 旧链路观察项
- 合同列表查询性能
- 客户导出功能

## 上线执行记录

### 发布时间
- 2026-04-10 10:00

### 实际执行步骤
1. 执行 Flyway migration
2. 部署后端服务
3. 部署前端资源
4. 验证 health check
5. 执行 smoke test

### 发布后 smoke test
- 列表查询：通过
- 新增项目：通过
- 修改项目：通过
- 导出：通过

### 发布后监控观察
- 5xx 错误率：0%
- P99 延迟：< 200ms
- 旧链路无异常

### 是否触发回滚
- 否

## 结论与归档建议

### 是否达到交付标准
- 是

### 若达到标准
- 是否可归档：是
- 是否需要 `/opsx-sync`：暂不需要（等待下一迭代"项目详情"完成后统一 sync）
- 是否需要补充规则基线：建议在后端规则中补充"导出接口必须包含文件大小上限检查"
- 当前是否具备证据化验收闭环：是
```

---

## 15. 使用建议

这份模板最适合在三种场景下使用：

1. 给团队做统一交付沉淀；
2. 给 AI 作为“最终输出目标”；
3. 给评审、测试、发布负责人做统一交接材料。

不要把它当成“必须一次写满的长文档”。更推荐做法是：

1. 先填 1-6 节；
2. 开发中补 7-8 节；
3. 发布前补 9 节；
4. 上线后补 10-11 节。

