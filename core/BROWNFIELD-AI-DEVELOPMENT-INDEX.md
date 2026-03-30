# 棕地项目 AI 开发文档索引

> 目标：作为这套资料的“流程入口页”，只回答一个问题: 现在该从哪份文档开始。  
> 使用原则：这份文档负责“起步导航”，不负责列出全部资产；如果你要看全量清单，请转到 `knowledge/core/AI-ADOPTION-DOCUMENT-CATALOG.md`。  
> 使用边界：这份索引不替代治理手册、主案例导航、执行提示词或验收模板；它只负责把你导向下一份该看的文档。

补充说明：

1. 这套资料的主干方法是 `OpenSpec + Superpowers + 棕地规则基线 + 证据式验收`
2. `project-management` 只是当前用于讲解的主案例，不是唯一业务场景
3. 迁移到其他 capability 时，应复用方法、分流、提示词结构和证据门禁，而不是复用业务名词本身

## 推荐连读

建议先按这个顺序进入：

1. `knowledge/core/TEAM-ADOPTION-QUICKSTART.md`
2. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
3. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`：如果工具环境不确定，先确认可用能力
4. `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md`
5. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
6. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

---

## 1. 使用这份索引前先记住

棕地项目里的核心问题不是“AI 会不会写”，而是：

1. 什么时候该先探索；
2. 什么时候该先提规则；
3. 什么时候该走最小 OpenSpec；
4. 什么时候必须走完整闭环；
5. 怎样才算“有证据地完成”。

所以这份索引不做资料盘点，而是直接帮你分流。

---

## 2. 第一步永远先分流

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`

先判断当前需求属于：

1. `S-Low`
2. `M-Medium`
3. `L / High Risk`

然后按下面进入：

1. `S-Low`：优先依赖 `.cursor/rules/*.mdc`、最小提示词、PR 验证
2. `M-Medium`：最小探索 + 最小 OpenSpec + 快速审批 + 验收矩阵
3. `L / High Risk`：完整探索 + 完整 OpenSpec + 执行手册 + 验收 + 交付

---

## 3. 按当前场景进入

## 3.1 我第一次接手这个历史项目

先看：

1. `knowledge/execution/BROWNFIELD-QUICKSTART-CHECKLIST.md`
2. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
3. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
4. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`

适用目标：

1. 先理解旧项目
2. 先提炼规则基线
3. 再让 AI 参与设计与实现

## 3.2 我已经理解项目，要开始推进功能

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md`

然后根据分流进入：

1. `M-Medium`：参考 `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
2. `L / High Risk`：进入 `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

如果工具环境不确定，再补看：

3. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

## 3.3 我只想快速开工

直接看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/BROWNFIELD-QUICKSTART-CHECKLIST.md`

说明：

1. 这条路径适合日常小需求和已理解上下文的团队成员
2. 不适合对旧项目还没有巡查结论的场景

## 3.4 我想看一个完整主案例

直接看：

1. `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md`
2. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
3. `openspec/changes/project-management/`

如果你要对比中型快车道，再补看：

4. `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

这里的 `project-management` 应理解为“参考主案例”：

1. 它负责演示棕地项目里一组较完整的前后端需求如何落到 `OpenSpec + Superpowers`
2. 当你处理其他模块时，可以替换 capability 名称和业务字段，但应尽量沿用这里的分流逻辑、规格结构、执行提示词和验收方式

## 3.5 我要判断现在能不能合并 / 上线

直接看：

1. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
2. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
3. `knowledge/review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md`

---

## 4. 按角色进入

## 4.1 技术负责人 / 架构负责人

优先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
3. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`

## 4.2 开发成员

优先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `.cursor/rules/*.mdc`
3. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

## 4.3 测试 / 验收 / 交付负责人

优先看：

1. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
2. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
3. `knowledge/review/EXPERT-REVIEW-PACK-LIST.md`

---

## 5. 如果你只保留最小必读集

建议保留这 5 份：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
5. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

---

## 6. 如果你要看全部资产

请转到：

1. `knowledge/core/AI-ADOPTION-DOCUMENT-CATALOG.md`

这份 `Catalog` 才是：

1. 全量文档清单
2. 分层说明
3. 历史资料说明
4. 阅读路径总览

---

## 7. `0-15` 步速查

如果你要把这套资料当成团队推广路径，可以按下面理解：

| 步骤 | 目标 | 主要文档 |
|---|---|---|
| 0 | 判断当前需求走哪条路径 | `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` |
| 1 | 了解整套资料从哪里开始 | `knowledge/README.md` `knowledge/core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md` |
| 2 | 确认工具环境和 fallback | `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md` |
| 3 | 巡查历史项目现状 | `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` |
| 4 | 提炼项目规则基线 | `.cursor/rules/*.mdc` `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` |
| 5 | 选择参考案例或最小示例 | `knowledge/core/PROJECT-MANAGEMENT-DOC-MAP.md` `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md` `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md` |
| 6 | 理解完整方法如何成立 | `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md` |
| 7 | 需求澄清与 explore | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` |
| 8 | 形成 OpenSpec artifacts | `openspec/changes/project-management/` `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` |
| 9 | 生成 Superpowers 实施计划 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` |
| 10 | 多角色前后端开发 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` |
| 11 | 联调与测试 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` |
| 12 | Git / PR / 合并 / 发布准备 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md` |
| 13 | 最终验证 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` |
| 14 | 上线执行 | `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md` |
| 15 | 归档与沉淀 | `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md` `knowledge/review/AI-ADOPTION-FINAL-CONCLUSION-MINUTES.md` |

