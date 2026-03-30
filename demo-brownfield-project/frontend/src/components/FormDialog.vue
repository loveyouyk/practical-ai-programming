<template>
  <!--
    Existing shared form dialog. Used by all create/edit forms.
    QUIRK: Dialog emits 'confirm' (not 'submit') when form is submitted.
    QUIRK: Dialog does NOT auto-close on confirm. Caller must close it
    in the @confirm handler after API success.
  -->
  <div class="form-dialog" v-if="visible">
    <div class="form-dialog__header">
      <span>{{ title }}</span>
      <button @click="$emit('close')">×</button>
    </div>
    <div class="form-dialog__body">
      <slot></slot>
    </div>
    <div class="form-dialog__footer">
      <button @click="$emit('close')">取消</button>
      <button @click="$emit('confirm')" :disabled="loading">
        {{ loading ? '提交中...' : '确定' }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormDialog',
  props: {
    visible: { type: Boolean, default: false },
    title: { type: String, default: '' },
    loading: { type: Boolean, default: false }
  }
}
</script>
