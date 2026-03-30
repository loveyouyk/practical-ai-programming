<template>
  <!--
    Existing shared export button.
    QUIRK: Requires the caller to pass `exportParams` — the component does NOT
    auto-read from any parent form. This is intentional but often causes bugs
    when developers forget to bind the current filter state.
  -->
  <button class="export-btn" @click="handleExport" :disabled="loading">
    {{ loading ? '导出中...' : '导出' }}
  </button>
</template>

<script>
export default {
  name: 'ExportButton',
  props: {
    exportFn: { type: Function, required: true },
    exportParams: { type: Object, default: () => ({}) }
  },
  data() {
    return { loading: false };
  },
  methods: {
    async handleExport() {
      this.loading = true;
      try {
        await this.exportFn(this.exportParams);
      } catch (e) {
        console.error('导出失败', e);
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>
