<template>
  <circle
    class="marker"
    :cx="info[0]"
    :cy="info[1]"
    :r="stationCircle.size"
    :stroke="color"
    stroke-width="1"
    :fill="stationCircle.selected ? color : '#ffffff'"
    uid="1916"
    lineId="1"
    :class="{ nonActive: !active }"
    @mouseover="stationCircle.size = 8"
    @mouseleave="stationCircle.size = 3"
    @click="stationCircle.selected = !stationCircle.selected"
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
        @click="stationCircle.selected = !stationCircle.selected"
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
