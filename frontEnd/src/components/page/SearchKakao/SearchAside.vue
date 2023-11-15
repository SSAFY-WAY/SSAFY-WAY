<script setup>
import { ref } from "vue";
import Filter from "./Filter.vue";
import SearchTitle from "./SearchTitle.vue";
// searchTitle Logic
const isShow = ref(false);
const toggle = () => {
  isShow.value = !isShow.value;
};
// searchOptions Logic
const searchOptions = ref([{ title: "지역구" }, { title: "동" }]);

//자치구 호출 : 특정 도시 클릭 -> 자치구 api 호출 -> 자치구 html태그 내용 업데이트

//법정동 호출 : 특정 자치구 클릭 -> 법정동 api 호출 -> 법정동 html 태그 업데이트
</script>

<template>
  <div class="searchAsideContainer">
    <SearchTitle @change-toggle="toggle" />
    <form
      v-show="isShow"
      action="#"
      name="address-info"
      method="get"
      class="searchOptions"
    >
      <div class="searchOptionsTitle">필터</div>
      <Filter
        v-for="(opt, index) in searchOptions"
        :key="index"
        :category="opt.title"
      />
      <button class="submitBtn">정보 조회</button>
    </form>
  </div>
</template>

<style>
.searchAsideContainer {
  width: 300px;
  height: auto;
  padding: 5px;
  position: absolute;
  top: 100px;
  left: 50px;
  z-index: 999;
  border-radius: 2px;
  background-color: white;
  border: 1px solid lightgray;
}
.searchOptions {
  margin-top: 10px;
}
.searchOptionsTitle {
  padding: 10px 0;
  border-bottom: 1px solid lightgray;
  margin-bottom: 10px;
  font-size: 20px;
}
</style>
