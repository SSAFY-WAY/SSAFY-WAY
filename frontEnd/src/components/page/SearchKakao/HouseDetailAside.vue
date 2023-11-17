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
    <!-- x버튼 -->

    <!-- 아파트 이미지 -->
    <section class="houseImg container">
      <div class="houseImg content">
        <span>대치동 은마아파트</span>
        <span>건축년도 : 2003.11</span>
        <v-icon
          icon="mdi-close"
          size="30px"
          color="white"
          class="close-icon"
          @click="$emit('closeDetail')"
        ></v-icon>
      </div>
      <v-icon
        icon="mdi-home-group"
        size="150px"
        color="white"
        class="house-icon"
      ></v-icon>
    </section>
    <!-- 평수 별 매물 조회 버튼 -->
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
          class="houseInfoNav item"
          :class="[selectedHouseIndex === index ? 'active' : '']"
        >
          {{ house.measure }} 평
        </button>
      </ul>
    </section>
    <!-- 매물 상세 정보  -->
    <section class="houseInfo container">
      <HouseInfo :houseInfo="currentAptInfo.house[selectedHouseIndex]" />
    </section>
    <!-- 로드뷰 -->
    <section class="roadview container">
      <strong class="roadview title">로드뷰</strong>
      <div id="roadview"></div>
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
.close-icon {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}
.houseDetail.container {
  position: absolute;
  float: right;
  background-color: white;
  width: 25%;
  height: auto;
  max-height: 605px;
  overflow-y: scroll;
  -ms-overflow-style: none;
  z-index: 99;
  top: 90px;
  right: 0;
  background-color: white;
  visibility: visible;
}
.houseDetail.container::-webkit-scrollbar {
  display: none;
}

.roadview.container {
  width: 100%;
  height: 300px;
  padding: 15px;
}
.roadview.title {
  height: 10%;
}
#roadview {
  margin-top: 5px;
  width: 100%;
  height: 80%;
  border-radius: 20px;
}
.houseInfoNav.container {
  width: 100%;
  height: auto;
  padding: 15px;
}

.houseInfoNav.items {
  display: flex;
  /* justify-content: space-around; */
}
.houseImg.container {
  height: 250px;
  width: 100%;
  position: relative;
  background-color: rgba(0, 0, 0, 0.5);
}
.houseImg.container:after {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  background-image: url("@/assets/houseDetail.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  height: 100%;
  opacity: 0.5;
  z-index: -1;
}
.houseImg.content {
  width: 100%;
  height: 100%;
  padding: 15px;
  color: white;
  font-weight: bold;
  font-size: 20px;
}
.houseImg.content {
  display: flex;
  flex-direction: column;
}
.house-icon {
  position: absolute;
  right: 5%;
  bottom: 5%;
}

.title {
  color: var(--primary);
}
.houseInfoNav.item {
  width: 50px;
  height: auto;
  border-bottom: 1px solid lightgray;
  margin: 5px;
}
.houseInfoNav.item.active {
  border-bottom: 2px solid var(--primary);
}
.houseInfo {
  border: 1px solid black;
  padding: 0 15px 0 15px;
}
</style>
