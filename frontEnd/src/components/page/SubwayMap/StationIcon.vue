<template>
  <div class="stationIcon" :style="{ backgroundColor: color }">
    {{ props.name }}
    <v-icon
      class="close"
      icon="mdi-window-close"
      size="11px"
      color="dark4"
      @click="
        () => {
          deleteStation(name);
        }
      "
    ></v-icon>
  </div>
</template>

<script setup>
import { useStationStore } from "@/store/stationStore.js";
const props = defineProps({
  name: String,
  color: String,
});

const { deleteStation } = useStationStore();
</script>

<style scoped>
.stationIcon {
  margin: 5px;
  min-width: 40px;
  height: 30px;
  color: white;
  border-radius: 10px;
  padding: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 13px;
  background-color: gray;
  position: relative;
  /* font-weight: bold; */
}
.close {
  position: absolute;
  top: -2px;
  right: -5px;
  padding: 8px;
  background-color: white;
  border-radius: 10px;
  border: 1px solid lightgray;
  cursor: pointer;
}
</style>
