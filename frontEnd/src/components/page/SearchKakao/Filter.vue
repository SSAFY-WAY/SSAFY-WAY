<script setup>
defineProps({
  category: String,
});
</script>

<template>
  <fieldset class="fieldset">
    <legend class="legend">{{ category }}</legend>
    <select class="optionContainer">
      <option class="option">{{ category }}</option>
    </select>
  </fieldset>
</template>

<style scoped>
.fieldset {
  border: none;
}
.legend {
  font-size: 20px;
  color: var(--primary);
  font-weight: bold;
}
.optionContainer {
  border: 1px solid lightgray;
  border-radius: 4px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.option {
}
</style>
