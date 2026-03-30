# 环节 3：生成 OpenSpec 四件套提示词

> 来源：`execution/BROWNFIELD-QUICKSTART-CHECKLIST.md` 第 5 步  
> 使用时机：M 级起手后，AI 生成的草案需要细化时，逐份补充

## 实际使用的提示词（spec 细化）

```text
请为 equipment-management 细化 OpenSpec spec，当前 spec 草案缺少以下场景，请补充：

1. 设备编号唯一性校验场景（创建时编号重复应被拒绝）
2. 修改时的租户隔离校验场景（不能修改其他租户的设备）
3. 查询时空结果场景

要求：
1. 使用 ADDED Requirements 格式
2. 每个 scenario 使用 GIVEN/WHEN/THEN 格式
3. 兼容性 requirement 保留
```

## 实际使用的提示词（design 细化）

```text
请修正 equipment-management 的 design.md：

1. 删除响应字段中的 statusName — 本项目约定后端不翻译，前端用 DictService 翻译
2. 分页参数的默认 pageSize 应为 20，不是 10（和 BaseTable 默认值对齐）
3. 在 Pending Confirmations 中增加：equipment_status 字典是否已存在

这是开发过程中发现的设计不一致，需要先回写 design 再继续开发。
```

## 实际效果

- spec 细化后新增了 3 个 scenario，覆盖了审批中 TL 发现的遗漏
- design 修正后删除了 statusName，修改了 pageSize 默认值，这避免了后续开发中的不一致

## 碰撞发现

- design 细化这一步实际上触发了"变更回写"流程（碰撞点 #8）。方法论的回写流程是有效的，但它被设计为"开发中发现遗漏后触发"，而在 Demo 中是"审批后、开发前就发现了" —— 说明回写流程应该更早介入。

