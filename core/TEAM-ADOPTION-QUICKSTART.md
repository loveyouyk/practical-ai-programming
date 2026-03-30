# 团队推广 Quickstart

> 目标：给团队负责人、TL、架构师和一线开发一个最短可推广入口，快速理解如何在棕地项目中使用 `OpenSpec + Superpowers` 提效，而不是把它当成“AI 自动开发”方案。  
> 适用场景：团队准备试点、正式推广，或个人第一次按这套方法在历史项目中推进需求变更、功能新增、升级改造。  
> 使用边界：这份 quickstart 只负责帮助团队快速起步，不替代治理手册、执行手册、验收矩阵和交付模板。

## 1. 这套方法到底是什么

一句话说：

> 在棕地项目里，用 `OpenSpec` 固化需求和设计真相源，用 `Superpowers` 提高澄清、计划、执行和评审效率，再用规则基线和证据门禁约束 AI 不偏航。  

核心目标不是“让 AI 自己写完”，而是：

1. 更快澄清需求边界
2. 更稳定地复用旧系统模式
3. 更准确地对齐前后端设计和实现
4. 更早发现兼容性、权限、租户、导出、回滚等高风险点
5. 把“功能完成”变成有证据的工程结论

## 2. 适合什么需求

最适合：

1. 历史项目中的新增功能
2. 旧功能升级改造
3. 涉及前后端联动的需求变更
4. 需要规格、提示词、验证和交付一起闭环的需求

不适合直接重套完整流程的场景：

1. 微小且低风险的纯局部修补
2. 还没确认工具环境、项目规则和旧系统现状的场景
3. 想让 AI 直接跳过探索和规格写代码的场景

## 3. 团队技能前置要求

在正式推广前，团队成员需要具备以下最小技能基础。不需要精通，但需要"用过、知道是什么"。

### 3.1 全员必备

1. **基础 AI 对话能力**：能在 Cursor / ChatGPT / Claude 等工具中用自然语言与 AI 对话，理解 AI 回复的局限性
2. **Git 基本操作**：branch、commit、push、PR、merge 的日常使用
3. **Markdown 基本写作**：能读写 Markdown 文档，因为 OpenSpec artifacts、验收矩阵、交付模板都是 Markdown 格式

### 3.2 TL / 架构师额外需要

1. **Cursor Rules 机制理解**：知道 `.cursor/rules/*.mdc` 的作用、加载方式和维护方式
2. **OpenSpec 基本概念**：理解 proposal / spec / design / tasks 四份文档各自解决什么问题
3. **提示词工程基础**：理解"约束式提示词"与"开放式提示词"的区别，能判断提示词质量

### 3.3 一线开发额外需要

1. **AI IDE 基本操作**：在 Cursor（或类似工具）中完成过至少 1 次完整的代码生成、review、修改流程
2. **Rules 消费意识**：知道在开发前应确认当前 `.cursor/rules` 是否已加载，而不是忽略它

### 3.4 测试 / 验收人员额外需要

1. **验收矩阵阅读能力**：能理解 requirement / scenario 与测试证据的映射关系
2. **结构化证据判断能力**：能区分"有效证据"和"贴了一段无关日志"

### 3.5 推荐上手路径

如果团队成员完全没有 AI 编程经验，建议按这个顺序上手：

1. 先用 Cursor 完成 3 个 `S-Low` 级别的真实需求（纯文案修改、单字段调整等）
2. 再参与 1 个 `M-Medium` 级别的需求作为观察者
3. 再独立负责 1 个 `M-Medium` 级别的需求
4. 此时再阅读完整资料包

---

## 4. 团队怎么开始试点

建议先选一类需求做试点：

1. 有明确业务目标
2. 会改到前后端，但范围可控
3. 能看到复用旧链路、验收和交付价值
4. 不要一开始就选资金、结算、强发布敏感的最高风险需求

建议试点顺序：

1. TL / 架构先判断需求属于 `S-Low`、`M-Medium` 还是 `L / High Risk`
2. 先做一次棕地巡查，提炼规则基线
3. 再决定走最小 OpenSpec 还是完整 OpenSpec
4. 再进入 `Superpowers` 的澄清、计划、执行和评审
5. 最后用验收矩阵和交付模板收口

## 5. 个人开发怎么起步

如果你是第一次自己用这套方法，最短路径建议是：

1. 先看 `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. 再看 `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
3. 再看 `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
4. 然后按 `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 巡查项目
5. 最后再进入 `execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 或 `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

## 6. 第一次推广先读哪 5 份

如果你只想把团队带起来，优先读这 5 份：

1. `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `core/BROWNFIELD-AI-DEVELOPMENT-INDEX.md`
3. `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
4. `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
5. `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

如果团队要看一个完整参考，再补：

6. `core/PROJECT-MANAGEMENT-DOC-MAP.md`
7. `execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`

## 7. 推广时最容易犯的错

1. 把这套方法理解成“AI 自动开发流程”
2. 跳过旧项目探索，直接让 AI 设计和实现
3. 只写提示词，不写正式 `OpenSpec`
4. 只看代码能跑，不看前后端契约、联调和发布证据
5. 把主案例的业务名词照搬到别的 capability，而不是复用方法结构

## 8. 一句话推广口径

如果你要向团队解释这套方法，可以直接说：

> 我们推广的不是“让 AI 替我们开发”，而是“在棕地项目里，用 `OpenSpec + Superpowers` 更快澄清需求、更稳复用旧系统、更准落地前后端功能，并用证据门禁降低偏航风险”。
