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
    const imageSize = new kakao.maps.Size(45, 45);

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
