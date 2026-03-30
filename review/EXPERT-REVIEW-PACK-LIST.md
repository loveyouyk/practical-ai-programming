# AI 开发资料专家评审包清单

> 目标：给外部专家一份清晰、可分发、可评审的资料清单，帮助他们判断这套 `OpenSpec + Superpowers + 棕地项目规则基线` 方法体系是否真的能指导团队在棕地项目中更准确、更高效地完成客户需求和前后端功能落地。  
> 适用范围：仅覆盖本次整理出的 `AI 开发流程资料`，不包含仓库中其他业务专题文档。  
> 评审原则：重点看是否准确、是否有约束、是否符合棕地项目实战、是否避免夸大 AI 能力、是否能真正帮助团队推广落地。

## 推荐连读

如果你要边看评审包边理解这套资料，建议同时参考：

1. `core/AI-ADOPTION-DOCUMENT-CATALOG.md`：看当前全量资料分层；
2. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`：看治理总口径；
3. `review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md`：看最终结论和采纳边界；
4. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`：看团队内部默认入口。

---

## 1. 评审包范围说明

本评审包主要面向以下问题：

1. 这套方法是否真正适合棕地项目 AI 开发；
2. 是否把 `OpenSpec`、`Superpowers`、规则基线、测试验证、PR、发布衔接清楚；
3. 是否对 AI 能力边界、证据要求、需求回写、上线门禁做了足够约束；
4. 是否能帮助团队更准确地还原真实设计和功能需求，而不是让 AI 自由发挥；
5. 是否能帮助前后端更高效地对齐接口、页面、交互、权限、租户和交付边界。

不在本次评审包范围内的内容：

1. 仓库中的其他业务专项方案文档；
2. 与本套方法体系无关的历史安全整改、运营商升级、清分方案等文档；
3. 实际业务代码实现正确性本身。

建议专家评审时特别警惕以下风险：

1. 提示词看起来完整，但没有要求真实输入、真实证据和待确认项
2. 流程看起来闭环，但无法指导前后端对齐到真实页面和真实接口
3. 文档看起来严谨，但实操时仍然需要大量口头补充
4. 把 AI 的草案能力误写成 AI 的事实判断能力

---

## 2. 最小必看评审包

如果外部专家时间有限，优先发这 12 份。

### 2.1 总入口与阅读路径

1. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
   作用：整套资料总入口。
   建议重点评审：
   - 文档分层是否清楚
   - 是否便于首次接触者理解
   - 是否存在误导性的入口描述

2. `core/PROJECT-MANAGEMENT-DOC-MAP.md`
   作用：以“项目管理模块”主案例串联主流程。
   建议重点评审：
   - 路径是否合理
   - 主案例是否足够代表真实业务复杂度
   - 是否便于线下培训或团队推广

3. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
   作用：治理总手册，统一分流治理、规则治理和结构化证据门禁。
   建议重点评审：
   - 分流标准是否足够严谨
   - 规则治理是否具备长期可维护性
   - 证据门禁是否真正可落地

### 2.2 方法论与边界

4. `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
   作用：方法论总文档。
   建议重点评审：
   - OpenSpec 与 Superpowers 的职责边界是否准确
   - “真相源”与“执行源”定义是否清楚
   - AI 能力边界、验证门禁、上线判断是否严谨

5. `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
   作用：工具前置与 fallback 说明。
   建议重点评审：
   - 是否避免了“工具假执行”
   - fallback 是否真实可落地
   - 是否对 `/opsx-*` 与人工流程的替代关系说清楚

6. `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
   作用：棕地项目巡查与规则提炼手册。
   建议重点评审：
   - 巡查是否证据化
   - 规则抽取是否来自真实项目而不是空泛最佳实践
   - 是否足以约束 AI 后续行为

### 2.3 提示词与执行手册

7. `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
   作用：通用 AI 提示词手册。
   建议重点评审：
   - 提示词是否足够约束 AI
   - 是否要求证据、待确认、契约、回写
   - 是否存在夸大 AI 判断能力的地方
   - 是否能够指导前后端准确表达页面、接口和联调需求

8. `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
   作用：主流程执行手册。
   建议重点评审：
   - 步骤是否闭环
   - 从需求到上线的提示词是否具备可操作性
   - 是否把验证、PR、上线、归档讲清楚
   - 是否存在模糊提示导致 AI 自行补全业务事实的风险

9. `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
   作用：主案例实战说明。
   建议重点评审：
   - 案例是否足够接近真实企业功能
   - RP、主数据、权限、租户、导出这些复杂点是否体现出来
   - 是否真正体现“先探索、再约束、再实现”
   - 是否足以支撑前后端围绕同一需求事实协同推进

### 2.4 验收与交付

10. `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
   作用：功能验收矩阵模板与示例。
   建议重点评审：
   - 是否真正把“功能完成”变成“有证据的完成”
   - 验收状态定义是否严谨
   - 是否避免把 AI 自评当成验收结论
   - 是否足以支撑客户需求、前端呈现和后端行为的一致性确认

11. `execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
    作用：统一交付模板。
    建议重点评审：
    - 是否能支撑交付沉淀
    - 是否覆盖工具环境、验证、PR、发布、归档证据
    - 是否避免“模板看起来完整，实际上无法填写”

12. `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
    作用：给团队一个 `M-Medium` 场景下的最小 OpenSpec 参考。
    建议重点评审：
    - 是否真的能区分中型快车道与高风险完整流程
    - 是否足够轻量但仍保持约束
    - 是否存在把中型需求过度简化的风险

---

## 3. 建议补充评审包

如果专家愿意更深入看流程闭环，建议再补这几份。

1. `execution/BROWNFIELD-QUICKSTART-CHECKLIST.md`
   作用：快速执行清单。
   建议重点评审：
   - 是否适合作为团队日常最小流程
   - 是否存在因过度压缩而丢失关键约束

2. `execution/BROWNFIELD-CHANGE-UPDATE-WORKFLOW.md`
   作用：开发中发现需求遗漏时的回写流程。
   建议重点评审：
   - 需求变更处理是否严谨
   - 是否真的把“先改 spec 再改代码”落到可执行流程

3. `review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md`
   作用：多轮专家讨论后的最终结论纪要。
   建议重点评审：
   - 结论是否准确反映争议点
   - 采纳与不采纳项是否边界清楚
   - 是否能作为后续修订的统一依据

4. `history/AI-ADOPTION-DISCUSSION-PROPOSAL.md`
   作用：治理方案历史讨论稿。
   使用边界：
   - 仅在专家希望追溯治理方案演进过程时补充提供
   - 不作为当前正式制度口径
   - 评审时应以正式治理手册和最终结论纪要为准

---

## 4. OpenSpec 主案例包

这部分适合给关注“规格写法是否严谨”的专家。

1. `openspec/changes/project-management/.openspec.yaml`
2. `openspec/changes/project-management/proposal.md`
3. `openspec/changes/project-management/design.md`
4. `openspec/changes/project-management/tasks.md`
5. `openspec/changes/project-management/specs/project-management/spec.md`

建议重点评审：

1. capability 建模是否合理；
2. requirement / scenario 是否完整；
3. design 是否把字段契约、权限、租户、主数据、导出语义约束清楚；
4. tasks 是否真的能交给后续执行，而不是抽象口号；
5. 是否存在 spec、design、tasks 之间不一致。

---

## 5. 历史参考案例

这部分不是主评审对象，但可以作为对照材料。

1. `openspec/changes/todo-list/`

建议用途：

1. 对比“简单案例”与“企业实战案例”的差异；
2. 观察为什么当前资料改成以 `project-management` 作为主案例；
3. 帮助专家评估主案例切换是否合理。

---

## 6. 规则基线配套文件

这部分适合给关注“AI 约束如何固化”的专家。

1. `.cursor/rules/brownfield-project-baseline.mdc`
2. `.cursor/rules/java-brownfield-backend.mdc`
3. `.cursor/rules/frontend-brownfield-conventions.mdc`

建议重点评审：

1. 规则是否来自真实项目模式；
2. 规则是否足够具体、可执行；
3. 是否真正有助于降低 AI 在棕地项目中的偏航风险；
4. 是否存在过度理想化或无法长期维护的问题。

---

## 7. 建议的专家分工评审方式

如果你线下找多位专家，建议按角色拆分。

### 7.1 方法论 / 架构专家

建议发：

1. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
2. `core/PROJECT-MANAGEMENT-DOC-MAP.md`
3. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
4. `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
5. `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

重点看：

1. 方法是否闭环；
2. 边界是否清晰；
3. 是否存在夸大 AI 能力；
4. 是否适合企业推广。

### 7.2 后端 / 前端实战专家

建议发：

1. `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
2. `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
3. `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
4. `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
5. `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
6. `openspec/changes/project-management/`

重点看：

1. 提示词是否真的能约束 AI；
2. 设计与实现阶段是否足够贴近真实棕地项目；
3. 字段契约、权限、租户、导出等复杂点是否讲清楚；
4. 是否有不符合实际研发流程的地方。

### 7.3 测试 / 交付 / 发布专家

建议发：

1. `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
2. `execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
3. `execution/BROWNFIELD-CHANGE-UPDATE-WORKFLOW.md`
4. `execution/BROWNFIELD-QUICKSTART-CHECKLIST.md`
5. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`

重点看：

1. 验收定义是否严谨；
2. 证据门禁是否真实可落地；
3. 发布、回滚、归档是否讲清楚；
4. 是否避免“口头完成”“AI 自证完成”。

---

## 8. 线下发包建议

你线下发给专家时，建议附一句说明：

```text
这套资料不是为了展示“AI 可以自动完成开发”，而是为了研究：
1. 如何在棕地项目中用 OpenSpec + Superpowers + 规则基线约束 AI；
2. 如何让 AI 更接近真实设计和功能需求；
3. 如何通过证据、验收、PR、发布门禁降低 AI 偏航风险。

请重点评审：
1. 是否存在夸大 AI 能力的地方；
2. 是否有不符合真实研发流程的地方；
3. 是否缺少会影响准确落地的关键约束；
4. 哪些文档应该合并、删减或进一步收紧。
```

---

## 9. 最终建议

如果你只想让外部专家最快理解这套体系，建议线下先发这四层：

1. 总入口：
   `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
2. 治理总手册：
   `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
3. 方法论主文档：
   `execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
4. 主案例与主规格：
   `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
   `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
   `openspec/changes/project-management/`
   `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

如果专家愿意继续深看，再补：

5. 规则与提示词：
   `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
   `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
6. 验收与交付：
   `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
   `execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
   `execution/BROWNFIELD-CHANGE-UPDATE-WORKFLOW.md`
7. 结论纪要：
   `review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md`
8. M 级独立执行指南：
   `execution/M-MEDIUM-EXECUTION-GUIDE.md`
9. 实战碰撞验证：
   `review/DEMO-COLLISION-REPORT.md`
   `demo-brownfield-project/README.md`
   `demo-brownfield-project/prompts/`（10 份实战提示词）

一句话总结：

> 这份评审包的重点不是“AI 会不会写代码”，而是“这套资料能不能把 AI 严格约束在真实项目事实、真实需求和真实交付门禁之内”。  

