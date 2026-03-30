<template>
  <div class="contract-page">
    <SearchForm @search="handleSearch" @reset="handleReset">
      <input v-model="queryForm.contractName" placeholder="合同名称" />
      <input v-model="queryForm.contractCode" placeholder="合同编号" />
      <select v-model="queryForm.status">
        <option value="">全部状态</option>
        <option v-for="(label, key) in statusDict" :key="key" :value="key">{{ label }}</option>
      </select>
    </SearchForm>

    <div class="toolbar">
      <button @click="openCreate">新增合同</button>
      <ExportButton :exportFn="exportContracts" :exportParams="queryForm" />
    </div>

    <BaseTable :total="total" :pageNum="queryForm.pageNum" :pageSize="queryForm.pageSize"
               @page-change="handlePageChange">
      <!-- table columns -->
    </BaseTable>

    <FormDialog :visible="dialogVisible" :title="dialogTitle" :loading="submitLoading"
                @confirm="handleSubmit" @close="dialogVisible = false">
      <!-- form fields -->
    </FormDialog>
  </div>
</template>

<script>
import SearchForm from '@/components/SearchForm.vue';
import BaseTable from '@/components/BaseTable.vue';
import FormDialog from '@/components/FormDialog.vue';
import ExportButton from '@/components/ExportButton.vue';
import { getContractList, createContract, updateContract, exportContracts } from '@/api/contract';

export default {
  name: 'ContractPage',
  components: { SearchForm, BaseTable, FormDialog, ExportButton },
  data() {
    return {
      queryForm: {
        contractName: '',
        contractCode: '',
        status: '',
        pageNum: 1,
        pageSize: 20   // matches BaseTable default
      },
      tableData: [],
      total: 0,
      dialogVisible: false,
      dialogTitle: '',
      submitLoading: false,
      formData: {},
      statusDict: {}
    };
  },
  methods: {
    handleSearch() {
      this.queryForm.pageNum = 1;
      this.fetchList();
    },
    handleReset() {
      this.queryForm = { contractName: '', contractCode: '', status: '', pageNum: 1, pageSize: 20 };
      // NOTE: intentionally does NOT auto-search after reset (matches SearchForm quirk)
    },
    handlePageChange(page) {
      this.queryForm.pageNum = page;
      this.fetchList();
    },
    async fetchList() {
      const res = await getContractList(this.queryForm);
      if (res.code === 200) {
        this.tableData = res.data.list;
        this.total = res.data.total;
      }
    },
    openCreate() {
      this.dialogTitle = '新增合同';
      this.formData = {};
      this.dialogVisible = true;
    },
    async handleSubmit() {
      this.submitLoading = true;
      try {
        if (this.formData.id) {
          await updateContract(this.formData.id, this.formData);
        } else {
          await createContract(this.formData);
        }
        this.dialogVisible = false;
        this.fetchList();
      } finally {
        this.submitLoading = false;
      }
    },
    exportContracts
  }
};
</script>
