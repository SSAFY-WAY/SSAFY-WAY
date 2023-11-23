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
  <div v-if="isLogin" class="houseDetail container">
    <div>
      <!-- 아파트 이미지 -->
      <section class="houseImg container">
        <div class="houseImg content">
          <span>{{ currentBuildingInfo.buildingName }}</span>
          <span>건축년도 : {{ currentBuildingInfo.builtYear }}년</span>
          <span
            v-if="currentBuildingInfo.nearestSubwayName"
            class="houseImg subwayDistance"
          >
            {{ currentBuildingInfo.nearestSubwayName }}에서
            {{ currentBuildingInfo.distanceFromSubway }}M
            <br />
          </span>
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
        <!-- <ul class="houseInfoNav items">
        <button
          v-for="(house, index) in currentBuildingInfo.houseDetailList"
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
          {{ house.area }} 평
        </button>
      </ul> -->
        <v-sheet class="mx-auto" max-width="1000">
          <v-slide-group show-arrows>
            <v-slide-group-item
              v-for="(house, index) in currentBuildingInfo.houseList"
              :key="index"
              v-slot="{ isSelected, toggle }"
            >
              <v-btn
                class="ma-2"
                rounded
                compact
                :color="isSelected ? 'primary' : undefined"
                @click="
                  () => {
                    toggle();
                    selectHouse(index);
                  }
                "
                size="small"
              >
                {{ house.area }} 평
              </v-btn>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </section>
      <!-- 매물 상세 정보  -->
      <section class="houseInfo container">
        <HouseInfo
          :houseInfo="currentBuildingInfo.houseList[selectedHouseIndex]"
          :regionName="currentBuildingInfo.regionName"
        />
      </section>
      <!-- 로드뷰 -->
      <section class="roadview container">
        <strong class="roadview title">로드뷰</strong>
        <div id="roadview"></div>
      </section>
    </div>
  </div>
  <div v-else class="loginNotice">
    <span>더 많은 정보를 확인하려면</span>
    <span>로그인해주세요!</span>
    <br />
    <router-link class="login-link" to="login">로그인하러 가기!</router-link>
  </div>
</template>

<script setup>
import { onMounted, ref, watch, computed } from "vue";
import HouseInfo from "@/components/page/SearchKakao/HouseInfo.vue";
import { useUserStore } from "@/store/userStore.js";
const props = defineProps({
  currentBuildingInfo: Object,
});
const currentBuildingInfo = computed(() => {
  return props.currentBuildingInfo;
});
const selectedHouseIndex = ref(0);
const selectHouse = (index) => {
  selectedHouseIndex.value = index;
};
const { isLogin } = useUserStore();
//ANCHOR
onMounted(() => {
  // 최초 마운트 시 로드뷰 띄우기
  if (isLogin) {
    const roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    const position = new kakao.maps.LatLng(
      currentBuildingInfo.value.lat,
      currentBuildingInfo.value.lng
    );
    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 50, function (panoId) {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
  }
});
watch(currentBuildingInfo, () => {
  if (isLogin) {
    const roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    const position = new kakao.maps.LatLng(
      currentBuildingInfo.value.lat,
      currentBuildingInfo.value.lng
    );
    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 500, function (panoId) {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
  }
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
  width: 30%;
  height: 100%;
  max-height: 730px;
  overflow-y: scroll;
  -ms-overflow-style: none;
  z-index: 99;
  top: 90px;
  right: 0;
  background-color: white;
  visibility: visible;
  height: 100%;
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
  border-bottom: 1px solid lightgray;
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
  border-bottom: 1px solid lightgray;
  padding: 10px 15px 10px 15px;
}
.houseImg.subwayDistance {
  background-color: var(--primary);
  padding: 5px;
  margin-top: 5px;
  color: var(white);
  width: fit-content;
  border-radius: 10px;
  box-shadow: 2px 2px var(--accent1);
}
.loginNotice {
  position: absolute;
  float: right;
  background-color: blue;
  width: 30%;
  height: 100%;
  max-height: 605px;
  overflow-y: scroll;
  -ms-overflow-style: none;
  z-index: 99;
  top: 90px;
  right: 0;
  background-color: white;
  visibility: visible;
  display: flex;
  justify-content: center;
  align-items: center;
  color: var(--accent4);
  font-weight: bold;
  font-size: 25px;
  flex-direction: column;
}
.login-link {
  background-color: var(--accent4);
  border-radius: 30px;
  padding: 10px;
  color: white;
}
</style>
