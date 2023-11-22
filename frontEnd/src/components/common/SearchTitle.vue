<template>
  <div @click="changeToggle" class="searchTitle">
    <span class="searchTitle-text">검색 조건을 설정해주세요 </span>
    <div class="searchTitle-icon">
      <v-icon icon="mdi-triangle-down" size="18px" color="white"></v-icon>
    </div>
  </div>
</template>
<script setup>
const changeToggle = () => {
  emit("changeToggle");
};
const emit = defineEmits(["changeToggle"]);
</script>
<style>
.searchTitle {
  height: 30px;
  display: flex;
  align-items: center;
  border: 1px solid var(--primary);
  border-radius: 5px;
  color: black;
}
.searchTitle:hover {
  background-color: rgba(245, 245, 245);
  color: black;
}
.searchTitle-text {
  width: 90%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 13px;
  font-weight: normal;
}
.searchTitle-icon {
  width: 15%;
  height: 100%;
  background-color: var(--primary);
  border-top-right-radius: 3px;
  border-bottom-right-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
