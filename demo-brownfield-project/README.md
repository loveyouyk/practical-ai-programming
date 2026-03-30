# Demo 实战验证项目：设备管理模块

> 本项目是 AI 编程落地实战方案的**碰撞验证场**。  
> 目的：用一个模拟棕地历史系统，以对抗思维走完 M-Medium 快车道全流程，检验方法论在实操中是否成立、哪里会断裂。  
> 复盘方式：按下面的目录从头到尾走一遍，等于"亲历"一次完整的 AI 编程落地过程。

---

## 一、复盘路线图

```
┌─────────────────────────────────────────────────────────────────┐
│  Phase 1: 理解棕地项目                                           │
│  ├── 阅读历史代码 → backend/ + frontend/                         │
│  └── 重点关注刻意埋入的 QUIRK（组件怪癖、数据约定）                   │
│                                                                 │
│  Phase 2: 走 M-Medium 全流程                                     │
│  ├── 环节 0: 分流判断 → prompts/00-triage.md                     │
│  ├── 环节 1: 棕地探索 → prompts/01-explore.md                    │
│  ├── 环节 2: M 级起手 → prompts/02-m-medium-kickoff.md           │
│  ├── 环节 3: 生成 OpenSpec → prompts/03-generate-openspec.md     │
│  ├── 环节 4: 审批 → prompts/04-approval.md                      │
│  ├── 环节 5: 后端开发 → prompts/05-backend-impl.md               │
│  ├── 环节 6: 前端开发 → prompts/06-frontend-impl.md              │
│  ├── 环节 7: 变更回写 → prompts/07-change-writeback.md           │
│  ├── 环节 8: 验收矩阵 → prompts/08-acceptance-matrix.md          │
│  └── 环节 9: 结构化证据 → prompts/09-evidence.md                 │
│                                                                 │
│  Phase 3: 审查产出物                                              │
│  ├── OpenSpec 四件套 → openspec/changes/equipment-management/     │
│  ├── 验收矩阵 → artifacts/acceptance-matrix.md                   │
│  ├── 结构化证据 → artifacts/verification-evidence.md              │
│  └── QUIRK 登记簿 → artifacts/quirk-registry.md                  │
│                                                                 │
│  Phase 4: 复盘碰撞点                                              │
│  └── 走查记录（11 个碰撞点）→ DEMO-WALKTHROUGH.md                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## 二、目录结构

```
demo-brownfield-project/
├── README.md                    ← 你正在看的文件（总索引）
├── DEMO-WALKTHROUGH.md          ← 逐环节走查记录 + 11 个碰撞点
│
├── prompts/                     ← 实战验证中实际使用的全部提示词
│   ├── README.md                ← 提示词清单总览
│   ├── 00-triage.md             ← 环节 0: 治理分流
│   ├── 01-explore.md            ← 环节 1: 棕地探索
│   ├── 02-m-medium-kickoff.md   ← 环节 2: M 级起手
│   ├── 03-generate-openspec.md  ← 环节 3: 生成 OpenSpec
│   ├── 04-approval.md           ← 环节 4: 审批
│   ├── 05-backend-impl.md       ← 环节 5: 后端实现
│   ├── 06-frontend-impl.md      ← 环节 6: 前端实现
│   ├── 07-change-writeback.md   ← 环节 7: 变更回写
│   ├── 08-acceptance-matrix.md  ← 环节 8: 验收矩阵
│   └── 09-evidence.md           ← 环节 9: 结构化证据
│
├── openspec/changes/equipment-management/   ← OpenSpec 四件套产出物
│   ├── proposal.md
│   ├── specs/equipment-management/spec.md
│   ├── design.md
│   └── tasks.md
│
├── artifacts/                   ← 验收和证据产出物
│   ├── acceptance-matrix.md     ← M 级精简验收矩阵（7 列）
│   ├── verification-evidence.md ← 5 份结构化证据块
│   └── quirk-registry.md       ← QUIRK 登记簿（7 个 QUIRK）
│
├── backend/                     ← 模拟历史系统后端代码
│   └── src/main/java/com/demo/
│       ├── common/              ← Response, ServiceException, TenantContext, PageResult, ExportUtil
│       ├── controller/          ← ContractController（历史合同模块）
│       ├── service/             ← ContractService, DictService
│       ├── service/impl/        ← ContractServiceImpl
│       └── model/               ← Contract
│
└── frontend/                    ← 模拟历史系统前端代码
    └── src/
        ├── api/                 ← contract.js（历史 API 封装）
        ├── components/          ← SearchForm, BaseTable, FormDialog, ExportButton（含 QUIRK）
        ├── router/modules/      ← business.js（历史路由配置）
        ├── utils/               ← request.js（统一请求封装，含 QUIRK）
        └── views/contract/      ← 合同列表页（完整参考页面）
```

---

## 三、模拟历史系统中刻意埋入的 QUIRK

以下 QUIRK 是故意设计的，模拟真实历史项目中常见的"非直觉行为"。方法论的实战有效性，取决于它能否发现和正确处理这些 QUIRK。

| # | QUIRK | 位置 | 方法论是否发现 |
|---|---|---|---|
| 1 | SearchForm 重置不自动搜索 | `frontend/src/components/SearchForm.vue` | ✅ 环节 1 探索中发现 |
| 2 | BaseTable 默认 pageSize=20 | `frontend/src/components/BaseTable.vue` | ✅ 环节 1 探索中发现 |
| 3 | FormDialog confirm 不自动关闭 | `frontend/src/components/FormDialog.vue` | ✅ 环节 1 探索中发现 |
| 4 | ExportButton 需手动传 params | `frontend/src/components/ExportButton.vue` | ✅ 环节 1 探索中发现 |
| 5 | DictService key 是 String | `backend/.../DictService.java` | ✅ 环节 1 探索中发现 |
| 6 | 错误处理用 window.__toast | `frontend/src/utils/request.js` | ✅ 环节 1 探索中发现 |
| 7 | 后端不返回翻译名称 | `backend/.../ContractServiceImpl.java` | ❌ 环节 1 未发现，环节 5 开发时碰撞 |

**关键结论：** 7 个 QUIRK 中 6 个在探索阶段被发现，1 个在开发阶段才碰撞到（字段翻译约定）。这说明探索阶段的提示词需要增加"字段翻译由谁做"这一检查项。

---

## 四、每份提示词的核心信息

| 环节 | 提示词 | 核心输入 | 核心产出 | 碰撞点 |
|---|---|---|---|---|
| 0 | `00-triage.md` | 需求描述 + 四维度分析 | S/M/L 结论 | #1 缺快速检查表 |
| 1 | `01-explore.md` | 项目代码 + 需求方向 | 巡查摘要 + QUIRK 清单 | #2 三步应合并 #3 缺 QUIRK 登记 |
| 2 | `02-m-medium-kickoff.md` | 巡查结论 + 约束 + 待确认项 | OpenSpec 四件套草案 | #4 缺待确认引导 #5 @openspec 不可用 |
| 3 | `03-generate-openspec.md` | 审批反馈 | OpenSpec 细化版 | 变更回写提前触发 |
| 4 | `04-approval.md` | 四件套 + checklist | 通过/微调/不通过 | #6 缺 checklist #7 缺微调选项 |
| 5 | `05-backend-impl.md` | 约束清单 + 任务 | 后端代码 | #8 字段翻译冲突 #9 线性顺序不适配 |
| 6 | `06-frontend-impl.md` | 约束 + QUIRK 清单 | 前端代码 | QUIRK 传递有效 |
| 7 | `07-change-writeback.md` | 发现的不一致 | design 修正 | 回写流程有效但 M 级可更轻 |
| 8 | `08-acceptance-matrix.md` | requirement 清单 | M 级精简矩阵 | #10 标准矩阵过重 |
| 9 | `09-evidence.md` | 验证操作 | 5 份证据块 | #11 Evidence Location 难填 |

---

## 五、如何用本 Demo 做团队培训

### 方式 A：带团队走一遍（推荐）

1. 先让团队读 `backend/` 和 `frontend/` 代码（模拟"接手历史项目"）
2. 给出需求："新增设备管理模块，支持列表查询、新增、修改"
3. 让团队成员各自尝试按方法论执行，使用 `prompts/` 中的提示词
4. 执行完后对照 `DEMO-WALKTHROUGH.md` 中的碰撞点，讨论"你碰到了吗？你怎么处理的？"
5. 对照 `artifacts/` 中的产出物，讨论"你的产出物和参考产出物有什么差异？"

### 方式 B：复盘式学习

1. 直接读 `DEMO-WALKTHROUGH.md`（11 个碰撞点）
2. 对照 `prompts/` 中每一步的提示词，理解"为什么这样写"和"哪里做了调整"
3. 重点讨论 `artifacts/quirk-registry.md`，培养 QUIRK 意识

### 方式 C：只看碰撞结论

1. 读 `DEMO-WALKTHROUGH.md` 最后的"碰撞点汇总"表格
2. 对照 `review/DEMO-COLLISION-REPORT.md` 看完整分析和修正建议

