<template>
  <div class="mapNavigateWrap">
    <!-- noticeLine -->
    <div class="selectMetroLine">
      <button
        class="metroButton"
        :class="{ nonActive: !props.switchState[number] }"
        v-for="number in metroNumber"
        :key="number"
        @click="metroNavToggle(number)"
        :style="{
          backgroundColor: colors[number - 1],
        }"
      >
        {{ number }} 호선
      </button>
    </div>
    <!-- noticeLimit -->
    <span class="notice"> ※ 최대 5개의 지하철 역을 선택할 수 있어요.</span>

    <!-- requestDataBox-->
  </div>
  <span v-if="selectedStations.length" class="request container">
    <div class="stationBucket container">
      선택한 역
      <StationIcon
        v-for="station in selectedStations"
        :name="station.name"
        :color="station.color"
      />
    </div>
    <div class="button container">
      <v-slider
        class="slider"
        label="역까지의 거리 (미터)"
        step="100"
        show-ticks="always"
        thumb-label="always"
        v-model="distance"
        color="accent4"
        min="0"
        max="1500"
      >
        <!-- <template v-slot:thumb-label="{ dist }">{{ dist }}M </template> -->
      </v-slider>
      <button class="searchButton" @click="requestData">조회하기</button>
    </div>
  </span>
</template>

<script setup>
import { ref } from "vue";
import StationIcon from "@/components/page/SubwayMap/StationIcon.vue";
import { useStationStore } from "@/store/stationStore.js";
import { storeToRefs } from "pinia";
const props = defineProps({
  switchState: Array,
  // selectedStationState: Array,
});
// pinia에서 지하철 역 데이터 관리
const { selectedStations } = storeToRefs(useStationStore());
// 노선도 색깔 로직
const metroNumber = ref([1, 2, 3, 4, 5, 6, 7, 8, 9]);
const emit = defineEmits(["metroNavToggle"]);
const metroNavToggle = (number) => {
  emit("metroNavToggle", number);
};
const colors = [
  "#052f93",
  "#10a643",
  "#de6d00",
  "#0099d1",
  "#a95094",
  "#d08d1a",
  "#657931",
  "#e74e6d",
  "#b58600",
];

// 반경 범위 로직
const distance = ref(0);
</script>

<style scoped>
.mapNavigateWrap {
  display: flex;
  position: relative;
  flex-direction: column;
  margin-bottom: 50px;
  /* border: 1px solid black; */
}
.slider {
  margin-top: 15px;
  margin-right: 15px;
}
.notice {
  position: relative;
  top: 15px;
  left: 30px;
  font-weight: bold;
  color: var(--accent4);
  padding: 5px;
  border-radius: 10px;
}

.selectMetroLine {
  margin-top: 20px;
  margin-left: 20px;
  border: 1px solid lightgray;
  padding: 10px;
  width: 480px;
  display: flex;
  border-radius: 3px;
  justify-content: space-around;
}
.metroButton {
  width: 40px;
  height: 40px;

  color: white;
  border-radius: 50%;
  font-size: 12px;
}
.nonActive {
  opacity: 0.1;
}

.request.container {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid lightgray;
  position: absolute;
  background-color: #f3f3f3;
  border-radius: 15px;
  top: 80px;
  right: 30px;

  /* z-index: 999; */
}
.stationBucket.container {
  display: inline;
  width: auto;
  height: 60px;
  display: flex;
  align-items: center;
  border-radius: 5px;
  padding: 8px;
  color: var(--accent4);
  font-weight: bold;
}
.button.container {
  display: flex;
  margin-top: 10px;
  width: 500px;
  /* border: 1px solid black; */
  /* align-items: center; */
}
.searchButton {
  margin-top: 10px;
  width: auto;
  padding: 10px;
  height: 40px;
  background-color: var(--accent4);
  color: white;
  font-size: 15px;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 30px;
}
.searchButton:hover {
  background-color: var(--light1);
}
</style>
