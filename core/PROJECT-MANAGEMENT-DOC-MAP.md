# 项目管理模块文档导航

> 目标：把 `项目管理模块` 相关的主文档、主流程、实战案例和 OpenSpec 示例串成一条可落地的执行路径，用于指导团队在棕地项目中更准确地完成前后端功能设计、实现、联调、验收和交付。  
> 用法：如果你不知道先看哪一份，就从这页开始。  
> 使用边界：这份导航默认服务 `project-management` 主案例和 `L / High Risk` 完整流程参考；不是所有需求都应直接照搬，正式进入前仍要先做治理分流。

补充说明：

1. `project-management` 是当前用于教学和推广的参考主案例，不是唯一业务场景
2. 这份文档真正想传递的是：在棕地项目里，如何把 `OpenSpec + Superpowers` 用到真实需求变更和新增功能上
3. 当你处理其他 capability 时，应替换业务名词，但保留探索、规格、计划、实现、验收和发布门禁的基本结构

---

## 1. 先看什么

先不要默认走完整流程。  
先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`

先判断当前需求属于：

1. `S-Low`
2. `M-Medium`
3. `L / High Risk`

在这个前提下，再进入项目管理模块路径。  
这里所有示例、提示词和 OpenSpec 写法都应理解为：

1. 用于帮助团队把真实需求、真实页面和真实后端契约落到可执行文档
2. 不代替业务确认、接口确认和联调验证
3. 不允许脱离当前项目规则基线自行脑补实现

如果你只想记项目管理模块的最短路径，按这个顺序：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
3. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
5. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
6. `openspec/changes/project-management/`
7. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
8. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`

---

## 2. 每份文档是干什么的

### 方法论总文档

- `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
- 作用：先判断需求应该走哪条治理路径，再进入对应执行资料。
- 适合谁：负责人、架构师、需要先做流程分流的人。

- `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
- 作用：解释为什么要这样做，讲清 OpenSpec、Superpowers、棕地探索、验收、发布之间的关系。
- 适合谁：负责人、架构师、要培训团队的人。

### 主流程执行手册

- `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
- 作用：按步骤给你可直接复制的提示词，从需求澄清一路到归档。
- 适合谁：真正要开工推进功能的人。

### 工具前置与 fallback 文档

- `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
- 作用：说明 `/opsx-*` 和 Superpowers 在什么环境可直接使用，不可用时如何降级执行。
- 适合谁：负责人、首次落地团队、需要做组织推广的人。

### 实战案例文档

- `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
- 作用：用“列表查询 / 新增 / 修改 / 导出”的真实业务模块演示整个流程怎么落地。
- 适合谁：想看一个完整案例而不是抽象方法的人。

### 主示例 OpenSpec change

- `openspec/changes/project-management/`
- 作用：给你一套可直接参考的 `proposal/spec/design/tasks` 示例。
- 适合谁：想看 OpenSpec 到底应该怎么写的人。

### `M-Medium` 最小示例

- `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
- 作用：给你一个中型快车道场景下的最小 OpenSpec 骨架，避免把高风险主案例误当成所有需求默认模板。
- 适合谁：想区分 `M-Medium` 和 `L / High Risk` 写法差异的人。

### 验收矩阵模板

- `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
- 作用：判断功能是否真正达标，而不是“代码写完就算完成”。
- 适合谁：测试、验收负责人、PR 合并前把关的人。

### 交付模板

- `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
- 作用：沉淀巡查、规则、OpenSpec、实现、测试、PR、发布、归档证据。
- 适合谁：交付负责人、评审人、上线负责人。

---

## 3. 按场景怎么用

### 场景 A：我第一次接手这个历史项目

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
3. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
5. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`

然后再进入：

6. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

### 场景 B：我已经理解项目，要开始推功能

直接看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
3. 如果判定为 `M-Medium`，走最小探索 + 最小 OpenSpec + 快速审批，并参考 `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`
4. 如果判定为 `L / High Risk`，看 `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
5. `openspec/changes/project-management/`

### 场景 C：我想快速对照一个真实案例

直接看：

1. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
2. `openspec/changes/project-management/`

如果你更想看 `M-Medium` 的最小 OpenSpec 骨架，再补看：

3. `knowledge/execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

### 场景 D：我要判断现在能不能上线

直接看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
3. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`

判断原则：

1. 不是“AI 说完成”就算完成
2. 不是“代码能跑”就算满足客户需求
3. 需要把前端页面、后端接口、权限/租户/主数据场景、联调结果和发布准备一起看

### 场景 E：我想先判断这个需求该走轻流程还是重流程

直接看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`

---

## 4. 按角色怎么用

### 技术负责人

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/PROJECT-MANAGEMENT-OPENSPEC-SUPERPOWERS-SPEC-GUIDE.md`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
4. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`

### 后端开发

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `.cursor/rules/java-brownfield-backend.mdc`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
4. `openspec/changes/project-management/specs/project-management/spec.md`

### 前端开发

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `.cursor/rules/frontend-brownfield-conventions.mdc`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
4. `openspec/changes/project-management/design.md`

### 测试 / 验收负责人

先看：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`
3. `knowledge/execution/BROWNFIELD-DELIVERY-TEMPLATE.md`
4. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`

---

## 5. 推荐最小组合

如果你不想看太多文档，最小组合就是这五份：

1. `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md`
2. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md`
3. `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md`
4. `openspec/changes/project-management/`
5. `knowledge/execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md`

适用前提：

1. 你已经完成基本治理分流判断
2. 团队已具备基础规则基线，或者已知道要按现有规则基线执行

如果你是第一次接手这个历史项目，或第一次在团队里正式推广，再额外补这些：

6. `knowledge/execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md`
7. `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md`
8. `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md`

这五份已经足够支撑：

1. 先判断治理路径；
2. 按步骤推进功能；
3. 对照真实案例；
4. 参考 OpenSpec 写法；
5. 判断功能是否达标。

---

## 6. 一句话入口

如果今天你要正式开干一个棕地项目功能，就按这条线：

1. 先看 `knowledge/core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` 判断路径
2. 按 `knowledge/execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` 巡查项目
3. 用 `knowledge/execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 和 rules 提炼统一约束
4. 按分流进入对应资料
5. 用 `knowledge/execution/PROJECT-MANAGEMENT-BROWNFIELD-PRACTICAL-CASE.md` 与 `openspec/changes/project-management/` 校准规格和提示词写法
6. 进入前后端设计、开发、联调、验收、PR、发布、归档

