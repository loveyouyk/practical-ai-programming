# Demo 实战走查记录：设备管理模块

> 目的：以真实对抗思维，严格按照实战指南方法论走完 M-Medium 快车道全流程，逐环节记录理论与实践的冲突点。  
> 需求：在现有历史系统（合同管理、客户管理）中新增"设备管理"模块，支持列表查询、新增、修改。不含导出。  
> 选择原因：故意不使用主案例 `project-management`，验证方法论对非主案例的可迁移性。

---

## 环节 0：治理分流

### 按方法论应做的事

参考 `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`，判断需求属于 S/M/L。

### 实际执行

四维判断：
1. **工作量**：新增一个列表页 + CRUD 接口 + 一张表 → 中等
2. **风险等级**：不涉及权限模型变更、不涉及导出语义变化、不涉及租户逻辑修改（只是复用） → 中等偏低
3. **影响面**：只新增独立页面和独立接口，不修改现有模块 → 单模块
4. **不确定性**：需要确认设备状态枚举来源、是否有现有设备相关表 → 低

**结论：M-Medium**

### 🔴 碰撞点 #1：分流判断缺乏快速检查清单

**冲突描述：** 治理手册第 4 节"分流治理模型"给出了四个维度和硬性升级条件，但在实操中需要反复在大段文字中查找判断条件。没有一个"快速 checklist"让开发者 30 秒内完成分流。

**实际影响：** 我作为实操者花了 3 分钟重新阅读手册第 4 节才完成判断。如果是日常开发节奏，多数开发者会跳过这一步直接开干。

**建议修正：** 在治理手册中增加一个"分流快速检查表"（10 个 Yes/No 问题），回答完自动得出 S/M/L。

---

## 环节 1：最小探索

### 按方法论应做的事

参考 `execution/BROWNFIELD-QUICKSTART-CHECKLIST.md` 第 1-3 步，先巡查项目结构、提炼规则、固化 rules。

### 实际执行

对 demo 项目做探索，产出如下发现：

**后端发现：**
1. 统一返回体：`Response<T>`（非 Spring 标准 `ResponseEntity`）
2. 统一异常：`ServiceException`（构造器接受 code + message）
3. 租户：`TenantContext`（ThreadLocal 模式）
4. 分页：内存分页（demo 简化版，生产用 PageHelper）
5. 分层：`Controller → Service → ServiceImpl`（没有 Mapper 接口层，ServiceImpl 直接内嵌存储）
6. 导出：`ExportUtil.export(fileName, headers, rows)` — 全局统一
7. 字典：`DictService.getDictByKey()` — **关键发现：dict key 是 String 不是 Int，DB 存 int 但 API 返回 string**

**前端发现：**
1. 请求封装：`utils/request.js` 的 `request()` 和 `downloadFile()`
2. **关键发现：错误处理通过 `window.__toast` 全局弹出，不返回错误对象**
3. 共享组件：`SearchForm`（重置不自动搜索）、`BaseTable`（默认 pageSize=20）、`FormDialog`（confirm 不自动关闭）、`ExportButton`（需手动传 params）
4. 路由配置：`router/modules/business.js`，需要 meta.title 和 meta.permission
5. API 层：`api/contract.js` — 导出时手动剔除 pageNum/pageSize

### 🔴 碰撞点 #2：方法论假设"先巡查再提炼规则再固化 rules"是三步，实际上常常合并

**冲突描述：** `BROWNFIELD-QUICKSTART-CHECKLIST.md` 把巡查（步骤1）、提炼规则（步骤2）、固化 rules（步骤3）分成三个独立步骤，每步都有独立提示词和完成标准。但在实操中，巡查的同时就自然在心里提炼规则了，发现一个约束就想马上写进 rules。三步串行执行时，步骤 2 几乎是对步骤 1 的重复表达。

**实际影响：** 如果严格按三步做，开发者会觉得"提炼规则"这步是在复述巡查结果，浪费时间。如果不按三步做，又担心"流程不合规"。

**建议修正：** 对 M 级需求，建议把三步合并为"巡查并提炼规则"一步，产出物直接是 rules 文件 + 巡查摘要。把三步串行保留给 L 级需求。

### 🔴 碰撞点 #3：发现了 QUIRK（历史怪癖）但方法论没有教你怎么记录和传递它们

**冲突描述：** 巡查中发现了至少 5 个 QUIRK（SearchForm 重置不自动搜索、BaseTable 默认 pageSize=20、FormDialog confirm 不自动关闭、ExportButton 需手动传 params、DictService key 是 String）。这些不是"规则"，而是"历史怪癖"，它们不能写成"必须"或"不得"的规则形式，但如果不记录，AI 一定会踩坑。

**实际影响：** 现有 `.cursor/rules/*.mdc` 的格式适合写"必须/不得"式约束，但不适合写"注意这个组件有一个坑：重置后不会自动触发搜索"这类信息。

**建议修正：** 在规则基线体系中增加一个"QUIRK 登记簿"的概念和模板，专门用于记录历史项目中的非直觉行为，格式建议：`组件/服务 + QUIRK 描述 + 正确应对方式`。

---

## 环节 2：最小 OpenSpec

### 按方法论应做的事

参考 `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`，生成 proposal/spec/design/tasks 四件套。

### 实际执行

使用分流起手提示词（来自 `BROWNFIELD-AI-PROMPT-HANDBOOK.md` 2.2 节）生成了最小 OpenSpec：

**proposal 要点：** 新增 `equipment-management` capability，复用现有 CRUD 模式，不含导出。

**spec 要点：**
- Query Equipment List（按名称/编号/状态筛选）
- Create Equipment
- Update Equipment
- Brownfield Compatibility

**design 要点：**
- `GET /api/equipment/list`
- `POST /api/equipment`
- `PUT /api/equipment/{id}`
- 表 `t_equipment`（id, tenant_id, equipment_name, equipment_code, status, location, ...）
- 状态字段使用 DictService key `equipment_status`

### 🔴 碰撞点 #4：M 级最小 OpenSpec 示例没有覆盖"待确认项"的标准化处理

**冲突描述：** `M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md` 的示例中没有 `Pending Confirmations` 或 `待确认` 章节。但在实操中，几乎每个需求都有待确认项（设备状态枚举是否在 DictService 中已存在？设备编号是否全局唯一还是租户内唯一？）。如果 M 级示例不包含待确认项，开发者容易误以为"M 级不需要记录待确认项"。

**实际影响：** 写 design 时发现设备状态枚举不确定是否已在字典服务中存在，但 M 级示例没有引导记录这个信息，差点直接拍脑袋决定用固定枚举。

**建议修正：** 在 M 级最小示例中增加一个 `Pending Confirmations` 示例节，即使只有 1-2 项，也要让开发者形成习惯。

### 🔴 碰撞点 #5：提示词中 `@openspec-ff-change` 是什么？工具不可用时怎么办？

**冲突描述：** 治理手册第 5.2 节和讨论稿中反复提到"使用 `@openspec-ff-change` 生成 proposal/spec/design/tasks"，但在实际环境中：
1. `@openspec-ff-change` 是 Cursor 的自定义命令？是 MCP tool？还是一个约定的提示词前缀？
2. `AI-WORKFLOW-TOOLING-PREREQUISITES.md` 有 `/opsx-explore`、`/opsx-verify` 等的 fallback，但没有 `@openspec-ff-change` 的 fallback
3. 在当前 demo 环境中完全无法执行这个命令

**实际影响：** 这是方法论中引用最多但说明最少的工具之一。开发者看到这个命令时的第一反应是"这是什么？在哪执行？"。

**建议修正：** 在 `AI-WORKFLOW-TOOLING-PREREQUISITES.md` 中明确说明 `@openspec-ff-change` 的性质（是什么、在哪用、前置条件），并增加 fallback 说明（如果不可用，手动创建 4 个 .md 文件并按模板填写）。

---

## 环节 3：人工审批

### 按方法论应做的事

TL 审批 proposal/spec/design/tasks，覆盖范围、requirement、接口、验证项。

### 实际执行

模拟 TL 审批，发现问题：
1. proposal 范围 OK
2. spec 缺少"设备编号唯一性"场景 → **补充**
3. design 中 `equipment_status` 字典是否已存在 → **标记为待确认**
4. tasks 缺少"确认字典服务是否有 equipment_status"任务 → **补充**

### 🔴 碰撞点 #6：审批标准只有"最少覆盖"，没有"审批 checklist 模板"

**冲突描述：** 治理手册说审批最少覆盖 proposal 范围/spec requirement/design 接口/tasks 验证项，但没有给出一个可勾选的 checklist。在实操中，TL 很容易漏掉其中某一项（比如我差点忘了检查 tasks 的验证项）。

**实际影响：** 审批质量完全取决于 TL 的个人经验和记忆力。

**建议修正：** 增加一个 M 级审批 checklist 模板，10 个左右的勾选项，TL 审批时逐项打勾。

### 🔴 碰撞点 #7：审批发现问题后，"不通过"的标准动作不够明确

**冲突描述：** 审批中发现 spec 缺场景、tasks 缺任务时，方法论说"退回 AI 重新生成"或"升级到完整流程"。但在实操中，更常见的情况是"补一两个小点就行了"，既不需要完全重新生成，也不需要升级流程。方法论没有覆盖这个"微调"的中间态。

**实际影响：** TL 在审批中发现的问题大多数是"补一个场景""加一个待确认项"，不是"整体方向错误"。如果每次都"退回重新生成"，效率太低。

**建议修正：** 明确审批结果可以有三种：通过 / 微调后通过（TL 直接指出修改点，开发者补充后无需再审）/ 不通过需重新生成。

---

## 环节 4：开发

### 按方法论应做的事

审批通过后，按 spec/design 开发后端和前端。

### 实际执行

严格按照 design 开发了 EquipmentController / EquipmentService / EquipmentServiceImpl，以及前端 equipment/index.vue、api/equipment.js、路由配置。

### 🔴 碰撞点 #8：开发中发现 design 的字段级契约和实际项目不完全匹配

**冲突描述：** design.md 中写了 `GET /api/equipment/list` 的响应字段包含 `statusName`（状态名称），需要从 DictService 翻译。但在实际看合同模块代码时发现，合同列表接口返回的是 `status`（纯数字），**翻译由前端完成**，不是后端。这是历史项目的一个决策（前端有字典缓存，后端不做翻译以减少耦合）。

如果按 design.md 开发，后端返回 `statusName`，就和历史项目模式不一致了。

**实际影响：** 这是方法论中最典型的"理论 vs 实践"冲突 — design 是在探索之后写的，但探索阶段对"字段翻译由谁做"这个隐性约定没有明确记录（因为它不是"规则"，而是"历史惯例"）。

**建议修正：** 
1. 在规则基线模板中增加"字段翻译/格式化约定"条目
2. 在 design 模板中增加"字段级契约"章节的填写说明，提醒检查"翻译由前端还是后端完成"

**实际处理：** 按方法论要求（碰撞点对应"需求变更回写"流程），先更新 design 删除 `statusName`，改为前端翻译，再继续开发。

### 🔴 碰撞点 #9：方法论说"先做后端再做前端"，但实际上前后端常常需要交叉验证

**冲突描述：** 实施计划和 tasks 都建议"先后端 → 再前端 → 再联调"的线性顺序。但在实操中，做后端列表接口时就需要确认"前端查询区到底传哪些参数"，做前端表单时就需要确认"后端校验规则到底是什么"。如果严格按线性顺序做，前端开始时会发现后端的 API 缺少某些约定，又要倒回去改。

**实际影响：** demo 中后端接口做完后，开始做前端才发现 SearchForm 的 reset 行为不会自动搜索，需要前端显式调用 fetchList()。这个信息在后端开发阶段无法获知，只有在前端开发时才会碰到。

**建议修正：** 对 M 级需求，建议实施计划的"后端→前端"改为"后端骨架→前端骨架→联调填充"的节奏，允许前后端交叉迭代，而不是严格线性。

---

## 环节 5：验收矩阵

### 按方法论应做的事

建立验收矩阵，每条 requirement 映射到证据。

### 实际执行

| Requirement | Scenario | 后端实现 | 前端实现 | 联调验证 | 状态 | 证据 |
|---|---|---|---|---|---|---|
| Query Equipment List | 按名称筛选 | ✅ | ✅ | ✅ | 联调通过 | 控制台请求/响应日志 |
| Query Equipment List | 租户隔离 | ✅ | N/A | ✅ | 联调通过 | 切换租户后数据隔离 |
| Create Equipment | 新增成功 | ✅ | ✅ | ✅ | 联调通过 | 新增后列表刷新 |
| Create Equipment | 必填校验 | ✅ | ✅ | ✅ | 联调通过 | 空字段提交返回 400 |
| Update Equipment | 修改+回显 | ✅ | ✅ | ✅ | 联调通过 | 编辑弹窗回显正确 |
| Compatibility | 旧页面不受影响 | N/A | ✅ | ✅ | 联调通过 | 合同页面正常访问 |

### 🔴 碰撞点 #10：M 级验收矩阵在实操中比模板要求的更轻，但方法论没有给"M 级精简版"模板

**冲突描述：** `BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` 的标准矩阵有 11 列（Requirement、Scenario、前端实现、后端实现、前端测试、后端测试、联调验证、发布准备、状态、证据、备注），对 M 级需求来说列太多。M 级需求通常没有独立的"前端测试""后端测试"列（因为没有自动化测试，只有联调验证）。

**实际影响：** 开发者填矩阵时会发现"前端测试""后端测试"两列要么空着，要么写"手动验证"—— 信息量很低但占位很多。

**建议修正：** 增加一个 M 级精简矩阵模板（6-7 列：Requirement、Scenario、实现状态、联调验证、证据、状态、备注），把"前端测试""后端测试""发布准备"合并或移除。

---

## 环节 6：结构化证据

### 按方法论应做的事

为关键 requirement 填写结构化证据块。

### 实际执行

```md
## Verification Evidence

### Change / Commit / PR
- feature/equipment-management, commit: "feat: add equipment CRUD"

### Command Or Action
- 打开设备管理页面，输入"测试设备"搜索，新增一台设备，修改设备名称

### Environment
- 本地开发环境，Chrome 120

### Result
- 列表正确返回筛选结果，新增/修改后列表自动刷新

### Evidence Location
- 浏览器控制台 Network 面板截图（本地无法自动采集）

### Conclusion
- 设备管理 CRUD 基本功能联调通过

### Residual Risk
- 未测试大数据量下的分页性能
- 未测试多租户并发场景
```

### 🔴 碰撞点 #11：证据块的 `Evidence Location` 在本地开发环境中很难有效填写

**冲突描述：** 证据块要求填写 `Evidence Location`，在 CI/CD 环境中可以指向 test report URL 或 artifact 链接。但在本地开发环境中，"证据位置"通常只能填"浏览器控制台截图"或"本地日志"—— 这些证据不具备可追溯性，别人无法复现。

**实际影响：** M 级需求大多在本地开发和验证，还没有进入 CI。此时强制要求 `Evidence Location` 会导致开发者随便填一个应付了事。

**建议修正：** 
1. 对 M 级的 Evidence Location，明确"可以填本地截图路径或 commit hash + 手动验证步骤描述"
2. 增加一条原则：证据的核心是"可复现"，而不是"有链接"。如果没有 CI 链接，写清楚"在哪个 commit 上、执行什么操作、应该看到什么结果"也是有效证据。

---

## 碰撞点汇总

| # | 碰撞点 | 类别 | 严重程度 | 影响环节 |
|---|---|---|---|---|
| 1 | 分流判断缺乏快速检查清单 | 治理 | 中 | 环节 0 |
| 2 | 巡查/提炼/固化三步在 M 级中应合并 | 流程 | 中 | 环节 1 |
| 3 | 缺少 QUIRK（历史怪癖）登记机制 | 规则体系 | 高 | 环节 1 |
| 4 | M 级最小 OpenSpec 缺少待确认项示例 | 模板 | 中 | 环节 2 |
| 5 | `@openspec-ff-change` 说明和 fallback 缺失 | 工具 | 高 | 环节 2 |
| 6 | 审批缺少 checklist 模板 | 流程 | 中 | 环节 3 |
| 7 | 审批"微调后通过"的中间态缺失 | 流程 | 中 | 环节 3 |
| 8 | 字段翻译/格式化约定未在规则基线中覆盖 | 规则体系 | 高 | 环节 4 |
| 9 | 线性"后端→前端"不适合 M 级交叉迭代 | 流程 | 中 | 环节 4 |
| 10 | M 级验收矩阵缺少精简模板 | 模板 | 中 | 环节 5 |
| 11 | Evidence Location 在本地环境中难以有效填写 | 证据体系 | 中 | 环节 6 |

