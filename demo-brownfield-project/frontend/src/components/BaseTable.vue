<template>
  <!--
    Existing shared table component with pagination.
    QUIRK: Pagination defaults to pageSize=20, not 10.
    This is different from what most people expect.
    The backend also defaults to 20 in the contract module.
  -->
  <div class="base-table">
    <table>
      <slot></slot>
    </table>
    <div class="pagination">
      <span>共 {{ total }} 条</span>
      <span>第 {{ pageNum }} / {{ Math.ceil(total / pageSize) }} 页</span>
      <button @click="$emit('page-change', pageNum - 1)" :disabled="pageNum <= 1">上一页</button>
      <button @click="$emit('page-change', pageNum + 1)" :disabled="pageNum >= Math.ceil(total / pageSize)">下一页</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BaseTable',
  props: {
    total: { type: Number, default: 0 },
    pageNum: { type: Number, default: 1 },
    pageSize: { type: Number, default: 20 }  // NOTE: default is 20, not 10
  }
}
</script>
