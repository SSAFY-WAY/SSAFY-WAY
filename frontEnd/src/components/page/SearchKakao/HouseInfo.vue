<template>
  <div class="houseInfo-container">
    <section class="title container">
      <span>서울시 {{ props.regionName }}</span>
    </section>
    <section class="info container">
      <span>전세</span>
      <span v-if="price[0] != 0"> {{ price[0] }}억</span>
      <span v-if="price[1] != 0"> {{ price[1] }}만원</span>
    </section>
    <section class="title container">
      <span>건물 {{ houseInfo.floor }}층</span>
    </section>
    <v-btn color="primary" class="button"> 문의하기 </v-btn>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
const props = defineProps({
  houseInfo: Object,
  regionName: String,
});
const transferPrice = (price) => {
  return [parseInt(price / 10000), parseInt(price % 10000)];
};
const price = computed(() => {
  return transferPrice(props.houseInfo.price);
});
</script>

<style scoped>
.houseInfo-container {
  position: relative;
}
.container {
  padding: 1px;
}
.title.container {
  font-size: 14px;
}

.info.container {
  display: flex;
  font-size: 23px;
  width: 50%;
  align-items: center;
  font-weight: bold;
}
.info.container :first-child {
  margin-right: 3px;
}
.button {
  position: absolute;
  font-weight: bold;
  right: 0px;
  top: 51px;
}
</style>
