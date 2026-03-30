# Tasks: equipment-management

> 产出环节：环节 2 + 环节 3（审批后补充了 1.3 和 4.4）

## 1. Minimal Discovery

- [x] 1.1 Confirm existing page/API/validation/table naming patterns
- [x] 1.2 Record QUIRK registry (SearchForm reset, BaseTable pageSize, FormDialog confirm, DictService key type)
- [ ] 1.3 Confirm DictService has `equipment_status` key — **待确认**（审批后补充）

## 2. Backend

- [x] 2.1 Add `t_equipment` table (in-memory store for demo)
- [x] 2.2 Add Equipment entity
- [x] 2.3 Add EquipmentService / EquipmentServiceImpl with tenant filtering
- [x] 2.4 Add EquipmentController (list/create/update)
- [x] 2.5 Verify: create with duplicate code returns error
- [x] 2.6 Verify: query filters work correctly
- [x] 2.7 Verify: tenant isolation on query and update

## 3. Frontend

- [x] 3.1 Add route in business.js with meta.title and meta.permission
- [x] 3.2 Add equipment/index.vue using SearchForm + BaseTable + FormDialog
- [x] 3.3 Add api/equipment.js
- [x] 3.4 Handle QUIRK: SearchForm reset → manual re-query
- [x] 3.5 Handle QUIRK: FormDialog confirm → manual close
- [x] 3.6 Handle QUIRK: BaseTable pageSize=20 alignment
- [x] 3.7 Handle: status display via DictService frontend translation

## 4. Verification

- [x] 4.1 Build M-level simplified acceptance matrix
- [x] 4.2 Record structured verification evidence
- [x] 4.3 Verify old contract page still works after changes
- [ ] 4.4 Verify DictService equipment_status in real environment — **待确认**（审批后补充）

