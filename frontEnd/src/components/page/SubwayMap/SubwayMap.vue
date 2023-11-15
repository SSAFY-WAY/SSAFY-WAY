<template>
  <SubwayNavigation
    @metro-nav-toggle="metroNavToggle"
    :switchState="switchState"
  />
  <svg
    xmlns="http://www.w3.org/2000/svg"
    version="1.1"
    aria-hidden="true"
    viewBox="0 0 1525 1000"
    style="
      /* position: absolute; */
      z-index: 1000;
      transform-origin: 0px 0px 0px;
      backface-visibility: hidden;
      width: 100%;
      height: 100%;
      /* transform: scale(2.4) scaleZ(1); */
      transition-timing-function: ease;
      cursor: default;
    "
  >
    <!-- path -->
    <template v-for="station in SubwayInfo" :key="station.line">
      <g
        :class="{ nonActive: !switchState[parseInt(station.line[1])] }"
        fill="none"
        :stroke="station.color"
        stroke-linejoin="round"
        stroke-linecap="round"
      >
        <ThePath v-for="paths in station.path" :path="paths" />
      </g>
    </template>
    <!-- Station -->
    <g class="marker-group" transform-origin="50% 50% 0">
      <template v-for="station in SubwayInfo" :key="station.line">
        <Station
          v-for="info in station.info"
          :color="station.color"
          :info="info"
          :active="switchState[parseInt(station.line[1])]"
        />
      </template>
    </g>
    <!-- Label -->
  </svg>
</template>
<script setup>
import ThePath from "./ThePath.vue";
import Station from "./Station.vue";
import SubwayNavigation from "./SubwayNavigation.vue";
import { ref } from "vue";
import SubwayInfo from "../../../assets/SubwayInfo.json";

/**
 * switchState : 각 호선의 선택 유무를 담고 있는 배열 ex) switchState[2] === 2호선의 선택 유무 (true || false)
 * currOn : 현재 켜져있는 호선 개수
 * metrgoNavToggle : 호선 버튼을 클릭하면 호선을 선택하고 switchState를 업데이트 하는 함수
 *
 */
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
</script>

<style scoped>
.nonActive {
  opacity: 0.1;
}
</style>
