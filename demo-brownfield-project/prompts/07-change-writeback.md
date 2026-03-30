# 环节 7：变更回写提示词

> 来源：`execution/BROWNFIELD-CHANGE-UPDATE-WORKFLOW.md` 第 6.1 节  
> 使用时机：开发中发现 design 与实际项目不一致（statusName 问题）

## 实际使用的提示词

```text
我们在开发 equipment-management 过程中发现一个设计不一致：

design.md 中写了响应字段包含 statusName（后端翻译），但实际巡查发现本项目的历史约定是"后端不翻译，前端用 DictService 本地翻译"。合同模块的列表接口只返回 status（int），不返回 statusName。

请先不要直接修改代码。

请先分析：
1. 这属于新 requirement、requirement 细化、设计变更，还是任务补充
2. 它会影响 proposal、spec、design、tasks 中的哪些部分
3. 它会影响前端、后端、联调、测试中的哪些环节
4. 已经写好的后端代码中哪些地方需要修改

然后请输出：
1. 应更新的 OpenSpec artifacts 清单
2. 具体修改内容
3. 对已完成代码的影响评估
```

## 实际效果

AI 正确分析：
1. 这是**设计变更**（requirement 没变，实现路径变了）
2. 只影响 design.md（删除 statusName 字段，补充"前端翻译"约定说明）
3. 影响后端（删除 statusName 拼接逻辑）和前端（确保前端有 DictService 翻译）
4. 不影响 proposal 和 spec

处理结果：先更新 design，再修改后端代码，整个过程 < 10 分钟。

## 碰撞发现

- 变更回写流程在 M 级中是有效的，但比方法论描述的更轻量 — 不需要走完"六步标准动作"，实际上 3 步就够了（判断类型→更新 design→改代码）。
- 说明 M 级的变更回写可以有一个"轻量版"。

