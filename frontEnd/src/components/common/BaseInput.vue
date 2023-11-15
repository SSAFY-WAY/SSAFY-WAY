<template>
  <div class="input-container">
    <label class="label" for="">{{ label }}</label>
    <input class="input" :type="type" :placeholder="placeholder" />
  </div>
</template>

<script setup>
const props = defineProps({
  label: String,
  placeholder: String,
  type: String,
});
</script>

<style scoped>
.input-container {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 15px 0;
}
.label {
  font-size: 20px;
}
.input {
  border-bottom: 1px solid gray;
}
</style>
