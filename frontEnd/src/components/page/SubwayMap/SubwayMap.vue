<template>
  <!-- <SubwayNavigation
    @metro-nav-toggle="metroNavToggle"
    :switchState="switchState"
  /> -->
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
    <template v-for="(station, index) in SubwayInfo" :key="station.line">
      <g
        :class="{ nonActive: !switchState[index + 1] }"
        fill="none"
        :stroke="station.color"
        stroke-linejoin="round"
        stroke-linecap="round"
      >
        <ThePath v-for="paths in station.path" :path="paths" />
        <g>
          <LineNumber
            v-for="locs in station.lineNumber"
            :locs="locs"
            :color="station.color"
            :line="index + 1"
          />
        </g>
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
  </svg>
</template>
<script setup>
import ThePath from "./ThePath.vue";
import Station from "./Station.vue";
import LineNumber from "./LineNumber.vue";
import SubwayInfo from "../../../assets/SubwayInfo.json";
const props = defineProps({
  switchState: Array,
});
</script>

<style scoped>
.nonActive {
  opacity: 0.1;
}
</style>
