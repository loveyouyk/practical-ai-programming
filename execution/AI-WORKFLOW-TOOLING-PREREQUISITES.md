# AI 开发工具前置与 Fallback 说明

> 目标：说明这套 `OpenSpec + Superpowers` 文档在真实项目中依赖哪些工具能力、如何判断工具可用，以及在工具不可用时如何降级执行。  
> 核心原则：不要把 `/opsx-*` 或某个 skill 名称当成“口头存在的基础设施”。

---

## 1. 为什么需要这份说明

如果流程文档里写了：

1. `/opsx-explore`
2. `/opsx-verify`
3. `/opsx-sync`
4. `/opsx-archive`
5. `Superpowers:brainstorming`
6. `Superpowers:writing-plans`

但团队没有统一理解：

1. 这些能力在哪用；
2. 哪些环境可以直接执行；
3. 不能执行时用什么替代；
4. 什么情况下必须人工确认；

就会出现两种问题：

1. **假执行**：大家嘴上说“已经 verify 了”，实际上没有统一验证动作；
2. **真阻塞**：团队成员不知道命令在哪里跑、跑完什么算通过。

---

## 2. 这套流程依赖什么能力

## 2.1 OpenSpec / `opsx-*`

这类能力用于：

1. 探索棕地项目现状
2. 生成或更新 OpenSpec artifacts
3. 验证 change 完整性
4. 同步和归档 change

## 2.2 Superpowers

这类能力用于：

1. 需求澄清
2. 实施计划展开
3. 多角色执行
4. 最终验证前收口

## 2.3 仓库本身仍然需要的基础能力

无论 AI 工具是否可用，下面这些都仍然需要：

1. 代码可读
2. 测试可执行
3. PR 可评审
4. 发布可验证

AI 工具不能替代这些基础工程能力。

---

## 3. 推荐环境判断方式

在正式执行流程前，先判断三类环境：

## 3.1 理想环境

满足：

1. 当前 IDE 已接入 OpenSpec / `opsx-*`
2. 当前 IDE 已接入 Superpowers
3. 团队可以直接使用文档中的提示词和工作流

这种情况下，按主流程文档直接执行。

## 3.2 半可用环境

满足：

1. 能使用 AI 对话和文档生成
2. 但不能直接调用 `/opsx-*` 或部分 Superpowers 能力

这种情况下，要采用“人工流程 + 文档模板”的方式降级执行。

## 3.3 受限环境

满足：

1. 没有 OpenSpec / Superpowers 集成
2. 只能用普通终端、Git、测试命令和 Markdown 文档

这种情况下，仍然可以按文档执行，但必须手工完成 artifacts、验证与交付证据。

---

## 4. `@openspec-ff-change` 说明与 fallback

### 4.1 它是什么

`@openspec-ff-change` 是 OpenSpec 工具链提供的快速生成命令，用于在 AI IDE（如 Cursor）中一次性生成 `proposal.md`、`spec.md`、`design.md`、`tasks.md` 四份 OpenSpec change 文档。

它的本质是一个预定义的提示词模板 + 目录结构生成器，不是一个独立的命令行工具。

### 4.2 在什么环境可用

1. **理想环境：** 当前 IDE 已安装 OpenSpec 插件或集成了 `@openspec-ff-change` 命令 → 直接使用
2. **半可用环境：** IDE 有 AI 对话能力但没有 OpenSpec 插件 → 使用 fallback
3. **受限环境：** 只有文本编辑器和终端 → 使用 fallback

### 4.3 fallback

如果 `@openspec-ff-change` 不可用，按以下步骤手动执行：

1. 在项目中创建 `openspec/changes/<change-name>/` 目录
2. 创建 `.openspec.yaml` 文件，写入 change 名称和 capability
3. 创建 `proposal.md`，按模板填写 Why / What Changes / Scope / Impact / Risks
4. 创建 `specs/<capability-name>/spec.md`，按模板填写 Requirements 和 Scenarios
5. 创建 `design.md`，按模板填写 Context / Decisions / API Contract / Data Model / Compatibility
6. 创建 `tasks.md`，按模板填写分组任务

模板参考：

- L 级完整示例：`openspec/changes/project-management/`
- M 级最小示例：`execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md`

### 4.4 M 级快车道中的替代话术

如果 `@openspec-ff-change` 不可用，M 级起手话术改为：

```text
这是一个 M 级需求。请先基于当前项目规则基线做最小探索，再按 OpenSpec 格式生成 proposal/spec/design/tasks 四份文档。参考 execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md 的骨架。生成后停下，等待我审批。
```

---

## 5. `/opsx-*` 的建议 fallback

## 5.1 `/opsx-explore`

### 理想方式

直接用 `opsx-explore` 或等价 explore 流程。

### fallback

手工完成以下输出：

1. 现状地图
2. 复用候选点
3. 高风险点
4. 待确认问题

并且必须附：

1. 真实文件路径
2. 至少一条调用链或引用链
3. 不确定项明确标注为 `待确认`

## 5.2 `/opsx-verify`

### 理想方式

直接执行 `opsx-verify <change>`

### fallback

手工完成以下验证并输出验证结论：

1. `Completeness`
   - tasks 是否覆盖 requirements
2. `Correctness`
   - 关键 scenario 是否有对应实现和测试
3. `Coherence`
   - 实现是否符合 design 和规则基线
4. `Compatibility`
   - 旧链路是否有回归证据
5. `Release readiness`
   - migration、smoke test、监控、回滚是否具备

建议把结论落到：

1. 验收矩阵
2. 交付模板
3. PR 描述

## 5.3 `/opsx-sync`

### 理想方式

直接执行 `opsx-sync <change>`

### fallback

手工判断：

1. 这次 change 是否已经稳定到适合进入长期主 specs
2. 哪些 requirement / design 需要抽到主 specs
3. 是否会影响当前主 capability 结构

在未确认稳定前，不要假装“已经 sync”。

## 5.4 `/opsx-archive`

### 理想方式

直接执行 `opsx-archive <change>`

### fallback

只有满足下面条件，才允许人工判定“可归档”：

1. OpenSpec artifacts 完整
2. 实现完成
3. 验证完成
4. PR / merge 已完成
5. 发布结论明确
6. 交付证据已沉淀

---

## 6. Superpowers 的建议 fallback

## 6.1 `brainstorming`

### fallback

人工按文档中的问题顺序完成：

1. 业务目标
2. 用户角色
3. MVP 范围
4. 非目标
5. 兼容性约束
6. 发布与回滚要求

## 6.2 `writing-plans`

### fallback

基于 `proposal/spec/design/tasks` 手工补一份实施计划，至少写清：

1. 任务顺序
2. 文件范围
3. 测试与验证动作
4. 关键风险

## 6.3 `subagent-driven-development`

### fallback

人工按角色拆分审阅：

1. 实现者
2. Spec Reviewer
3. Code Quality Reviewer

至少保证 review 不被跳过。

---

## 7. 哪些地方不能只靠 AI

下面这些动作，不应假装“AI 说通过就算通过”：

1. 权限与租户验收
2. 真实联调结果
3. 导出文件业务正确性
4. 上线前 smoke test
5. 回滚可执行性
6. 最终是否允许归档或发布

这些地方必须有：

1. 测试结果
2. 日志 / 截图 / 文件样例
3. PR 或交付文档证据
4. 必要时人工确认

---

## 8. 建议的最小可执行门禁

即使工具不可用，最少也要做到：

1. 有规则基线
2. 有 OpenSpec artifacts
3. 有实施计划
4. 有前后端测试
5. 有验收矩阵
6. 有 PR 描述
7. 有发布清单

如果以上任一项缺失，就不要宣称“全流程已经跑通”。

---

## 9. 一句话总结

> `/opsx-*` 和 Superpowers 是强能力，但不是魔法。真正可推广的流程，必须在工具可用时高效执行，在工具不可用时仍然能被人工降级执行。  

