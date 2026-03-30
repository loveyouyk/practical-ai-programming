# 环节 6：前端实现提示词

> 来源：`execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 第 9 节  
> 使用时机：后端基本完成后，开始前端开发

## 实际使用的提示词

```text
你现在在一个历史前端项目中新增设备管理页面。请优先复用现有页面模式。

已知约束（来自探索）：
1. 必须沿用当前路由配置方式（router/modules/business.js），需要 meta.title 和 meta.permission
2. 必须复用 SearchForm 组件 — 注意 QUIRK：重置后不会自动搜索，需要在 @reset 回调中手动调用查询方法
3. 必须复用 BaseTable 组件 — 注意 QUIRK：默认 pageSize=20，不是 10
4. 必须复用 FormDialog 组件 — 注意 QUIRK：confirm 事件不会自动关闭弹窗，需在 API 成功后手动设 visible=false
5. 必须沿用 utils/request.js 的 request() 方法
6. API 文件放在 api/equipment.js，参考 api/contract.js 的格式
7. 状态字段前端用 DictService 翻译，不依赖后端返回 statusName — 注意 QUIRK：DictService key 是 String 类型
8. 不允许引入新的 UI 组件库或样式框架

本次任务：
- 新增 views/equipment/index.vue（列表页，参考 views/contract/index.vue）
- 新增 api/equipment.js
- 在 router/modules/business.js 中新增路由

请先输出：
1. 现有页面结构和可复用组件
2. 本次会影响哪些旧页面或公共组件（应为 none）
3. 你注意到的 QUIRK 及应对方式
4. 再给出实现方案
```

## 与方法论原版的差异

原版提示词（手册第 9 节）有 6 条约束。实操中关键变化是：
- 每个共享组件后面都附上了 QUIRK 说明（重置行为、confirm 行为、pageSize 默认值）
- 要求 AI 在输出中明确列出"你注意到的 QUIRK 及应对方式"（检验 AI 是否真的理解了 QUIRK）
- DictService 的 String key 类型在前端上下文中再次强调

## 实际效果

AI 正确生成了参考 contract/index.vue 的设备管理页面，包含：
- SearchForm + @reset 手动调查询 ✅
- BaseTable pageSize=20 ✅
- FormDialog confirm 后手动关闭 ✅
- DictService 前端翻译 ✅

关键证据：AI 在输出中主动列出了"我注意到 FormDialog 的 confirm 不自动关闭，需要在提交成功后手动关闭"。说明 QUIRK 信息在提示词中传递有效。

## 碰撞发现

- QUIRK 信息写在提示词约束里比写在 rules 文件里更直接有效（对单次会话而言）
- 但 QUIRK 信息不能只靠提示词传递 — 如果换一个 AI 会话，QUIRK 信息就丢了。所以 QUIRK 登记簿（持久化）+ 提示词嵌入（当次会话）需要双重机制。

