# 设备管理模块功能验收矩阵（M 级精简版）

> 产出环节：环节 8（验收矩阵）

| Requirement | Scenario | 实现状态 | 联调验证 | 证据 | 状态 | 备注 |
|---|---|---|---|---|---|---|
| Query Equipment List | 按名称筛选 | 前后端均完成 | 输入"测试"搜索返回匹配结果 | commit abc123, 控制台 Network 日志 | 联调通过 | |
| Query Equipment List | 按编号精确查询 | 前后端均完成 | 输入完整编号返回唯一结果 | commit abc123, 控制台 Network 日志 | 联调通过 | |
| Query Equipment List | 按状态筛选 | 前后端均完成 | 选择"进行中"筛选正确 | commit abc123 | 联调通过 | 状态翻译由前端完成 |
| Query Equipment List | 租户隔离 | 后端完成 | 切换 TenantContext 后数据隔离 | commit abc123, Service 层测试 | 联调通过 | |
| Query Equipment List | 空结果 | 前后端均完成 | 无匹配时返回空列表，不报错 | commit abc123 | 联调通过 | |
| Create Equipment | 新增成功 | 前后端均完成 | 填写表单提交后列表刷新 | commit def456 | 联调通过 | |
| Create Equipment | 必填校验 | 前后端均完成 | 空名称提交返回 400 错误 | commit def456 | 联调通过 | |
| Create Equipment | 编号唯一性 | 前后端均完成 | 重复编号返回"设备编号已存在" | commit def456 | 联调通过 | 当前按租户内唯一实现 |
| Update Equipment | 修改成功 | 前后端均完成 | 修改名称后列表显示更新 | commit ghi789 | 联调通过 | |
| Update Equipment | 数据回显 | 前端完成 | 点编辑弹窗正确回显原数据 | commit ghi789 | 联调通过 | |
| Update Equipment | 租户隔离 | 后端完成 | 修改其他租户数据返回 403 | commit ghi789, Service 层测试 | 联调通过 | |
| Compatibility | 旧页面不受影响 | N/A | 合同管理页面正常查询/新增 | 手动验证 | 联调通过 | |
| Compatibility | 共享组件未修改 | N/A | SearchForm/BaseTable/FormDialog 源码无变更 | git diff 确认 | 联调通过 | |

## 验收结论

**部分达标**

已达标：
- 所有 requirement 的 scenario 均已联调通过
- 旧页面兼容性已验证
- 共享组件未被修改

未达标：
- `equipment_status` 字典在真实 DictService 中是否存在 — **待确认**
- `equipment_code` 唯一性范围最终决策 — **待确认**（当前按租户内唯一实现）

> 此结论为基于证据的验收建议，不替代最终人工审批。

