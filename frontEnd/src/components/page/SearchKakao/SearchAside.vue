<script setup>
import { ref, computed } from "vue";

import SearchTitle from "@/components/common/SearchTitle.vue";

import { requestDistrict } from "@/apis/request/requestDistrict.js";
import { requestLegalDong } from "@/apis/request/requestLegalDong.js";
import { requestBuilding } from "@/apis/request/requestBuilding.js";
// emit
const emit = defineEmits(["buildingList"]);
// searchTitle Logic
const isShow = ref(false);
const toggle = () => {
  isShow.value = !isShow.value;
};
// api 데이터
const districtList = ref([]);
const legalDongList = ref([]);
const selectedDistrict = ref({});
const selectedLegalDong = ref({});
const requestData = computed(() => {
  return {
    minPrice: 0,
    maxPrice: 100000000,
    minArea: 0,
    maxArea: 100,
    types: ["오피스텔", "아파트", "연립다세대", "단독다가구"],
    districtName: selectedDistrict.value.name,
    legalDongName: selectedLegalDong.value.name,
    districtCode: selectedDistrict.value.code,
    legalDongCode: selectedLegalDong.value.code,
  };
});
//자치구 호출 : 특정 도시 클릭 -> 자치구 api 호출 -> 자치구 html태그 내용 업데이트
const getDistrict = () => {
  requestDistrict()
    .then((data) => {
      console.log(data);
      districtList.value = data.data.districtList;
    })
    .catch((err) => {
      alert(err);
    });
};
//법정동 호출 : 특정 자치구 클릭 -> 법정동 api 호출 -> 법정동 html 태그 업데이트
const getLegalDong = () => {
  requestLegalDong(requestData.value.districtCode)
    .then((data) => {
      legalDongList.value = data.data.legalDongList;
    })
    .catch((err) => {
      alert(err);
    });
};
// 매물 정보 호출 -> emit으로 파라미터 방출
const getBuildingList = (e) => {
  e.preventDefault();
  requestBuilding(requestData.value)
    .then((buildingList) => {
      console.log(buildingList.data);
      emit("buildingList", buildingList.data);
    })
    .catch((err) => {
      alert(err);
    });
};
</script>

<template>
  <div class="searchAsideContainer">
    <SearchTitle @change-toggle="toggle" />
    <form v-show="isShow" action="#" name="address-info" class="searchOptions">
      <div class="searchOptionsTitle">필터</div>
      <!-- 자치구 호출 -->
      <fieldset class="fieldset">
        <legend class="legend">자치구</legend>
        <select
          v-model="selectedDistrict"
          class="optionContainer"
          @click="getDistrict"
        >
          <option value="" selected disabled hidden>자치구 선택</option>
          <option v-for="district in districtList" :value="district">
            {{ district.name }}
          </option>
        </select>
        <!-- <v-select
          variant="underlined"
          label="자치구"
          :items="[
            'California',
            'Colorado',
            'Florida',
            'Georgia',
            'Texas',
            'Wyoming',
          ]"
        ></v-select> -->
      </fieldset>
      <!-- 법정동 호출 -->
      <fieldset class="fieldset">
        <legend class="legend">법정동</legend>
        <select
          class="optionContainer"
          @click="getLegalDong"
          v-model="selectedLegalDong"
        >
          <option value="" hidden selected disabled>법정동 선택</option>
          <option v-for="dong in legalDongList" :value="dong">
            {{ dong.name }}
          </option>
        </select>
      </fieldset>
      <!-- 호출 버튼 -->
      <button class="searchOptions submitBtn" @click="getBuildingList">
        정보 조회
      </button>
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
  font-weight: bold;
}
.searchOptions.submitBtn {
  width: 100%;
  background-color: var(--primary);
  color: white;
  border-radius: 8px;
  font-weight: 600;
  font-size: 17px;
  padding: 5px;
}
.searchOptions.submitBtn:hover {
  background-color: var(--dark1);
}
.fieldset {
  border: none;
  padding: 10px;
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
</style>
