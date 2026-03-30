# 环节 2：M 级起手提示词

> 来源：`execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 第 2.2 节  
> 使用时机：探索完成后，正式进入 M-Medium 快车道

## 实际使用的提示词

```text
这是一个 M 级棕地需求。请先基于当前项目规则基线做最小探索，再生成最小 OpenSpec change。

需求：在当前历史系统中新增"设备管理"模块。

已知约束（来自刚才的探索）：
1. 后端必须使用 Response<T> 统一返回体，不使用 ResponseEntity
2. 异常必须使用 ServiceException，不另造异常类
3. 租户隔离通过 TenantContext（ThreadLocal），新表必须有 tenant_id 字段
4. 分页使用 PageHelper / PageResult 模式
5. 字典使用 DictService，key 是 String 类型
6. 后端不返回翻译名称，前端用 DictService 本地翻译
7. 前端必须复用 SearchForm、BaseTable（默认 pageSize=20）、FormDialog（confirm 不自动关闭）
8. 路由需要 meta.title 和 meta.permission

要求：
1. 先识别复用点、兼容性风险和待确认项
2. 再生成 proposal、spec、design、tasks 草案
3. 生成后停下，等待人工审批
4. 如果发现边界不清、字段契约不清、主数据来源不清或旧链路影响超出预期，请明确建议升级到完整流程
5. 不要直接进入完整实现

待确认项（必须在文档中标出）：
- 设备状态枚举是否在 DictService 中已有（key: equipment_status）
- 设备编号唯一性是全局唯一还是租户内唯一
```

## 与方法论原版的差异

原版提示词（手册 2.2 节）是通用模板，只有 5 条要求。实操中增加了：
- "已知约束"整个小节（8 条从探索中提炼的具体约束）
- "待确认项"整个小节（碰撞点 #4 的发现 — M 级也需要显式列出待确认）
- 将探索结果作为输入直接嵌入提示词，而不是依赖 AI "记住"之前的对话

## 实际效果

AI 生成了完整的 proposal/spec/design/tasks，并正确标出了两个待确认项。但 design 中错误地包含了 `statusName` 字段（碰撞点 #8），因为 AI 按"通用最佳实践"做了后端翻译。

## 碰撞发现

- 🔴 碰撞点 #4：原版提示词没有"待确认项"引导。如果不主动加上，AI 会默认补齐所有信息而不标"待确认"。
- 🔴 碰撞点 #5：原版提到 `@openspec-ff-change` 但实际环境中不可用，只能用提示词 fallback。

