<!-- 
자치구 
법정동
건물명
종류
임대면적
보증금
임대료
층 -->
<template>
  <div class="houseDetail container">
    <!-- 로드뷰 -->
    <section class="roadview container">
      <strong class="roadview title">로드뷰</strong>
      <div id="roadview"></div>
    </section>
    <section class="houseInfoNav container">
      <strong class="houseInfoNav title">면적 별 매물 정보</strong>
      <ul class="houseInfoNav items">
        <button
          v-for="(house, index) in currentAptInfo.house"
          :key="index"
          @click="
            (e) => {
              e.preventDefault();
              selectHouse(index);
            }
          "
        >
          {{ house.measure }} 평
        </button>
      </ul>
    </section>
    <section class="houseInfo container">
      <HouseInfo :houseInfo="currentAptInfo.house[selectedHouseIndex]" />
    </section>
  </div>
</template>

<script setup>
import { onMounted, ref, watch, computed } from "vue";
import HouseInfo from "@/components/page/SearchKakao/HouseInfo.vue";
const props = defineProps({
  currentAptInfo: Object,
});
const currentAptInfo = computed(() => {
  return props.currentAptInfo;
});
const selectedHouseIndex = ref(0);
const selectHouse = (index) => {
  selectedHouseIndex.value = index;
};

//ANCHOR
onMounted(() => {
  // 최초 마운트 시 로드뷰 띄우기
  const roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
  const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
  const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
  const position = new kakao.maps.LatLng(
    currentAptInfo.value.latlng[0],
    currentAptInfo.value.latlng[1]
  );
  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
  roadviewClient.getNearestPanoId(position, 50, function (panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
  });
});
watch(currentAptInfo, () => {
  const roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
  const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
  const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
  const position = new kakao.maps.LatLng(
    currentAptInfo.value.latlng[0],
    currentAptInfo.value.latlng[1]
  );
  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
  roadviewClient.getNearestPanoId(position, 500, function (panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
  });
});
</script>

<style scoped>
.houseDetail.container {
  position: absolute;
  float: right;
  background-color: white;
  width: 25%;
  height: 88.5%;
  z-index: 99;
  top: 90px;
  right: 0;
  background-color: white;
  visibility: visible;
  padding: 15px 0;
}
.roadview.container {
  width: 100%;
  height: 40%;
}
.roadview.title {
  height: 10%;
}
#roadview {
  width: 100%;
  height: 90%;
}
.houseInfoNav.container {
  width: 100%;
  height: auto;
}
.houseInfoNav.items {
  display: flex;
  justify-content: space-around;
}
</style>
