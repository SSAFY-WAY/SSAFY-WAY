<template>
  <path class="path" stroke-width="3" :d="props.path"></path>
</template>

<script setup>
const props = defineProps({
  path: String,
});
</script>

<style></style>
