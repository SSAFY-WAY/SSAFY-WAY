<script setup>
import KakaoMap from "@/components/page/SearchKakao/KakaoMap.vue";
import SearchAside from "@/components/page/SearchKakao/SearchAside.vue";
import HouseDetailAside from "@/components/page/SearchKakao/houseDetailAside.vue";
import { ref } from "vue";
import mockInfo from "@/mocks/aptInfo.json";

// 상세정보 보여주기 / 안보여주기 스위치
const isShowDetail = ref(false);

// 스위치 상태 전환
const showHouseDetail = () => {
  if (!isShowDetail.value) isShowDetail.value = true;
};
const closeHouseDetail = () => {
  if (isShowDetail.value) isShowDetail.value = false;
};
// Building 정보
const buildingList = ref(mockInfo);
const getBuildingList = (data) => {
  buildingList.value = data;
};
// 현재 매물 데이터 업데이트
const currentBuildingInfo = ref({});
const getHouseInfo = (buildingInfo) => {
  currentBuildingInfo.value = buildingInfo;
};
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
