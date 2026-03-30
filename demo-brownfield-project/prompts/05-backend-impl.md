# 环节 5：后端实现提示词

> 来源：`execution/BROWNFIELD-AI-PROMPT-HANDBOOK.md` 第 8 节  
> 使用时机：审批通过后，开始后端开发

## 实际使用的提示词

```text
你现在在一个历史 Java 项目中新增设备管理能力。请严格遵守现有项目规范，而不是按通用 Spring Boot 样板生成代码。

已知约束：
1. 必须遵循当前项目已有的 Controller / Service / ServiceImpl 分层
2. 统一返回体使用 Response<T>，不使用 ResponseEntity
3. 异常使用 ServiceException(code, message)，不使用 @ControllerAdvice
4. 租户通过 TenantContext.getTenantId() 获取，所有查询必须过滤 tenant_id
5. 分页使用 PageResult<T>，pageSize 默认值 20
6. 字典使用 DictService.getDictByKey("equipment_status")
7. 后端不做字段翻译，status 返回原始 int 值，不返回 statusName
8. 不允许无关重构

本次任务：
- 新增 EquipmentController（GET /api/equipment/list, POST /api/equipment, PUT /api/equipment/{id}）
- 新增 EquipmentService 接口和 EquipmentServiceImpl
- 新增 Equipment 实体类（参考 Contract 字段风格）
- 表名 t_equipment，必须包含 tenant_id, create_time, update_time, create_by, deleted

请先输出：
1. 你理解到的现有模式
2. 本次改动将落在哪些包/模块
3. 风险点
4. 再给出实现方案
```

## 实际效果

AI 正确按 ContractServiceImpl 的风格生成了 EquipmentServiceImpl，包含：
- tenant_id 过滤
- ServiceException 校验
- Response<T> 返回
- PageResult<T> 分页

但初始版本包含了 `statusName` 字段翻译（碰撞点 #8），在 review 后修正。

## 碰撞发现

- 🔴 碰撞点 #8：即使提示词第 7 条明确写了"后端不做字段翻译"，AI 初始版本仍然生成了 statusName。原因是 AI 的"通用最佳实践"倾向太强。需要在约束中用更强硬的措辞（"严禁返回 statusName 或任何 xxxName 翻译字段"）。
- 🔴 碰撞点 #9：实现后端时发现需要知道"前端查询表单到底传哪些参数名"，但前端还没开始做。实际上在写后端查询接口的 query 参数时，就需要和前端对齐字段名。

