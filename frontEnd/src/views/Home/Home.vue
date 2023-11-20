<script setup>
import { ref } from "vue";
import Carousel from "../../components/page/Home/Carousel.vue";
import InputSearch from "../../components/page/Home/InputSearch.vue";
const inputText = ref("");
</script>

<template>
  <Carousel color="primary" />
  <div class="input-title text-primary">
    <span>어떤 집을 찾으세요?</span>
    <router-link to="/searchSubway">지하철로 찾기</router-link>
    <router-link to="/searchKakao">지도로 찾기</router-link>
  </div>
  <InputSearch />
</template>

<style scoped>
.input-container {
  margin: 0 auto;
  width: 800px;
  display: flex;
  justify-content: center;
  align-content: center;
}
.input-title {
  width: 800px;
  height: 80px;
  margin: 25px auto;
  background-color: var(--primary);
  border-radius: 30px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.input-title :first-child {
  color: white;
  font-size: 30px;
  font-weight: bold;
}
.input-title a {
  width: auto;
  padding: 10px;
  height: 40px;
  background-color: var(--dark2);
  color: white;
  font-size: 15px;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 30px;
}
.input-title a:hover {
  background-color: black;
}
</style>
