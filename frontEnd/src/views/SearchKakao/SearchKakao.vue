<script setup>
import KakaoMap from "@/components/page/SearchKakao/KakaoMap.vue";
import SearchAside from "@/components/page/SearchKakao/SearchAside.vue";
import HouseDetailAside from "@/components/page/SearchKakao/houseDetailAside.vue";
import { ref, onMounted } from "vue";
import mockInfo from "@/mocks/aptInfo.json";
import { requestBuildingBySubway } from "@/apis/request/requestBuilding.js";

// 상세정보 보여주기 / 안보여주기 스위치
const isShowDetail = ref(false);
// 지하철 타입, 법정동 타입
const type = ref("legal");
// 스위치 상태 전환
const showHouseDetail = () => {
  if (!isShowDetail.value) isShowDetail.value = true;
};
const closeHouseDetail = () => {
  if (isShowDetail.value) isShowDetail.value = false;
};
// BuildingList by 법정동 조회 정보
const buildingList = ref(mockInfo);
const getBuildingList = (data) => {
  buildingList.value = data;
  type.value = "legal";
};
// 현재 매물 데이터 업데이트
const currentBuildingInfo = ref({});
const getHouseInfo = (buildingInfo) => {
  currentBuildingInfo.value = buildingInfo;
};

// 지하철에서 넘어온 데이터가 있다면 그걸로 업데이트
onMounted(() => {
  if (history.state.data) {
    requestBuildingBySubway(history.state.data)
      .then((data) => {
        console.log(data.data);
        getBuildingList(data.data);
        type.value = "subway";
      })
      .catch((err) => {
        alert(err);
      });
  }
});
</script>
<template>
  <KakaoMap
    @click-marker="
      (buildingInfo) => {
        showHouseDetail();
        getHouseInfo(buildingInfo);
      }
    "
    :buildingList="buildingList"
  />
  <SearchAside @building-list="getBuildingList" />
  <template v-if="isShowDetail">
    <HouseDetailAside
      :currentBuildingInfo="currentBuildingInfo"
      :regionName="buildingList.regionName"
      @close-detail="closeHouseDetail"
    />
  </template>
</template>
