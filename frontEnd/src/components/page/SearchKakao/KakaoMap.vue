<template>
  <div id="map"></div>
</template>

<style scoped>
#map {
  width: 100%;
  height: 100vh;
}
</style>

<script setup>
import { onMounted } from "vue";
import aptInfo from "@/mocks/aptInfo.json";
import { loadMap, loadScript } from "@/util/KakaoMap";
const emit = defineEmits(["clickMarker"]);
const clickMarker = (data) => {
  // 화면 우측에 UI 띄우기 -> 컴포넌트 visibility visible, 클릭한 위치의 데이터 전달 -> emit으로 방출
  emit("clickMarker", data);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    // 카카오 객체가 있고 카카오 맵을 그릴 준비가 됐으면 맵 실행
    loadMap(aptInfo, clickMarker);
  } else {
    loadScript(aptInfo, clickMarker);
  }
});
</script>
