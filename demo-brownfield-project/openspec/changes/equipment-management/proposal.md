# Proposal: Equipment Management Module

> 产出环节：环节 2（M 级起手）+ 环节 3（审批后微调）

## Why

当前系统缺少设备资产的集中管理入口。业务团队需要对设备基本信息进行查询、新增和修改，以替代目前的线下 Excel 管理方式。

## What Changes

- 新增 `equipment-management` capability
- 后端新增设备 CRUD 接口，复用现有 Controller/Service/Mapper 分层和统一返回体
- 前端新增设备管理列表页，复用现有 SearchForm、BaseTable、FormDialog 组件
- 新增数据库表 `t_equipment`

## Scope

### In Scope

1. 设备列表查询（支持按名称、编号、状态筛选）
2. 新增设备
3. 修改设备

### Out of Scope

1. 设备导出（本期不含）
2. 设备删除
3. 设备详情页
4. 设备与项目/合同的关联管理

## Impact

- 前端：新增独立页面和路由，不修改现有页面
- 后端：新增独立接口和表，不修改现有接口
- 兼容性：不修改权限模型、租户逻辑、导出语义和现有接口契约

## Risks

1. 设备状态枚举来源待确认（字典服务 vs 固定枚举）
2. 设备编号唯一性范围待确认（全局 vs 租户内）

