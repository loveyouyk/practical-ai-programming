# AI 编程落地治理手册

> 文档性质：正式治理手册，用于指导团队在棕地项目中推广 `OpenSpec + Superpowers + 规则基线 + 验收/交付证据`。  
> 目标：在不推翻现有资料体系的前提下，为团队提供统一的分流治理、规则治理和结构化证据门禁。  
> 适用范围：面向棕地项目中的 AI 编程落地，不替代现有研发管理制度、测试制度、发布制度和人工审批责任。  
> 使用边界：这份手册只负责回答“当前需求该走哪条路径、什么证据才算数”，不直接替代具体执行手册、案例导航和提示词手册。

## 推荐连读

如果你是第一次进入这套体系，建议按这个顺序继续看：

1. `knowledge/core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`：先看整套资料总入口；
2. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`：如果工具环境不确定，先确认 `/opsx-*` 和 Superpowers 的可用性；
3. `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md`：再进入项目管理主案例导航；
4. `knowledge/core/AI-ADOPTION-DOCUMENT-CATALOG.md`：需要盘点全量资料时再看；
5. `knowledge/review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md`：需要理解治理升级背景时再看。

---

## 1. 为什么需要这份治理手册

我们现有资料已经解决了很多关键问题：

1. 强调棕地项目约束，而不是绿地演示；
2. 用 `OpenSpec` 固化需求、设计和任务；
3. 用规则基线约束 AI 不要自由发挥；
4. 用验收矩阵、交付模板和验证门禁阻断“AI 自证完成”。

但如果要把这套方法从“会用的人能用”升级到“团队可以长期推广”，仍然必须解决四个现实问题：

1. 不是所有需求都适合走完整重流程；
2. 规则基线不能每次需求都重建，也不能无人维护；
3. 团队最常见的是中型需求，必须有低摩擦快车道；
4. “做完了”必须变成有结构、有追溯性的证据结论。

这份手册就是为了解决这四个问题。

---

## 2. 这份手册与现有文档的关系

这份手册不替代现有主文档，而是为它们增加治理层。

## 2.1 它解决什么

它主要解决：

1. 什么需求走轻流程，什么需求必须走重流程；
2. `.cursor/rules/*.mdc` 谁维护、何时更新、如何反馈；
3. 什么样的机器证据才算有效；
4. 团队角色在 AI 编程流程中的分工边界。

## 2.2 它不替代什么

它不替代：

1. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md` 的完整方法论；
2. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 的巡查与提炼方法；
3. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 与 `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` 的提示词手册；
4. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` 的验收管理；
5. 仓库本身的 Git、PR、测试、发布流程。

## 2.3 推荐使用顺序

建议顺序如下：

1. 先用本手册判断治理路径；
2. 再进入对应的执行文档；
3. 最后把结果回落到验收矩阵、交付模板和 PR。

---

## 3. 核心治理原则

## 3.1 不一刀切

不是所有需求都走完整 OpenSpec 重流程，但也不是小需求就天然可以轻放。

## 3.2 不让 AI 自证完成

AI 可以辅助生成草案、计划、代码和检查清单，但不能单独宣称：

1. 需求已闭环；
2. 功能已达标；
3. 具备发布评审条件。

这些结论必须由结构化证据和人工判断共同得出。

## 3.3 规则是基础设施

`.cursor/rules/*.mdc` 默认视为项目基础设施，而不是每次需求都重新提炼的临时产物。

## 3.4 高风险场景必须重流程

涉及权限、租户、导出语义、旧接口契约、事务边界、跨模块高不确定性行为时，宁可走重，不可误走轻。

## 3.5 口头完成不算完成

以下内容不能视为完成依据：

1. “我本地测过了”
2. “AI 说通过了”
3. “看起来没问题”
4. “应该不会影响旧逻辑”

---

## 4. 分流治理模型

## 4.1 为什么不能只按工时判断

只按工时或代码量判断会误伤很多棕地项目需求。

例如：

1. 改 3 行代码也可能改坏租户过滤；
2. 只动一个导出按钮，也可能改变导出语义；
3. 一个简单字段回显问题，也可能牵动旧接口契约。

所以分流必须至少同时看四个维度：

1. 工作量
2. 风险等级
3. 影响面
4. 不确定性

## 4.2 四个判断维度

### 工作量

看改动规模是否明显超出微小修补。

### 风险等级

看是否触及：

1. 权限
2. 租户
3. 导出语义
4. 旧接口契约
5. 事务边界
6. 发布敏感资产

### 影响面

看是否涉及：

1. 单页面 / 单接口
2. 单模块
3. 多模块
4. 多服务
5. 旧客户端或旧页面兼容

### 不确定性

看是否存在：

1. 历史逻辑不清
2. 主数据来源不清
3. 字段语义不清
4. 复用边界不清
5. 发布方式不清

## 4.3 硬性升级条件

即使工作量看起来很小，只要命中以下任一项，也不能走 `S-Low`：

1. 涉及权限模型变更；
2. 涉及租户或数据范围逻辑；
3. 涉及导出语义变化；
4. 涉及旧接口返回结构变化；
5. 涉及事务边界或资金、账务、结算等敏感逻辑；
6. 涉及高不确定性的历史链路；
7. 涉及数据库 migration 且回滚复杂。

---

## 5. 三条治理路径

## 5.1 `S-Low`：微小且低风险

### 典型场景

1. 单一字段展示修正；
2. 文案调整；
3. 明确边界的样式微调；
4. 范围非常清晰的简单 bug。

### 推荐流程

1. 默认加载现有 `.cursor/rules`；
2. 直接进入实现；
3. 保留最小机器证据；
4. 通过 PR 做人工 review；
5. 必要时补一条最小验收记录。

### 明确不允许

以下情况不得进入 `S-Low`：

1. 改权限；
2. 改租户逻辑；
3. 改导出语义；
4. 改旧接口返回结构；
5. 改事务边界；
6. 涉及高不确定历史逻辑。

### 最低证据要求

至少保留：

1. 变更说明；
2. 关键验证命令或操作；
3. 结果结论；
4. 剩余风险说明。

## 5.2 `M-Medium`：中型且风险可控

### 典型场景

1. 标准单表或双表 CRUD；
2. 独立页面新增；
3. 标准第三方 API 对接；
4. 边界清晰的功能增强；
5. 需要规格约束但不属于高风险核心改造的业务模块。

### 推荐流程

1. 先做最小探索；
2. 使用 `@openspec-ff-change` 或等价方式生成 `proposal/spec/design/tasks`；
3. 人工快速审批；
4. 审批通过后再开发；
5. 必须补验收矩阵；
6. 必须提交结构化机器证据。

### 人工审批最少覆盖

1. `proposal` 的范围和非目标；
2. `spec` 的 requirement / scenario；
3. `design` 的接口、字段和复用边界；
4. `tasks` 的验证和回归项。

### 标准起手话术

```text
这是一个 M 级需求。请先基于当前项目规则基线做最小探索，再使用 `@openspec-ff-change` 生成 `proposal/spec/design/tasks`。生成后停下，等待我审批。
```

### 何时升级到高风险

只要审批中发现以下情况之一，应升级到完整流程：

1. 边界不清；
2. 字段契约不清；
3. 主数据来源不清；
4. 验证项明显缺失；
5. 影响的旧链路超出原判断；
6. 风险高于原先评估。

## 5.3 `L` 或 `High Risk`：大型或高风险

### 典型场景

1. 多租户；
2. 权限体系改造；
3. 核心历史资产调整；
4. 跨多个服务或模块联动；
5. 高不确定性需求；
6. 高敏感业务，如结算、账务、资金、审计关键链路。

### 推荐流程

1. 完整探索；
2. 校对现有规则基线；
3. 完整 OpenSpec；
4. 完整实施计划；
5. 多角色执行；
6. 完整验收矩阵；
7. 完整交付模板；
8. 发布前完整验证门禁。

### 默认要求

1. 不允许跳过探索；
2. 不允许只看 `design` 就开工；
3. 不允许缺少兼容性和回滚项；
4. 不允许没有证据就宣称“具备发布评审条件”。

---

## 6. 不同路径如何接入现有文档

## 6.1 `S-Low`

优先使用：

1. `.cursor/rules/*.mdc`
2. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
3. 现有 PR 与最小验证证据

一般不强制使用：

1. 完整 OpenSpec
2. 完整交付模板
3. 完整执行提示词手册

## 6.2 `M-Medium`

优先使用：

1. 本治理手册
2. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
3. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
4. 最小 OpenSpec change
5. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

按需参考：

1. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
2. `openspec/changes/project-management/`

## 6.3 `L / High Risk`

优先使用：

1. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
2. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
5. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
6. `openspec/changes/project-management/`

---

## 7. 规则基线治理

## 7.1 治理目标

规则治理的目标不是“写更多规则”，而是：

1. 让 AI 默认遵循项目真实约束；
2. 避免每次需求都重新提炼基线；
3. 避免过时规则长期误导 AI；
4. 保留一线开发的反馈入口。

## 7.2 规则分层

建议至少分三层：

1. 项目级规则
2. 后端规则
3. 前端规则

作用边界：

1. 项目级规则约束流程、真相源、风险和验证纪律；
2. 后端规则约束 Java / Spring Boot / 接口 / 数据 / 测试模式；
3. 前端规则约束页面、组件、路由、API 封装和交互模式。

## 7.3 规则所有权

建议 owner 如下：

1. 项目级规则：TL / 架构师
2. 后端规则：后端负责人
3. 前端规则：前端负责人

## 7.4 默认执行口径

1. 一线开发默认消费现有 rules；
2. 不是每个需求都重新提炼规则；
3. 日常需求分支不建议顺手直接改 rules；
4. rules 的变更应走专门 PR 和 review。

## 7.5 更新触发条件

以下情况触发规则评估：

1. 高风险需求结束后；
2. 团队多次重复踩同一类坑；
3. 历史项目模式发生稳定变化；
4. 现有 rules 与真实项目冲突；
5. 现有 rules 无法覆盖新的稳定复用模式。

## 7.6 一线开发反馈机制

允许一线开发提交以下三类反馈：

1. `rule conflict`
2. `rule gap`
3. `rule outdated`

推荐最小反馈格式：

```md
## Rule Feedback

### Type
- rule conflict / rule gap / rule outdated

### Rule File
- 

### Observed Problem
- 

### Real Evidence
- 

### Suggested Handling
- 
```

## 7.7 规则更新方式

建议采用：

1. 单独 PR；
2. 写明更新原因；
3. 写明影响范围；
4. 必要时同步更新提示词手册、清单和案例文档。

## 7.8 与巡查 Playbook 的关系

`knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 解决的是：

1. 如何巡查项目；
2. 如何从项目中抽出规则；
3. 如何把证据与规则绑定。

本治理手册解决的是：

1. 已经有了 rules 之后，团队怎么用；
2. 谁维护；
3. 何时更新；
4. 冲突怎么反馈。

---

## 8. 结构化机器证据门禁

## 8.1 为什么不能只贴日志

“贴一段日志”最大的问题不是形式简单，而是没有上下文。

如果没有这些信息：

1. 谁触发的
2. 在什么版本上触发的
3. 在什么环境触发的
4. 结果证明了什么
5. 还有什么没证明

那这段日志就很难作为治理证据。

## 8.2 最小证据块模板

推荐统一采用如下最小格式：

```md
## Verification Evidence

### Change / Commit / PR
- 

### Command Or Action
- 

### Environment
- 

### Result
- 

### Evidence Location
- 

### Conclusion
- 

### Residual Risk
- 
```

## 8.3 证据门禁原则

1. 没有机器证据，不进入“已完成”；
2. 有机器证据，但不能证明关键风险，不进入“具备发布评审条件”；
3. 机器证据证明执行过，人类 reviewer 判断是否充分；
4. 证据必须能回溯到 change、commit、PR 或具体产物位置。

## 8.4 不同路径的证据强度

### `S-Low`

至少需要：

1. 关键命令或操作；
2. 结果；
3. 结论；
4. 剩余风险。

### `M-Medium`

至少需要：

1. change / commit / PR 关联；
2. 关键测试或验证命令；
3. 环境；
4. 结果；
5. 证据位置；
6. 验收矩阵引用；
7. 剩余风险。

### `L / High Risk`

至少需要：

1. 完整 change / commit / PR 关联；
2. 探索证据；
3. 测试证据；
4. 联调证据；
5. 验收矩阵；
6. 交付模板；
7. 发布前 smoke test / migration / rollback 证据；
8. 剩余风险与人工结论。

## 8.5 什么叫“证据充分”

证据充分不是看数量，而是看它是否覆盖当前风险。

例如：

1. 改权限，只跑 happy path 不够；
2. 改租户，只看单租户结果不够；
3. 改导出，只证明能下载文件不够；
4. 改接口契约，只贴页面截图不够。

## 8.6 Reviewer 的判断标准

Reviewer 不是看“有没有贴日志”，而是看：

1. 证据是否真实；
2. 证据是否与当前变更相关；
3. 证据是否覆盖关键风险；
4. 结论是否被证据支撑；
5. 剩余风险是否被明确标注。

---

## 9. 角色职责

## 9.1 一线开发

主要职责：

1. 按分流标准选择正确路径；
2. 在现有规则基线下使用 AI；
3. 对 `M/L` 级需求补齐 artifacts 和证据；
4. 发现规则与项目冲突时发起反馈。

不承担的职责：

1. 每次需求都重新提炼项目级规则；
2. 单独决定高风险需求是否可以走轻流程；
3. 用主观口头说明替代验证证据。

## 9.2 Tech Lead / 架构师

主要职责：

1. 维护 `.cursor/rules/*.mdc`；
2. 审批 `M/L` 级需求的范围、边界和设计；
3. 决定存在争议时的流程分级；
4. 在复盘后沉淀新规则。

## 9.3 Reviewer

主要职责：

1. 看 PR 是否与 change 范围一致；
2. 看机器证据是否真实且足够；
3. 看是否存在兼容性遗漏；
4. 证据不足时直接 reject。

## 9.4 发布负责人 / 验收负责人

主要职责：

1. 检查验收矩阵；
2. 检查交付模板和发布清单；
3. 做最终发布评审；
4. 确认当前是否具备进入发布评审条件。

---

## 10. 团队执行口径

## 10.1 明确允许的事

1. `S-Low` 需求不走完整 OpenSpec；
2. `M-Medium` 需求走最小探索 + 最小 OpenSpec + 快速审批；
3. `L / High Risk` 需求走完整流程；
4. 一线开发对 rules 提反馈；
5. Reviewer 以证据不足为理由 reject。

## 10.2 明确不允许的事

1. 所有需求一刀切走完整重流程；
2. 所有中型需求只看 `design.md` 就开工；
3. 因为工作量小就跳过契约和兼容性判断；
4. 把“本地执行通过”当成最终完成依据；
5. 让 rules 黑箱维护且无反馈通道。

---

## 11. 推广建议

## Phase 1：先建立共识

目标：

1. 团队理解为什么要分流；
2. 团队理解 rules 是基础设施；
3. 团队理解 AI 不能自证完成。

## Phase 2：先试点 `M-Medium`

目标：

1. 在 1-2 个中型需求上试运行；
2. 观察审批成本、开发体验和证据门禁效果；
3. 不急着一开始就全量推高风险完整流程。

## Phase 3：再推广到高风险场景

目标：

1. 在多租户、权限、导出、跨模块场景中正式启用完整流程；
2. 复盘后更新 rules 和 prompt 资产。

## 11.4 试点选择建议

适合作为首批试点的需求：

1. 标准 CRUD 模块；
2. 单模块页面新增；
3. 标准导出或查询增强；
4. 单一第三方 API 对接。

不建议作为首批试点的需求：

1. 权限体系改造；
2. 多租户逻辑重构；
3. 核心账务、清分、资金结算类场景。

---

## 12. 与现有资料体系的落地关系

后续对现有资料的改造建议按这个顺序进行：

1. 先把本治理手册作为治理总依据；
2. 再把 `knowledge/core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md` 和 `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md` 接入分流入口；
3. 再把 `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`、`knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`、`knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` 接入新的治理口径；
4. 最后再升级案例文档、验收矩阵和主示例。

---

## 13. 一句话总结

> 这套治理的核心，不是把重流程废掉，而是把它准确地用在真正高风险的变更上；让 rules 静默生效，让中型需求高频顺滑，让结构化证据真正替代“口头完成”。  

