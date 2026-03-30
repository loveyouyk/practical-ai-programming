# Demo 复盘操作指南：一步步带你走

> 本指南的目的：指导你作为复盘者，逐步检查 Demo 实战的每个环节，理解"AI 是怎么做的、做对了什么、碰到了什么坑"。  
> 预计用时：完整走一遍约 60-90 分钟，快速浏览约 30 分钟。  
> 你不需要写代码，只需要阅读、检查和思考。

---

## 开始之前

请确认你已经把仓库 clone 到本地或在线环境中可以访问 `demo-brownfield-project/` 目录。

所有路径以 `demo-brownfield-project/` 为根。

---

## Phase 1：理解棕地项目（15 分钟）

> 目的：你需要先把自己当成"第一天接手这个历史系统的开发者"，理解它有什么、有什么坑。

### Step 1.1 看后端骨架

打开以下文件，重点看注释和代码结构：

```
backend/src/main/java/com/demo/common/Response.java
backend/src/main/java/com/demo/common/ServiceException.java
backend/src/main/java/com/demo/common/TenantContext.java
backend/src/main/java/com/demo/common/PageResult.java
backend/src/main/java/com/demo/common/ExportUtil.java
```

**检查清单：**

- [ ] `Response.java` — 这是统一返回体。注意它不是 Spring 的 `ResponseEntity`，AI 如果用 `ResponseEntity` 就踩坑了
- [ ] `ServiceException.java` — 这是统一异常。注意构造器只有 `(int code, String message)`，没有 cause 参数
- [ ] `TenantContext.java` — ThreadLocal 方式的租户上下文。注意如果开发者忘记在查询中过滤 `tenant_id`，就会出现跨租户数据泄露
- [ ] `ExportUtil.java` — 看注释："This is the ONLY approved export mechanism"。如果 AI 另造一套导出就违规了

**你的思考：** 如果你是 AI，在没有看过这些代码的情况下，你会怎么写返回体？大概率会用 `ResponseEntity` —— 这就是棕地项目里 AI 最常犯的第一个错。

### Step 1.2 看后端业务模块（合同管理）

```
backend/src/main/java/com/demo/model/Contract.java
backend/src/main/java/com/demo/service/ContractService.java
backend/src/main/java/com/demo/service/impl/ContractServiceImpl.java
backend/src/main/java/com/demo/controller/ContractController.java
```

**检查清单：**

- [ ] `ContractServiceImpl.java` — 看 `queryPage()` 方法。注意第一行就做了 `TenantContext.getTenantId()`，没有 tenant 就直接抛异常
- [ ] `ContractServiceImpl.java` — 看 `create()` 方法。注意校验用的是 `ServiceException`，不是 Spring Validation 注解
- [ ] `ContractController.java` — 看返回方式。注意每个方法都 try-catch `ServiceException`，然后返回 `Response.error()`

**关键发现：** 后端列表接口**不返回** `statusName`（状态名称翻译）。只返回 `status`（纯 int）。这个约定在代码里是隐性的 —— 没有任何注释说"不要在后端翻译"。

### Step 1.3 看字典服务

```
backend/src/main/java/com/demo/service/DictService.java
```

**检查清单：**

- [ ] 看 `getDictByKey()` 的返回类型 — `Map<String, String>`。key 是 **String** 类型
- [ ] 但数据库里 `status` 字段是 **Integer**。这意味着前端拿到 `status=1` 后，需要转成 `"1"` 才能去字典里查

**这就是一个 QUIRK**：数据库存 int，字典服务返回 String key，前端必须做类型转换。如果 AI 不知道这个坑，会直接用 int 去匹配 String，拿不到翻译结果。

### Step 1.4 看前端骨架

逐个打开以下组件，**只看注释和 QUIRK**：

```
frontend/src/components/SearchForm.vue
frontend/src/components/BaseTable.vue
frontend/src/components/FormDialog.vue
frontend/src/components/ExportButton.vue
frontend/src/utils/request.js
```

**检查清单：**

- [ ] `SearchForm.vue` — QUIRK：`handleReset()` 只 emit `reset`，**不 emit `search`**。开发者如果不知道，重置后列表不会刷新
- [ ] `BaseTable.vue` — QUIRK：`pageSize` 默认值是 **20** 不是 10。如果后端默认用 10，前后端就对不齐
- [ ] `FormDialog.vue` — QUIRK：`@confirm` 事件**不会自动关闭弹窗**。开发者必须在 API 成功后手动 `this.dialogVisible = false`
- [ ] `ExportButton.vue` — QUIRK：需要**手动传** `exportParams`，组件不会自动读取父组件的查询状态
- [ ] `request.js` — QUIRK：错误处理通过 `window.__toast` 全局弹出，**不返回 error 对象**给调用方

### Step 1.5 看合同列表页（参考实现）

```
frontend/src/views/contract/index.vue
```

**检查清单：**

- [ ] 看 `handleReset()` — 重置后**没有**调用 `fetchList()`（配合 SearchForm 的 QUIRK）
- [ ] 看 `data()` — `pageSize: 20`（配合 BaseTable 的 QUIRK）
- [ ] 看 `handleSubmit()` — API 成功后手动 `this.dialogVisible = false`（配合 FormDialog 的 QUIRK）
- [ ] 看 `exportContracts` 的调用方式 — 通过 `:exportParams="queryForm"` 手动传参

**你的思考：** 这一页就是"新增模块时应该参考的模板页面"。如果 AI 不知道要参考这个页面，就会按通用 Vue 最佳实践写出一套完全不同风格的代码。

### Step 1.6 看路由配置

```
frontend/src/router/modules/business.js
```

**检查清单：**

- [ ] 每个路由都有 `meta.title` 和 `meta.permission`。新增路由必须也有这两个字段

---

**Phase 1 小结：** 你现在应该对这个历史系统有了基本理解。最关键的收获是发现了 7 个 QUIRK。接下来看方法论是怎么应对这些 QUIRK 的。

---

## Phase 2：逐步走查提示词（30 分钟）

> 目的：看实战中每一步用了什么提示词、产生了什么效果、碰到了什么坑。

### Step 2.0 分流判断

打开 `prompts/00-triage.md`

**检查清单：**

- [ ] 看"实际使用的提示词"部分 — 注意它把四个维度（工作量/风险/影响面/不确定性）都写进去了
- [ ] 看"实际效果"部分 — AI 正确判断为 M-Medium
- [ ] 看"碰撞发现"部分 — 🔴 碰撞点 #1：花了 3 分钟查手册才完成判断，日常节奏下开发者会跳过

**你的思考：** 如果你拿到一个需求，你能在 30 秒内判断 S/M/L 吗？如果不能，说明分流检查表的改进是有必要的。现在去看改进后的 `execution/templates/triage-quick-card.md`。

### Step 2.1 棕地探索

打开 `prompts/01-explore.md`

**检查清单：**

- [ ] 对比"实际使用的提示词"和方法论原版（`execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 4.1 节）—— 实操版多了第 6-9 条（QUIRK、数据翻译约定）和"QUIRK"输出分组
- [ ] 看"实际效果"—— AI 找出了 5 个 QUIRK 和字段翻译约定
- [ ] 看"碰撞发现"—— 🔴 碰撞点 #2：三步拆太细；🔴 碰撞点 #3：QUIRK 没有专门的登记机制

**你的思考：** 原版提示词的输出分组是"前端/后端/数据/风险/待确认"。实操后发现需要增加"QUIRK"和"数据翻译约定"两个分组。这个改进已经反写到方法论中了。

### Step 2.2 M 级起手

打开 `prompts/02-m-medium-kickoff.md`

**检查清单：**

- [ ] 看"实际使用的提示词"—— 注意它有"已知约束"段（8 条）和"待确认项"段（2 条），这些都是从上一步探索结果**手动搬运**过来的
- [ ] 看"与方法论原版的差异"—— 原版只有 5 条通用要求，实操版增加了具体约束和待确认项
- [ ] 看"碰撞发现"—— 🔴 碰撞点 #4：原版没有待确认引导；🔴 碰撞点 #5：`@openspec-ff-change` 不可用

**关键操作：** 这一步是方法论中"最关键的衔接动作" —— 把探索结果手动搬运到 OpenSpec 生成提示词里。如果不搬运，AI 会按通用方式生成 OpenSpec，丢失所有历史项目约束。

### Step 2.3 生成 OpenSpec

打开 `prompts/03-generate-openspec.md`

**检查清单：**

- [ ] 看 spec 细化提示词 —— 审批后发现缺 3 个 scenario，需要补充
- [ ] 看 design 修正提示词 —— **这就是变更回写**：发现 statusName 不该有，先改 design 再继续

**对照产出物：** 打开 `openspec/changes/equipment-management/design.md`，找到 Decision 2 和 Decision 3 —— 标注了"变更回写产物"。这证明回写流程确实被执行了。

### Step 2.4 审批

打开 `prompts/04-approval.md`

**检查清单：**

- [ ] 看"实际使用的提示词"—— 把审批四条标准转成了可逐项检查的提示词
- [ ] 看"审批结论"—— 三个问题都是小改动，结论是"微调后通过"（不是退回重新生成）
- [ ] 看"碰撞发现"—— 🔴 碰撞点 #6：没有 checklist 模板；🔴 碰撞点 #7：只有"通过/不通过"两种结论

**对照改进：** 现在去看 `execution/templates/m-medium-approval-checklist.md` —— 这就是根据碰撞点新增的审批 checklist。

### Step 2.5 后端实现

打开 `prompts/05-backend-impl.md`

**检查清单：**

- [ ] 看约束第 7 条 — "后端不做字段翻译，status 返回原始 int 值，不返回 statusName"
- [ ] 看"实际效果"— AI 初始版本**仍然生成了 statusName**（虽然约束里写了不要）
- [ ] 看"碰撞发现"— 🔴 碰撞点 #8：AI 的"通用最佳实践"倾向太强，需要更强硬措辞

**你的思考：** 即使提示词里写了"不返回 statusName"，AI 仍然可能按惯性生成。这说明需要用"严禁"这种更强硬的措辞。改进后的提示词手册已经加上了"QUIRK 警告（严格遵守，违反即为 bug）"的表述。

### Step 2.6 前端实现

打开 `prompts/06-frontend-impl.md`

**检查清单：**

- [ ] 看"QUIRK 警告"段 — 每个共享组件后面都附了 QUIRK 和应对方式
- [ ] 看"实际效果"— AI 在输出中**主动列出**了"我注意到 FormDialog 的 confirm 不自动关闭"
- [ ] 这说明 QUIRK 信息在提示词中传递是有效的

**关键结论：** QUIRK 写在提示词里比只靠 `.cursor/rules` 文件更直接有效。但 rules 文件的价值在于跨会话持久化 —— 两者需要配合使用。

### Step 2.7 变更回写

打开 `prompts/07-change-writeback.md`

**检查清单：**

- [ ] 看触发原因 — design 中 statusName 与历史约定不一致
- [ ] 看 AI 分析结论 — 正确判断为"设计变更"，只影响 design.md
- [ ] 看处理时间 — < 10 分钟
- [ ] 看"碰撞发现"— M 级回写实际上只需要 3 步（判断→更新→继续），不需要走完整 6 步

### Step 2.8 验收矩阵

打开 `prompts/08-acceptance-matrix.md`

**检查清单：**

- [ ] 看使用的矩阵格式 — 7 列精简版（不是 11 列标准版）
- [ ] 看"碰撞发现"— 🔴 碰撞点 #10：标准 11 列过重

**对照产出物：** 打开 `artifacts/acceptance-matrix.md`，逐行检查：

- [ ] 每条 requirement 是否都有 scenario
- [ ] 每条是否都有证据
- [ ] "待确认"项是否标注清楚
- [ ] 最终结论是"部分达标"（因为待确认项未消解）

### Step 2.9 结构化证据

打开 `prompts/09-evidence.md`

**检查清单：**

- [ ] 看 Evidence Location 的处理方式 — "commit hash + 可复现操作步骤"
- [ ] 看"碰撞发现"— 🔴 碰撞点 #11：本地环境没有 CI URL

**对照产出物：** 打开 `artifacts/verification-evidence.md`，检查 5 份证据块：

- [ ] Evidence 1（列表查询）— 操作步骤是否具体到"输入什么、看到什么"
- [ ] Evidence 2（新增）— 是否覆盖了"校验失败"和"编号重复"场景
- [ ] Evidence 3（修改）— 是否验证了"数据回显"
- [ ] Evidence 4（租户隔离）— 是否验证了"跨租户不可见"
- [ ] Evidence 5（兼容性）— 是否验证了"旧页面不受影响"

---

## Phase 3：审查产出物完整性（10 分钟）

### Step 3.1 OpenSpec 四件套交叉检查

打开以下 4 份文件，检查它们之间是否一致：

```
openspec/changes/equipment-management/proposal.md
openspec/changes/equipment-management/specs/equipment-management/spec.md
openspec/changes/equipment-management/design.md
openspec/changes/equipment-management/tasks.md
```

**检查清单：**

- [ ] proposal 的 scope 是否和 spec 的 requirement 对齐（proposal 说了 3 个功能，spec 是否有对应的 3+1 条 requirement）
- [ ] design 的 API 路径是否和 spec 的 scenario 描述一致
- [ ] design 是否包含 Pending Confirmations
- [ ] tasks 是否覆盖了 spec 中的所有 requirement（不只是实现，还有验证）
- [ ] tasks 中是否有"确认待确认项"的前置任务

### Step 3.2 QUIRK 登记簿检查

打开 `artifacts/quirk-registry.md`

**检查清单：**

- [ ] 是否覆盖了 Phase 1 中你发现的所有 QUIRK
- [ ] 每个 QUIRK 是否有"正确应对方式"
- [ ] 每个 QUIRK 是否标注了"发现环节"和"验证状态"
- [ ] 是否有遗漏？（提示：Phase 1 中你发现了 7 个 QUIRK，登记簿里有几个？）

---

## Phase 4：复盘碰撞点（15 分钟）

### Step 4.1 阅读碰撞汇总

打开 `DEMO-WALKTHROUGH.md`，直接翻到最后的"碰撞点汇总"表格。

逐行读，对每个碰撞点问自己：

| 碰撞点 | 你的判断 |
|---|---|
| #1 缺快速检查表 | 你自己做分流判断时也觉得慢吗？ |
| #2 三步应合并 | 你觉得巡查/规则/固化分开做有价值还是浪费？ |
| #3 缺 QUIRK 登记 | 你在 Phase 1 中发现 QUIRK 时，会想到要记录下来吗？ |
| #4 缺待确认引导 | 你写 OpenSpec 时会主动列待确认项吗？ |
| #5 @openspec 不可用 | 你的团队环境里能用 @openspec-ff-change 吗？ |
| #6 缺审批 checklist | 你做 review 时靠记忆还是靠清单？ |
| #7 缺微调选项 | 你审批发现小问题时是退回还是直接说修改点？ |
| #8 字段翻译冲突 | 你的项目里字段翻译由谁做？有没有记录？ |
| #9 线性顺序 | 你做需求时前后端是串行还是交叉？ |
| #10 矩阵过重 | 你觉得 11 列矩阵适合日常需求吗？ |
| #11 证据难填 | 你在本地开发时"证据"怎么写？ |

### Step 4.2 对照改进结果

对每个碰撞点，检查方法论是否已经做了改进：

| 碰撞点 | 改进在哪里 | 你去看一下 |
|---|---|---|
| #1 | `execution/templates/triage-quick-card.md` | 11 个问题 30 秒定级 |
| #2 | `execution/BROWNFIELD-QUICKSTART-CHECKLIST.md` 开头的 M 级说明 | 三步可合并 |
| #3 | `.cursor/rules/quirk-registry.mdc` + `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 第 4 节 | QUIRK 登记簿 |
| #4 | `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md` 7.5 节 | Pending Confirmations |
| #5 | `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md` 第 4 节 | @openspec 说明和 fallback |
| #6 | `execution/templates/m-medium-approval-checklist.md` | 审批 checklist |
| #7 | `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` M-Medium 审批结果 | 三种结论 |
| #8 | `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 数据翻译约定 | 翻译/格式化检查 |
| #9 | `execution/M-MEDIUM-EXECUTION-GUIDE.md` 第 5 节 | 交叉迭代节奏 |
| #10 | `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` M 级精简矩阵 | 7 列版 |
| #11 | `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` 8.3 节 | Evidence Location 指引 |

---

## Phase 5：总结和行动（5 分钟）

复盘完成后，请回答以下问题（可以写在纸上或和团队讨论）：

### 关于方法论本身

1. 这套方法论对你的项目适用吗？哪些部分最有价值？
2. 11 个碰撞点中，哪些在你的实际项目里也会遇到？
3. QUIRK 这个概念对你有启发吗？你的项目里有多少类似的"非直觉行为"？

### 关于提示词

4. 你觉得哪份提示词最实用？
5. 你觉得哪份提示词还需要调整（结合你自己项目的技术栈）？
6. "探索结果手动搬运到后续提示词"这个衔接动作，你觉得合理吗？有更好的方式吗？

### 关于团队推广

7. 如果你要向团队推广，你会先试 S 级、M 级还是 L 级？
8. 你的团队中谁最适合先试用？TL？资深开发？新人？
9. 试点第一个需求，你会选什么类型的需求？

### 下一步行动

写下你准备做的前 3 个行动项：

```
1. _______________
2. _______________
3. _______________
```

---

## 附：快速复盘路径（30 分钟版）

如果时间有限，只走以下步骤：

1. 【5 分钟】读 `frontend/src/components/` 下的 4 个组件，找 QUIRK
2. 【5 分钟】读 `prompts/01-explore.md`，看探索提示词怎么写
3. 【5 分钟】读 `prompts/05-backend-impl.md`，看碰撞点 #8（字段翻译冲突）
4. 【5 分钟】读 `artifacts/quirk-registry.md`，看 QUIRK 登记簿
5. 【5 分钟】读 `DEMO-WALKTHROUGH.md` 最后的碰撞点汇总表
6. 【5 分钟】回答 Phase 5 中的 9 个问题

