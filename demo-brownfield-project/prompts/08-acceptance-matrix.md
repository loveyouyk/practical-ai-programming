# 环节 8：验收矩阵提示词

> 来源：`execution/BROWNFIELD-FEATURE-ACCEPTANCE-MATRIX.md` 第 7.2 节  
> 使用时机：开发和联调完成后，输出验收结论

## 实际使用的提示词

```text
请基于 equipment-management 的 OpenSpec change 和当前实现状态，输出功能验收矩阵。

使用 M 级精简矩阵格式（7 列）：
| Requirement | Scenario | 实现状态 | 联调验证 | 证据 | 状态 | 备注 |

requirement 清单：
1. Query Equipment List（按名称/编号/状态筛选 + 租户隔离 + 空结果）
2. Create Equipment（成功 + 必填校验 + 编号唯一性）
3. Update Equipment（成功 + 数据回显 + 租户隔离）
4. Brownfield Compatibility（旧页面不受影响 + 共享组件未修改）

要求：
1. 每条 requirement 和 scenario 单独映射
2. 没有证据的项不能标记为通过
3. 最后给出"建议进入发布评审 / 不建议进入发布评审 / 部分达标"结论
4. 明确该结论只是验收建议，不替代人工审批
```

## 实际效果

AI 正确输出了 M 级精简矩阵，所有 requirement 状态为"联调通过"，结论为"部分达标"（因为待确认项 equipment_status 字典尚未在真实 DictService 中验证）。

## 碰撞发现

- 🔴 碰撞点 #10：标准 11 列矩阵确实过重。M 级精简版（7 列）在实操中明显更顺手。
- "部分达标"结论是准确的 — 待确认项未消解时不应标"具备上线评审条件"。

