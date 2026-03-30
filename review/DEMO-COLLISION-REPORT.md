# Demo 实战碰撞报告：理论 vs 实践冲突分析

> 日期：2026-03-30  
> 验证方式：搭建模拟棕地项目（含合同管理历史模块、5+ 个刻意埋入的 QUIRK），以"新增设备管理模块"为需求，严格按 M-Medium 快车道走完全流程  
> 碰撞策略：在每个环节以对抗思维寻找方法论的实操断裂点，而不是证明方法论正确

---

## 一、碰撞总览

在严格按方法论执行的 6 个环节中，共发现 **11 个碰撞点**，按类别分布如下：

| 类别 | 碰撞数 | 严重程度分布 |
|---|---|---|
| 流程设计 | 4 | 中 ×4 |
| 规则体系 | 2 | 高 ×2 |
| 模板设计 | 2 | 中 ×2 |
| 工具说明 | 1 | 高 ×1 |
| 治理机制 | 1 | 中 ×1 |
| 证据体系 | 1 | 中 ×1 |

**核心发现：** 方法论的宏观架构没有问题，但在 M 级需求的实操细节上存在"为 L 级设计但未充分适配 M 级"的现象。M 级是最高频的需求形态（占 60-70%），如果 M 级体验不好，方法论的团队接受度会大幅下降。

---

## 二、高严重度碰撞点详析

### 碰撞 #3：缺少 QUIRK（历史怪癖）登记机制

**根因分析：**

当前规则基线体系（`.cursor/rules/*.mdc`）设计为"必须/不得"的约束式表达。但棕地项目中存在大量"非直觉行为"（QUIRK），它们不是约束，而是"如果不知道就会踩坑"的隐性知识。例如：

- `SearchForm` 重置后不自动搜索
- `BaseTable` 默认 pageSize=20 而非 10
- `DictService` 返回 String key 但 DB 存 Integer
- `FormDialog` confirm 不自动关闭，需调用方控制

这些信息无法用"必须/不得"表达，但对 AI 和新成员同样关键。

**影响评估：** 在 demo 中，因为没有 QUIRK 登记，AI 生成的表单弹窗代码没有在 confirm 回调中手动关闭弹窗，导致提交后弹窗仍然打开。这正好印证了实战案例中的"踩坑 1"。

**建议：** 在规则体系中正式引入 QUIRK 登记簿。

### 碰撞 #5：`@openspec-ff-change` 说明和 fallback 缺失

**根因分析：**

`@openspec-ff-change` 在治理手册（5.2 节）和讨论稿（7 节）中被多次引用为 M 级快车道的标准起手命令，但：

1. 没有任何文档说明它是什么（Cursor 命令？提示词前缀？MCP tool？）
2. `AI-WORKFLOW-TOOLING-PREREQUISITES.md` 有 `/opsx-explore`、`/opsx-verify` 的 fallback，但没有 `@openspec-ff-change` 的
3. 如果团队使用的 IDE 不支持这个命令，整个 M 级快车道的入口就断了

**影响评估：** 这是 M 级快车道最关键的入口工具。如果这个入口不通，开发者要么退回到手写 4 份 Markdown（等于手动走 L 级），要么干脆跳过 OpenSpec（等于退化到无约束模式）。

**建议：** 补充完整说明和 fallback。

### 碰撞 #8：字段翻译/格式化约定未在规则基线中覆盖

**根因分析：**

"字段由前端翻译还是后端翻译"是棕地项目中一个极常见但极容易忽略的约定。demo 项目中合同模块的历史决策是"后端返回纯数字，前端用字典缓存翻译"。如果规则基线没有记录这个约定，新功能很容易写成"后端翻译后返回 statusName"，导致风格不一致。

**影响评估：** 这类约定在代码中是隐性的（没有任何文件显式写明），靠口耳相传。规则基线如果不覆盖，AI 和新成员必然会按"通用最佳实践"来做（后端翻译更常见），从而和历史系统冲突。

**建议：** 在规则基线模板中增加"数据翻译/格式化约定"条目。

---

## 三、方法论适配性结论

### 3.1 哪些环节实操顺滑

1. **治理分流（环节 0）**：四维判断思路正确，硬性升级条件有效
2. **OpenSpec 四件套（环节 2）**：proposal/spec/design/tasks 结构清晰，M 级最小示例有价值
3. **需求变更回写（环节 4 中触发）**：发现 design 不一致后，"先改 spec 再改代码"的纪律确实有效

### 3.2 哪些环节存在摩擦

1. **巡查→规则→固化三步串行（环节 1）**：M 级中过重
2. **审批（环节 3）**：缺少 checklist 和微调机制
3. **验收矩阵（环节 5）**：标准模板对 M 级过重

### 3.3 方法论可迁移性验证

本次 demo 使用"设备管理"而非主案例"项目管理"，验证了方法论的可迁移性。结论：

- **可迁移的部分：** 分流逻辑、规则基线结构、OpenSpec 四件套格式、验收矩阵框架、证据块模板
- **需要适配的部分：** 字段级细节（每个模块的字典来源不同）、具体的提示词内容（需替换业务名词）
- **意外发现：** M 级最小 OpenSpec 示例比主案例更适合作为迁移起点，因为它更轻量

---

## 四、需要反向修正的方法论文档

| 文档 | 修正内容 | 优先级 |
|---|---|---|
| `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` | 增加分流快速检查表；审批结果增加"微调后通过"选项 | 高 |
| `execution/AI-WORKFLOW-TOOLING-PREREQUISITES.md` | 增加 `@openspec-ff-change` 说明和 fallback | 高 |
| `execution/BROWNFIELD-RULE-BASELINE-PLAYBOOK.md` | 增加 QUIRK 登记簿概念和模板；增加"数据翻译/格式化约定"条目 | 高 |
| `execution/BROWNFIELD-QUICKSTART-CHECKLIST.md` | M 级巡查/规则/固化三步合并说明 | 中 |
| `execution/M-MEDIUM-MINIMAL-OPENSPEC-EXAMPLE.md` | 增加 Pending Confirmations 示例 | 中 |
| `execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` | 增加 M 级精简矩阵模板 | 中 |
| `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` | 增加 M 级审批 checklist 模板 | 中 |
| `execution/PROJECT-MANAGEMENT-BROWNFIELD-EXECUTION-PROMPTS.md` | M 级实施计划改为前后端交叉迭代节奏 | 低 |
| `core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` | 证据 Evidence Location 增加本地环境指引 | 低 |

