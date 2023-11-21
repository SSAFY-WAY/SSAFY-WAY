<template>
  <div class="mapNavigateWrap">
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
    <div v-if="selectedStations.length" class="stationBucket container">
      <StationIcon
        v-for="station in selectedStations"
        :name="station.name"
        :color="station.color"
      />
    </div>
    <button class="searchButton">조회하기</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import StationIcon from "@/components/page/SubwayMap/StationIcon.vue";
import { useStationStore } from "@/store/stationStore.js";
import { storeToRefs } from "pinia";
const props = defineProps({
  switchState: Array,
  selectedStationState: Array,
});
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
// 지하철역 선택 로직
</script>

<style scoped>
.mapNavigateWrap {
  display: flex;
}
.selectMetroLine {
  margin-top: 20px;
  margin-left: 20px;
  border: 1px solid var(--primary);
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
.stationBucket.container {
  width: auto;
  margin-top: 15px;
  margin-left: 100px;
  height: 60px;
  border: 2px solid var(--accent1);
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-radius: 5px;
  background-color: #f3f3f3;
}
.searchButton {
  margin-top: 25px;
  margin-left: 20px;
  width: auto;
  padding: 10px;
  height: 40px;
  background-color: var(--primary);
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
