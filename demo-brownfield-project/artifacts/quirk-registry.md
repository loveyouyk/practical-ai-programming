# QUIRK 登记簿（Demo 项目）

> 产出环节：环节 1（棕地探索）中发现，贯穿环节 4-6（开发）全程

## 组件 QUIRK

| 组件 | QUIRK 描述 | 正确应对方式 | 发现环节 | 验证状态 |
|---|---|---|---|---|
| SearchForm | 重置按钮只触发 `@reset` 事件，不自动触发 `@search` | 在 `@reset` 回调中手动调用 `fetchList()` 或等价查询方法 | 环节 1（探索） | ✅ 环节 6（前端实现）中验证 |
| BaseTable | 默认 `pageSize=20`，不是 10 | 查询参数、后端默认值和接口文档都要对齐为 20 | 环节 1（探索） | ✅ 环节 5-6（前后端实现）中对齐 |
| FormDialog | `confirm` 事件不自动关闭弹窗 | 在 `@confirm` 回调中，API 请求成功后手动设 `this.dialogVisible = false` | 环节 1（探索） | ✅ 环节 6（前端实现）中验证 |
| ExportButton | 不自动读取父组件的筛选状态 | 必须显式传入 `:exportParams="queryForm"`，否则导出全量数据 | 环节 1（探索） | N/A（本次不含导出功能，但记录备用） |

## 服务 QUIRK

| 服务 | QUIRK 描述 | 正确应对方式 | 发现环节 | 验证状态 |
|---|---|---|---|---|
| DictService | `getDictByKey()` 返回的 key 是 **String** 类型 | 前端拿到 `status`(int) 后需要 `String(status)` 转换再匹配 | 环节 1（探索） | ✅ 环节 6（前端实现）中处理 |
| request.js | 错误处理通过 `window.__toast` 全局弹出，不返回错误对象给调用方 | 如果需要在业务代码中捕获错误做特殊处理，需要在 request 拦截器中加 flag | 环节 1（探索） | N/A（本次未触发该 QUIRK，但记录备用） |

## 数据约定 QUIRK

| 约定 | QUIRK 描述 | 正确应对方式 | 发现环节 | 验证状态 |
|---|---|---|---|---|
| 字段翻译 | 后端**不返回** `xxxName` 翻译字段，前端用 DictService 本地翻译 | 新增模块严禁在后端拼接 statusName / typeName 等翻译字段 | 环节 1（探索）+ 环节 7（变更回写确认） | ✅ 环节 5（后端实现）中按约定处理 |
| 文件命名 | 导出文件必须符合 `{module}_{timestamp}.xlsx` 格式 | 调用 ExportUtil 时按规范传 fileName | 环节 1（探索） | N/A（本次不含导出） |
| pageSize 对齐 | 前端 BaseTable 默认 20 + 后端接口默认 20 + 查询参数默认 20，三者必须一致 | 新增模块的查询 data 初始化 `pageSize: 20` | 环节 1 + 环节 7 | ✅ 前后端均对齐为 20 |

## QUIRK 对本次开发的实际影响

1. **FormDialog confirm**：如果不知道这个 QUIRK，新增设备提交后弹窗不会关闭，用户会以为没提交成功而重复点击
2. **字段翻译**：如果不知道后端不翻译的约定，design 中会写 statusName，开发时又要回写 design（实际发生了碰撞点 #8）
3. **DictService String key**：如果不知道 key 类型不一致，前端状态下拉和列表状态显示会对不上

