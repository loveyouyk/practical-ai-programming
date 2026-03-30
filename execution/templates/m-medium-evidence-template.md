# M 级结构化证据精简模板

> 用途：M-Medium 需求使用此 4 字段精简版。L/High Risk 使用完整 7 字段版。  
> 使用方式：每个关键 requirement 复制一份填写。

---

```md
## Verification Evidence

### What（做了什么）
- Commit: [hash 或分支名]
- 操作：[具体操作步骤，不要写"本地验证通过"]

### Result（结果是什么）
- [可观察的具体结果，如"列表返回 3 条匹配记录""提交后弹窗关闭并列表刷新"]

### Conclusion（结论）
- [通过/未通过 + 一句话理由]

### Residual Risk（剩余风险）
- [未覆盖的场景，如"未测试大数据量""未测试多租户并发"]
```

---

## 与完整版的关系

| 字段 | M 级精简版 | L 级完整版 |
|---|---|---|
| What（做了什么） | ✅ 必填 | ✅ 对应 Change/Commit/PR + Command Or Action |
| Result | ✅ 必填 | ✅ 对应 Result |
| Conclusion | ✅ 必填 | ✅ 对应 Conclusion |
| Residual Risk | ✅ 必填 | ✅ 对应 Residual Risk |
| Environment | — | ✅ 必填 |
| Evidence Location | — | ✅ 必填 |
| Change/PR 关联 | 合并在 What 中 | ✅ 独立字段 |

如果 M 级需求后续升级为 L 级，补齐 Environment + Evidence Location + Change/PR 关联即可。

