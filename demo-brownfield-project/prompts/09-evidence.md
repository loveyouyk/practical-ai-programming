# 环节 9：结构化证据提示词

> 来源：`core/AI-ADOPTION-GOVERNANCE-HANDBOOK.md` 第 8.2 节  
> 使用时机：验收矩阵完成后，为关键 requirement 填写证据块

## 实际使用的提示词

```text
请为 equipment-management 的关键 requirement 生成结构化证据块。

当前环境是本地开发环境（无 CI/CD），请按以下格式填写：

## Verification Evidence

### Change / Commit / PR
- 

### Command Or Action
- （描述具体操作步骤，而不是"本地验证通过"）

### Environment
- 

### Result
- （描述可观察的具体结果）

### Evidence Location
- （本地环境填 commit hash + 可复现的操作步骤）

### Conclusion
- 

### Residual Risk
- 

需要覆盖的 requirement：
1. 列表查询（含筛选）
2. 新增（含校验失败）
3. 修改（含回显）
4. 租户隔离
5. 兼容性（旧页面未受影响）
```

## 实际效果

AI 为每个 requirement 生成了证据块。Evidence Location 填的是"commit hash abc123 + 操作步骤"，比"浏览器截图"更有可复现性。

## 碰撞发现

- 🔴 碰撞点 #11：原版证据块模板没有说明"本地环境怎么填 Evidence Location"。需要在提示词中主动指导"本地环境填 commit hash + 可复现操作步骤"。
- 证据的核心是"可复现"，不是"有 URL"。这一点需要在方法论中明确。

