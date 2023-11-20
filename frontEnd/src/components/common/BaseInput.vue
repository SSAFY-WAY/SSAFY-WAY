<template>
  <div class="input-container">
    <label class="label">{{ label[0] }}</label>
    <input
      class="input"
      :type="type"
      :placeholder="placeholder"
      @input="emit('update', label[1], $event.target.value)"
      :value="content"
    />
  </div>
</template>

<script setup>
const props = defineProps({
  label: Array,
  placeholder: String,
  type: String,
  content: String,
});
const emit = defineEmits(["update"]);
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
