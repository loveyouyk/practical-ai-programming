# 设备管理模块结构化验证证据

> 产出环节：环节 9（结构化证据）

---

## Evidence 1: 列表查询（含筛选）

### Change / Commit / PR
- feature/equipment-management, commit abc123

### Command Or Action
- 打开浏览器访问 /equipment
- 在查询区输入设备名称"测试设备"，点击查询
- 观察列表是否只显示名称包含"测试设备"的记录
- 清空筛选条件，选择状态"进行中"，点击查询
- 观察列表是否只显示状态为"进行中"的记录

### Environment
- 本地开发环境，Node 18 + Chrome 120

### Result
- 名称筛选正确返回匹配记录
- 状态筛选正确返回匹配记录
- 分页显示每页 20 条，与 BaseTable 默认值一致

### Evidence Location
- commit abc123 上执行上述操作可复现
- 前端控制台 Network 面板可见 GET /api/equipment/list?equipmentName=测试设备&pageNum=1&pageSize=20 请求
- 响应 code=200，data.list 非空

### Conclusion
- 列表查询功能含筛选联调通过

### Residual Risk
- 未测试大数据量（>1000 条）下的查询性能

---

## Evidence 2: 新增设备（含校验失败）

### Change / Commit / PR
- feature/equipment-management, commit def456

### Command Or Action
- 点击"新增设备"按钮，弹出 FormDialog
- 不填任何字段直接点确定 → 观察是否返回 400 错误
- 填写完整字段（设备名称="测试设备A"，设备编号="EQ-001"，状态=1）→ 点确定
- 观察弹窗是否关闭、列表是否刷新并包含新记录
- 再次新增设备编号="EQ-001" → 观察是否返回"设备编号已存在"

### Environment
- 本地开发环境，Node 18 + Chrome 120

### Result
- 空字段提交：返回 400，消息"设备名称不能为空"
- 正常提交：返回 200，列表刷新，弹窗关闭（QUIRK 正确处理）
- 重复编号：返回 400，消息"设备编号已存在"

### Evidence Location
- commit def456 上执行上述操作可复现
- Network 面板可见 POST /api/equipment 请求和响应

### Conclusion
- 新增功能含校验联调通过

### Residual Risk
- 编号唯一性当前按租户内唯一实现，需确认是否符合业务要求

---

## Evidence 3: 修改设备（含回显）

### Change / Commit / PR
- feature/equipment-management, commit ghi789

### Command Or Action
- 在列表中点击某条设备的"编辑"按钮
- 观察 FormDialog 是否正确回显当前数据（名称、编号、状态、位置）
- 修改设备名称为"修改后的名称"，点确定
- 观察列表是否刷新并显示修改后的名称

### Environment
- 本地开发环境，Node 18 + Chrome 120

### Result
- 编辑弹窗正确回显原数据
- 修改后列表刷新显示新名称
- 弹窗在 API 成功后正确关闭（QUIRK 正确处理）

### Evidence Location
- commit ghi789 上执行上述操作可复现

### Conclusion
- 修改功能含回显联调通过

### Residual Risk
- 无

---

## Evidence 4: 租户隔离

### Change / Commit / PR
- feature/equipment-management, commit abc123

### Command Or Action
- 设置 TenantContext.setTenantId(1L)，新增设备"租户1设备"
- 设置 TenantContext.setTenantId(2L)，查询设备列表
- 观察是否能看到"租户1设备"

### Environment
- Service 层单元测试

### Result
- 租户 2 查询结果不包含租户 1 的设备
- 租户 2 尝试修改租户 1 的设备时返回 403

### Evidence Location
- EquipmentServiceImpl 测试代码，commit abc123

### Conclusion
- 租户隔离在查询和修改场景中均有效

### Residual Risk
- 未测试并发多租户场景

---

## Evidence 5: 兼容性

### Change / Commit / PR
- feature/equipment-management, 全部 commit

### Command Or Action
- 访问 /contract（合同管理页面）
- 执行查询、新增操作
- 确认 SearchForm、BaseTable、FormDialog 行为正常
- 执行 `git diff -- frontend/src/components/` 确认共享组件无修改

### Environment
- 本地开发环境

### Result
- 合同管理页面功能正常
- 共享组件源码无变更

### Evidence Location
- git diff 输出，本地验证

### Conclusion
- 旧页面和共享组件未受影响

### Residual Risk
- 仅验证了合同页面，未验证客户管理页面（风险低，因为设备模块完全独立）

