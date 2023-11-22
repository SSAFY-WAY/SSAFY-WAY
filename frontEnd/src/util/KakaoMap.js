import getImageUrl from "@/util/getUrl.js";

const loadMap = (buildingInfo, clickMarker) => {
  if (buildingInfo.buildingList.length === 0) return;
  const firstEl = buildingInfo.buildingList[0];
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(firstEl.lat, firstEl.lng),
    level: 5,
  };
  const map = new kakao.maps.Map(container, options);
  // 마커를 표시할 위치와 title 객체 배열입니다

  // 마커 이미지의 이미지 주소입니다
  const imageSrc = getImageUrl("home_image1.png");
  for (let i = 0; i < buildingInfo.buildingList.length; i++) {
    const building = buildingInfo.buildingList[i];
    // 마커 이미지의 이미지 크기 입니다
    const imageSize = new kakao.maps.Size(40, 40);

    // 마커 이미지를 생성합니다
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: new kakao.maps.LatLng(building.lat, building.lng), // 마커를 표시할 위치
      title: building.buildingName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
    kakao.maps.event.addListener(marker, "click", () => {
      clickMarker(building);
    });
  }

  // marker.setMap(map);

  // buildingInfo[지하철]이 존재하면? (지하철 검색이면) -> 지하철역 마커 띄우기
  var circle = new kakao.maps.Circle({
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 원의 중심좌표 입니다
    radius: 50, // 미터 단위의 원의 반지름입니다
    strokeWeight: 5, // 선의 두께입니다
    strokeColor: "#75B8FA", // 선의 색깔입니다
    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "dashed", // 선의 스타일 입니다
    fillColor: "#CFE7FF", // 채우기 색깔입니다
    fillOpacity: 0.7, // 채우기 불투명도 입니다
  });

  // 지도에 원을 표시합니다
  circle.setMap(map);
  // 지하철역과 distance를 통해 마커 동그라미 설정
};
const loadScript = (buildingInfo, clickMarker) => {
  if (buildingInfo.buildingList.length === 0) return;
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
    import.meta.env.VITE_KAKAO_KEY
  }&autoload=false`;
  script.onload = () =>
    window.kakao.maps.load(() => {
      loadMap(buildingInfo, clickMarker);
    });
  document.head.appendChild(script);
};

export { loadMap, loadScript };
