# AI 编程落地方案最终结论纪要

> 文档性质：最终结论版纪要，用于收敛多轮专家评审后的统一判断。  
> 目标：明确哪些结论已经确认、哪些建议应修正后采纳、哪些事项应进入下一轮正式文档修订。  
> 范围：聚焦棕地项目 AI 编程落地方案及其核心资料体系，不覆盖仓库中的其他业务专项文档。  
> 使用边界：这份纪要用于理解“为什么形成当前治理与资料体系”，不作为日常需求执行入口；日常落地请优先回到治理手册、流程入口页和主案例导航。

## 推荐连读

如果你要把这份纪要和当前资料体系一起看，建议继续参考：

1. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`：看当前已经落地的治理总手册；
2. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`：看团队日常入口如何使用；
3. `core/AI-ADOPTION-DOCUMENT-CATALOG.md`：看当前最终形态资料清单；
4. `review/EXPERT-REVIEW-PACK-LIST.md`：看对外专家评审包怎么发。

---

## 1. 纪要结论摘要

多轮专家讨论后的统一结论是：

1. 当前这套 `OpenSpec + Superpowers + 棕地规则基线 + 验收/交付证据` 的总体方向成立；
2. 它已经明显优于“AI 直接自由写代码”的粗放模式；
3. 但若要从“方法论资料”升级为“团队级制度”，仍必须补上：
   - 完整分流路由
   - 基线治理机制
   - 结构化证据门禁
   - 可推广的 M 级快车道

统一建议结论：

> **Adopt with changes**  
> 即：保留当前体系主干，不推翻现有资料，但在正式制度化前，必须完成一轮以“分流治理、规则治理、证据治理”为核心的升级。

---

## 2. 已确认结论

以下结论已经被多位专家共同确认。

## 2.1 当前体系的优势判断成立

1. 对棕地项目的定位准确；
2. 对 AI 发散行为的约束思路正确；
3. 对“AI 自证完成”的警惕和证据门禁方向正确；
4. 用 `project-management` 作为主案例，比简单 Todo 示例更具代表性；
5. 当前资料已经具备较高的实战导向和企业工程语感。

## 2.2 三个核心风险判断成立

1. 一刀切流程会引发“流程过重”的推广阻力；
2. AI 编程把更多高价值思考责任前移给人；
3. `.cursor/rules/*.mdc` 若无治理机制，后续容易失效或腐化。

## 2.3 四个优化方向成立

1. 引入变更规模与风险分流；
2. 让规则基线静默生效、受控升级；
3. 为 M 级需求建立审批快车道；
4. 让机器证据成为门禁核心，而不是让 AI 或开发者口头宣称“完成”。

---

## 3. 需修正后采纳的建议

以下建议方向正确，但不能原样照搬，必须经过修正后再进入正式文档。

## 3.1 分流不能只按工时

不能只采用“`S/M/L` + 预计耗时”的简单 T-Shirt Size。

必须改成至少四个维度共同判断：

1. 工作量
2. 风险等级
3. 影响面
4. 不确定性

原因：

1. 很多“只改几行代码”的变更，实际可能触及权限、租户、导出、旧接口契约、事务边界；
2. 这些变更绝不能因为工时小就自动进入轻流程。

## 3.2 M 级快车道不能只审 `design.md`

M 级快车道应保留，但审批对象至少包括：

1. `proposal`
2. `spec`
3. `design`
4. `tasks`

原因：

1. `design.md` 只能覆盖实现路径，不能单独兜住范围、需求场景和验证项；
2. 真正容易漏掉的，往往是 requirement / scenario / 回归与验证项，而不是单纯接口结构。

## 3.3 机器证据不能简化成“贴一段日志”

机器证据方向被确认，但形式必须升级。

不能只接受：

1. 一段 console 输出
2. 一张局部截图
3. 一句“本地执行通过”

应至少要求：

1. commit / PR 关联
2. 命令
3. 环境
4. 结果
5. 证据位置
6. 结论
7. 剩余风险

## 3.4 静默基线不能演化成“前线无反馈权”

规则基线默认静默生效是对的，但必须保留一线开发的反馈入口。

正确口径应是：

1. 一线开发默认消费 rules；
2. TL / 架构师集中维护；
3. 一线开发可发起 `rule conflict / rule gap / rule outdated` 反馈；
4. 规则更新走专门 PR 和 review。

---

## 4. 不建议采纳的极端做法

以下做法不建议进入正式制度。

1. 所有需求都走完整 OpenSpec 重流程；
2. 所有 M 级需求只看 `design.md` 就开始开发；
3. Reviewer 只检查有没有贴日志，而不检查日志证明了什么；
4. 任何小需求都默认可以跳过契约、验证和兼容性判断；
5. 让 `.cursor/rules/*.mdc` 只由少数人“黑箱维护”，一线完全无反馈通道。

---

## 5. 最优方案收敛结论

最终建议采用的正式方向为：

> **按变更规模与风险双轴分流 + 规则基线静默生效 + M 级审批快车道 + 结构化机器证据门禁**

其含义如下：

## 5.1 分流治理

目标：

1. 避免小需求滥用重流程；
2. 避免高风险小改动误走轻流程；
3. 让团队知道何时该轻、何时必须重。

## 5.2 规则治理

目标：

1. 让规则像基础设施一样存在；
2. 避免每次需求重复提炼规则；
3. 避免 rules 长期失效没人更新。

## 5.3 M 级快车道

目标：

1. 让最常见的中型需求有高频、低摩擦路径；
2. 保持速度，同时保住范围、契约和验证边界。

## 5.4 证据治理

目标：

1. 让“完成”从口头表述变成有证据支持的结论；
2. 让 Reviewer 与发布负责人有足够依据做判断；
3. 让“机器执行过”与“足以发布”明确区分。

---

## 6. 当前文档体系核查结论

本轮核查的核心文档包括：

1. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
2. `core/PROJECT-MANAGEMENT-DOC-MAP.md`
3. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
4. `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
5. `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
6. `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
7. `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
8. `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
9. `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
10. `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
11. `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
12. `openspec/changes/project-management/`

总体判断：

1. 现有主干文档质量较高；
2. 约束意识、证据意识、棕地意识都已经到位；
3. 当前体系已经补入治理层、案例层和 `M-Medium` 最小示例；
4. 完整流程、快车道、规则治理和证据门禁之间的边界已明显更清楚；
5. 现阶段主问题已不再是“缺少治理层”，而是后续如何在团队推广中持续使用和校准。

也就是说：

> 当前文档体系已经从“完整方法说明”升级为“带治理层的完整套件”，后续重点从补文档转向试点、评审和持续校准。

---

## 6.1 本轮已落地的新增资产

在前述结论基础上，当前体系已新增并接入：

1. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

它们分别解决：

1. 分流治理、规则治理、结构化证据门禁；
2. `M-Medium` 场景如何使用最小 OpenSpec，而不误用高风险完整主案例。

---

## 7. 现有文档体系优化建议映射

以下建议按文档逐份给出。

## 7.1 `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`

当前价值：

1. 作为总入口已经足够清晰；
2. 能把现有资料按用途和角色串起来。

当前状态：

1. 已接入治理总手册；
2. 已改成“先判断变更级别，再进入对应文档路径”。

后续建议：

1. 在团队试点后观察入口是否仍有理解门槛；
2. 必要时继续压缩首次阅读路径。

## 7.2 `core/PROJECT-MANAGEMENT-DOC-MAP.md`

当前价值：

1. 适合作为项目管理主案例的阅读导航；
2. 有利于培训和主案例讲解。

当前状态：

1. 已增加 `S / M / L / 高风险` 分流提示；
2. 已补入 `M-Medium` 最小示例入口。

后续建议：

1. 在线下培训或试点后观察导航是否还需要进一步简化。

## 7.3 `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`

当前价值：

1. 方法论总文档扎实；
2. 真相源、执行源、工具 fallback、AI 边界都写得比较清楚。

当前状态：

1. 已新增“流程分流原则”；
2. 已明确它主要服务 `L / High Risk`，并承接 `M-Medium` 升级路径。

后续建议：

1. 在试点后继续观察是否还需要进一步压缩非高风险读法。

## 7.4 `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

当前价值：

1. 工具前置与 fallback 说明已经比较成熟；
2. 能有效阻断“假执行”。

当前缺口：

1. 还没有把 “M 级快车道里使用 `@openspec-ff-change`” 的标准方式纳入；
2. 还没有把“结构化机器证据”与 CI / PR / commit 绑定要求写清。

建议优化方向：

1. 补充 M 级路径的工具用法；
2. 补充“证据与 revision / PR / CI run 的绑定建议”；
3. 补充轻流程和完整流程下工具使用差异。

## 7.5 `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`

当前价值：

1. 规则如何从项目中提炼出来，写得很扎实；
2. 巡查证据化要求是当前体系的重要资产。

当前缺口：

1. 仍然更像“如何建立基线”；
2. 还没有升级为“如何治理基线”。

建议优化方向：

1. 增加“基线治理机制”章节；
2. 写清：
   - owner
   - 更新触发条件
   - 一线开发反馈方式
   - 专门 PR 更新方式
3. 明确：
   - 日常开发默认消费 rules
   - 不是每个需求都重新提炼基线

## 7.6 `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`

当前价值：

1. 提示词约束写得比较实战；
2. 已经有证据、待确认、契约边界意识。

当前状态：

1. 已新增 `S-Low / M-Medium / L / High Risk` 起手提示词；
2. 已显式固化 `M-Medium` 快车道话术。

后续建议：

1. 在真实项目里继续检验这些起手话术是否足够稳定、易用。

## 7.7 `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

当前价值：

1. 是完整流程最强执行手册；
2. 需求到归档的链路已经很清晰。

当前状态：

1. 已明确它默认用于高风险 / 完整流程场景；
2. 已在入口文档中重新定位为 `L / High Risk` 路径主手册。

后续建议：

1. 保持它的完整流程定位，不再把它当成所有需求默认入口。

## 7.8 `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`

当前价值：

1. 主案例非常适合解释为什么需要棕地探索、规则、OpenSpec；
2. 案例复杂度合理，具有代表性。

当前状态：

1. 已明确主案例主要用于完整流程讲解；
2. 已补充“如果缩成 `M-Medium` 应该怎么走”。

后续建议：

1. 在团队试点中验证大家是否能正确区分主案例与中型示例的用途。

## 7.9 `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

当前价值：

1. 验收矩阵已经比较成熟；
2. 已经开始强调请求/响应样例、导出样例、联调证据。

当前状态：

1. 已补入结构化证据模板；
2. 已补入不同风险等级的证据强度要求。

后续建议：

1. 在真实 PR 和验收场景中继续检验矩阵字段是否足够可填写。

## 7.10 `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

当前价值：

1. 给 `M-Medium` 提供了独立骨架；
2. 避免把高风险主案例误用成所有需求的默认模板。

当前风险：

1. 如果团队理解不到位，仍可能把“最小示例”误解为“永远可以轻流程”。

后续建议：

1. 在线下培训或试点中明确：
   - 它是中型快车道示例
   - 不是跳过治理和证据的理由
## 7.11 `openspec/changes/project-management/`

当前价值：

1. proposal / spec / design / tasks 的主案例质量较高；
2. 已经把权限、租户、主数据、导出语义写进去了。

当前缺口：

1. 当前 change 还是“完整流程 OpenSpec 主案例”；
2. 它不适合作为所有需求的默认模板。

建议优化方向：

1. 明确保留它作为：
   - 高风险 / 完整流程主示例
2. 不建议拿它直接作为 S 或 M 级需求的默认模板
3. 后续如进入正式修订，可考虑再补一个：
   - M 级最小 OpenSpec 示例

---

## 8. 建议的正式修订顺序

如果下一步要改文档，不建议同时全改。  
建议按下面顺序推进：

## Phase 1：先补治理层文档

优先补：

1. 分流治理说明
2. 基线治理说明
3. 结构化机器证据说明

目标：

1. 先把“新结论”落地成统一依据；
2. 避免先改各主文档，后面口径又变。

## Phase 2：再改总入口与导航

优先改：

1. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
2. `core/PROJECT-MANAGEMENT-DOC-MAP.md`

目标：

1. 先把入口分流清楚；
2. 让团队知道什么情况该看哪套资料。

## Phase 3：再改方法论与执行手册

优先改：

1. `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
2. `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
3. `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

目标：

1. 把“完整流程”与“快车道”关系理顺；
2. 避免执行手册继续被所有需求默认套用。

## Phase 4：最后改案例、验收矩阵和主示例

优先改：

1. `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
2. `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
3. 必要时补充新的 M 级 OpenSpec 示例

目标：

1. 把案例和证据强度升级到新标准；
2. 让主案例更适合作为团队演示和试点教材。

---

## 9. 最终结论

这轮核查后的最终结论是：

1. 现有资料体系不需要推翻；
2. 主干质量已经足够好；
3. 真正要做的不是“重写全部文档”，而是为现有体系补上一层“分流治理层”；
4. 一旦分流治理层建立完成，现有完整流程文档将更有威信，因为它只会被用于真正该重的场景。

一句话总结：

> 当前体系的主问题不是“内容不对”，而是“还没有把完整流程、快车道、静默基线和证据门禁纳入同一套治理结构”；下一步优化应先补治理层，再改入口与执行层。

---

## 10. Demo 碰撞验证追记（2026-03-30）

在完成治理层补建后，进行了一轮 Demo 实战碰撞验证：

### 验证方式

搭建模拟棕地项目（含 7 个 QUIRK），以"设备管理模块"为需求，严格按 M-Medium 快车道走完全流程。

### 核心发现

1. 方法论宏观架构完全成立，11 个碰撞点全部在实操细节层面
2. M 级是最高频路径，需要独立的端到端执行指南
3. QUIRK（历史怪癖）是棕地项目的核心隐患，需要专门的登记和传递机制
4. 提示词的实操版和原版有显著差异，需要升级

### 已落地的改进

1. 新增 M-Medium 独立执行指南
2. 新增 QUIRK 登记簿（mdc 文件 + 概念说明）
3. 提示词手册全面升级（QUIRK 输出、数据翻译检查、约束传递机制）
4. 新增独立模板文件（分流卡、审批 Checklist、精简证据模板）
5. 变更回写流程增加审批阶段触发路径

### 验证结论

> Demo 碰撞验证确认：当前体系在补入 M 级执行指南、QUIRK 体系和提示词升级后，已具备团队试点推广条件。

详见：`review/DEMO-COLLISION-REPORT.md`、`demo-brownfield-project/README.md`
