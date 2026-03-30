<template>
  <!--
    Existing shared search form component.
    Used by contract, customer, and all list pages.
    QUIRK: Reset button clears form but does NOT auto-trigger search.
    Caller must handle the re-query in @reset callback.
  -->
  <div class="search-form">
    <slot></slot>
    <div class="search-form__actions">
      <button @click="$emit('search')">查询</button>
      <button @click="handleReset">重置</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchForm',
  methods: {
    handleReset() {
      this.$emit('reset');
      // NOTE: intentionally does NOT emit 'search' after reset
    }
  }
}
</script>
