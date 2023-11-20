<template>
  <circle
    class="marker"
    :cx="info[0]"
    :cy="info[1]"
    :r="stationCircle.size"
    :stroke="color"
    stroke-width="1"
    :fill="isSelected() ? color : '#ffffff'"
    uid="1916"
    lineId="1"
    :class="{ nonActive: !active }"
    @mouseover="stationCircle.size = 8"
    @mouseleave="stationCircle.size = 3"
    @click="onClickStation"
  ></circle>

  <g
    class="label-group"
    :class="{ nonActive: !active }"
    style="
      fill: black;
      letter-spacing: -1px;
      font-size: 9px;
      font-weight: normal;
      font-family: NanumGothic, MalgunGothic, Dotum, Arial;
    "
  >
    <text
      class="label labelWrap"
      :class="{ nonActive: !active }"
      :y="info[6]"
      uid="1916"
      lineId="1"
      :style="{
        textAnchor: info[7],
      }"
    >
      <tspan
        @mouseover="stationCircle.size = 8"
        @mouseleave="stationCircle.size = 3"
        @click="onClickStation"
        :x="info[5]"
        :dx="info[3]"
        :dy="info[4]"
        >{{ info[2] }}</tspan
      >
    </text>
  </g>
</template>

<script setup>
import { ref } from "vue";
import { useStationStore } from "@/store/stationStore";
import { storeToRefs } from "pinia";
const { addStation, deleteStation } = useStationStore();
const { selectedStations } = storeToRefs(useStationStore());
const props = defineProps({
  info: Array,
  color: String,
  active: Boolean,
});

// 지하철역 click Event 발생 시 변화시킬 변수들
const stationCircle = ref({
  size: 3,
  selected: false,
});

const isSelected = () => {
  return selectedStations.value.some((e) => {
    return e.name === props.info[2];
  });
};

const onClickStation = () => {
  // 5개가 선택된 경우에는 더 선택 못하도록.
  if (!stationCircle.value.selected && selectedStations.value.length >= 5)
    return;
  // 원래 선택된 역이라면 지우고, 선택 안된 역이라면 추가
  if (stationCircle.value.selected) deleteStation(props.info[2]);
  else addStation({ name: props.info[2], color: props.color });
  // 상태 바꾸기
  stationCircle.value.selected = !stationCircle.value.selected;
};
</script>

<style scoped>
.nonActive {
  opacity: 0.4;
}
tspan {
  font-weight: bold;
  cursor: pointer;
}
.marker {
  cursor: pointer;
}
</style>
