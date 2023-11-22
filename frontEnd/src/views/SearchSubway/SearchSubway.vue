<template>
  <SubwayNavigation
    @metro-nav-toggle="metroNavToggle"
    :switchState="switchState"
    @send-request-data="requestSubwayData"
  />
  <SubwayMap :switchState="switchState" />
</template>

<script setup>
import SubwayMap from "@/components/page/SubwayMap/SubwayMap.vue";
import SubwayNavigation from "@/components/page/SubwayMap/SubwayNavigation.vue";
import { ref } from "vue";
import router from "@/router";

// 노선도 색깔 상태 관리
const currOn = ref(9);
const switchState = ref(new Array(10).fill(true));
const metroNavToggle = (number) => {
  // 최초 다 켜진 상태에서는 모두 꺼주기
  if (currOn.value === 9) {
    switchState.value = new Array(10).fill(false);
    currOn.value = 1;
    switchState.value[number] = true;
    return;
  }
  // 켜진 지하철이면 꺼주고, 안켜진 지하철이면 켜주기

  switchState.value[number] ? currOn.value-- : currOn.value++;
  switchState.value[number] = !switchState.value[number];

  // 다 켜지거나 다 꺼진 상태가 된다면 다시 다 켜주기
  if (currOn.value === 9 || currOn.value === 0) {
    switchState.value = new Array(10).fill(true);
    currOn.value = 9;
  }
};

// 지하철 데이터 요청 보내기
const requestSubwayData = (stations, distance) => {
  const data = {
    minPrice: 0,
    maxPrice: 1000000000,
    minArea: 0,
    maxArea: 1000,
    types: ["오피스텔", "아파트", "연립다세대", "단독다가구"],
    subwayNameList: stations,
    distance: distance,
  };
  router.push({
    name: "searchKakao",
    state: {
      data: data,
    },
  });
};
</script>

<style></style>
