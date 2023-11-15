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
import getImageUrl from "../../../util/getUrl";
import houseInfo from "../../../mocks/houseInfo.json";
const onClickMarker = () => {
  console.log("마커 클릭");
};
const loadMap = () => {
  var container = document.getElementById("map");
  var options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  var map = new kakao.maps.Map(container, options);
  // 마커를 표시할 위치와 title 객체 배열입니다

  // 마커 이미지의 이미지 주소입니다
  var imageSrc = getImageUrl("home_image1.png");
  for (var i = 0; i < houseInfo.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(45, 45);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: new kakao.maps.LatLng(
        houseInfo[i].latlng[0],
        houseInfo[i].latlng[1]
      ), // 마커를 표시할 위치
      title: houseInfo[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
    kakao.maps.event.addListener(marker, "click", onClickMarker);
  }

  marker.setMap(map);
};
const loadScript = () => {
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
    import.meta.env.VITE_KAKAO_KEY
  }&autoload=false`;
  script.onload = () => window.kakao.maps.load(loadMap);
  document.head.appendChild(script);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    // 카카오 객체가 있고 카카오 맵을 그릴 준비가 됐으면 맵 실행
    loadMap();
  } else {
    loadScript();
  }
});
</script>
