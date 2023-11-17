<script setup>
import KakaoMap from "@/components/page/SearchKakao/KakaoMap.vue";
import SearchAside from "@/components/page/SearchKakao/SearchAside.vue";
import HouseDetailAside from "@/components/page/SearchKakao/houseDetailAside.vue";
import { ref } from "vue";

// 상세정보 보여주기 / 안보여주기 스위치
const isShowDetail = ref(false);
// 현재 클릭한 매물 데이터
const currentAptInfo = ref({});
// 스위치 상태 전환
const showHouseDetail = () => {
  if (!isShowDetail.value) isShowDetail.value = true;
};
const closeHouseDetail = () => {
  if (isShowDetail.value) isShowDetail.value = false;
};
// 현재 매물 데이터 업데이트
const injectHouseInfo = (aptInfo) => {
  currentAptInfo.value = aptInfo;
};
</script>
<template>
  <KakaoMap
    @click-marker="
      (aptInfo) => {
        showHouseDetail();
        injectHouseInfo(aptInfo);
      }
    "
  />
  <SearchAside />
  <template v-if="isShowDetail">
    <HouseDetailAside
      :currentAptInfo="currentAptInfo"
      @close-detail="closeHouseDetail"
    />
  </template>
</template>
