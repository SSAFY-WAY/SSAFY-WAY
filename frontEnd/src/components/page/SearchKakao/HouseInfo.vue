<template>
  <div>{{ houseInfo.price }} 만원</div>
</template>

<script setup>
const props = defineProps({
  houseInfo: Object,
});
</script>

<style></style>
