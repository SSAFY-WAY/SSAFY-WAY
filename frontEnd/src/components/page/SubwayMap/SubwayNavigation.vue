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
    <div>hihihi</div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
  switchState: Array,
});
console.log(props.switchState);
const metroNumber = ref([1, 2, 3, 4, 5, 6, 7, 8, 9]);
const metroButton = ref("metroButton");
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
  border-radius: 35px;
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
</style>
